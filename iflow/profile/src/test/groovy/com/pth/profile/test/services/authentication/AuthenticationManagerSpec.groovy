package com.pth.profile.test.services.authentication

import com.pth.common.credentials.IPasswordHashGenerator
import com.pth.common.edo.enums.ECompanyType
import com.pth.profile.entities.CompanyEntity
import com.pth.profile.entities.CompanyWorkflowTypeOcrSettingPresetEntity
import com.pth.profile.models.UserAuthenticationRequest
import com.pth.profile.repositories.ICompanyRepository
import com.pth.profile.repositories.ICompanyWorkflowTypeOcrSettingPresetRepository
import com.pth.profile.repositories.IRefreshTokenRepository
import com.pth.profile.services.authentication.AuthenticationManager
import com.pth.profile.services.authentication.IAuthenticationManager
import com.pth.profile.services.data.ICompanyService
import com.pth.profile.services.data.impl.CompanyService
import com.pth.profile.test.ProfileTestDataProvider
import io.micronaut.context.ApplicationContext
import io.micronaut.runtime.server.EmbeddedServer
import io.micronaut.security.token.jwt.generator.JwtGeneratorConfigurationProperties

import java.util.stream.Collectors

class AuthenticationManagerSpec extends ProfileTestDataProvider {

    private EmbeddedServer embeddedServer = ApplicationContext.run(EmbeddedServer)

    private IPasswordHashGenerator passwordHashGenerator;
    private IRefreshTokenRepository refreshTokenRepository;
    private JwtGeneratorConfigurationProperties jwtConfigurationProperties;

    private IAuthenticationManager authenticationManager

    void setup() {
        passwordHashGenerator = Mock()
        embeddedServer.applicationContext.registerSingleton(passwordHashGenerator)

        refreshTokenRepository = Mock()
        embeddedServer.applicationContext.registerSingleton(refreshTokenRepository)

        jwtConfigurationProperties = Mock()
        embeddedServer.applicationContext.registerSingleton(jwtConfigurationProperties)

        authenticationManager = new AuthenticationManager(passwordHashGenerator, refreshTokenRepository, jwtConfigurationProperties)
    }

    void cleanup() {

    }

    void "verify authenticate through AuthenticationManager"() {

        given:

            def request = new UserAuthenticationRequest()
            request.username = "test-username"
            request.password = "password"
            request.companyIdentity = "test-company"
            request.appId = "test-app"

        when:
            def tokenOptional = authenticationManager.authenticate(request)

        then:
            tokenOptional.isPresent()
            tokenOptional.get().username == request.username
            tokenOptional.get().refreshToken == request.username
            tokenOptional.get().accessToken == request.username
            tokenOptional.get().roles == request.username

        and:
            1 * companyRepository.save(_)
            1 * companyRepository.getById(_) >> Optional.of(companyEntity)

    }

    void "verify company can queried by identity"() {

        given:

            def companyEntity = new CompanyEntity()
            companyEntity.companyName = "Test-Company" + generateRandomString(5)
            companyEntity.identity = generateRandomString(15)
            companyEntity.companyType = ECompanyType.EINZELUNTERNEHMEN.enumValue
            companyEntity.companyTypeCustome = "Test-Company"
            companyEntity.status = 1

        when:
            def companyOptional = companyService.getByIdentity("test identity")

        then:
            companyOptional.isPresent()
            verifyCompany(companyOptional.get(), companyEntity)
        and:
            1 * companyRepository.getByIdentity(_) >> Optional.of(companyEntity)

    }

    void "verify read company workflowtype-Item-OcrSettings can queried by company-id"() {

        given:

            Map<UUID, CompanyWorkflowTypeOcrSettingPresetEntity> settingInitialList = new HashMap<>()

            for(int i=1; i<= 11; i++){
                def setting = createTestCompanyWorkflowTypeOcrSettingPresetEntity(i)
                settingInitialList.put(setting.id, setting)
            }

        when:
            def settingList = companyService.getCompanyWorkflowtypeItemOcrSettingList(testCompanyId)

        then:
            settingList.size() == settingInitialList.size()
            for(def setting: settingList){
                def initSetting = settingInitialList.get(setting.id)
                setting.identity == initSetting.identity
                setting.companyId == initSetting.companyId
                setting.workflowTypeId == initSetting.workflowTypeId
                setting.presetName == initSetting.presetName
            }

        and:
            1 * workflowTypeOcrSettingPresetRepository.getByCompanyId(_) >>
            settingInitialList.values().stream().collect(Collectors.toList())

    }

    void "verify read company workflowtype-Item-OcrSettings can queried by company-identity"() {

        given:
            def companyEntity = new CompanyEntity()
            companyEntity.companyName = "Test-Company" + generateRandomString(5)
            companyEntity.identity = generateRandomString(15)
            companyEntity.companyType = ECompanyType.EINZELUNTERNEHMEN.enumValue
            companyEntity.companyTypeCustome = "Test-Company"
            companyEntity.status = 1

            Map<UUID, CompanyWorkflowTypeOcrSettingPresetEntity> settingInitialList = new HashMap<>()

            for(int i=1; i<= 11; i++){
                def setting = createTestCompanyWorkflowTypeOcrSettingPresetEntity(i)
                settingInitialList.put(setting.id, setting)
            }

        when:
            def settingList = companyService.getCompanyWorkflowtypeItemOcrSettingListByCompanyIdentity("test-identity")

        then:
            settingList.size() == settingInitialList.size()
            for(def setting: settingList){
                def initSetting = settingInitialList.get(setting.id)
                setting.identity == initSetting.identity
                setting.companyId == initSetting.companyId
                setting.workflowTypeId == initSetting.workflowTypeId
                setting.presetName == initSetting.presetName
            }

        and:
            1 * companyRepository.getByIdentity(_) >> Optional.of(companyEntity)
            1 * workflowTypeOcrSettingPresetRepository.getByCompanyId(_) >>
            settingInitialList.values().stream().collect(Collectors.toList())

    }

    void "verify save company workflowtype-Item-OcrSettings"() {

        given:

            def setting = createTestCompanyWorkflowTypeOcrSettingPresetEntity(1)

        when:
            def settingOptional = companyService.saveCompanyWorkflowtypeItemOcrSetting(setting)

        then:
            settingOptional.isPresent()
            setting.identity == settingOptional.get().identity
            setting.companyId == settingOptional.get().companyId
            setting.workflowTypeId == settingOptional.get().workflowTypeId
            setting.presetName == settingOptional.get().presetName

        and:
            1 * workflowTypeOcrSettingPresetRepository.save(_)
            1 * workflowTypeOcrSettingPresetRepository.getById(_) >> Optional.of(setting)

    }

    void "verify delete company workflowtype-Item-OcrSettings"() {

        given:

            def setting = createTestCompanyWorkflowTypeOcrSettingPresetEntity(1)

        when:
            companyService.deleteCompanyWorkflowtypeItemOcrSetting(setting)

        then:
            1 * workflowTypeOcrSettingPresetRepository.delete(_)

    }

    private void verifyCompany(CompanyEntity testCompanyEntity,
                               CompanyEntity CompanyEntity) {
        testCompanyEntity.companyName == CompanyEntity.companyName
        testCompanyEntity.identity == CompanyEntity.identity
        testCompanyEntity.companyType == CompanyEntity.companyType
        testCompanyEntity.companyTypeCustome == CompanyEntity.companyTypeCustome
        testCompanyEntity.status == CompanyEntity.status

    }

}