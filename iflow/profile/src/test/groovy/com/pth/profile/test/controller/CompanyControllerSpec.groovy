package com.pth.profile.test.controller

import com.pth.common.contants.ApiUrlConstants
import com.pth.common.edo.CompanyEdo
import com.pth.common.edo.CompanyWorkflowtypeItemOcrSettingPresetEdo
import com.pth.common.edo.CompanyWorkflowtypeItemOcrSettingPresetListEdo
import com.pth.common.edo.enums.ECompanyType
import com.pth.profile.entities.CompanyEntity
import com.pth.profile.entities.CompanyWorkflowTypeOcrSettingPresetEntity
import com.pth.profile.mapper.ICompanyMapper
import com.pth.profile.mapper.ICompanyWorkflowTypeOcrSettingPresetMapper
import com.pth.profile.mapper.impl.CompanyMapper
import com.pth.profile.mapper.impl.CompanyWorkflowTypeOcrSettingPresetMapper
import com.pth.profile.services.data.ICompanyService
import com.pth.profile.test.ProfileTestDataProvider
import io.micronaut.context.ApplicationContext
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.RxHttpClient
import io.micronaut.http.client.exceptions.HttpClientResponseException
import io.micronaut.runtime.server.EmbeddedServer
import io.micronaut.security.authentication.UsernamePasswordCredentials
import io.micronaut.security.token.jwt.render.BearerAccessRefreshToken

import java.lang.reflect.Array

class CompanyControllerSpec extends ProfileTestDataProvider {

    private EmbeddedServer embeddedServer = ApplicationContext.run(EmbeddedServer)

    private RxHttpClient lowLevelClient = embeddedServer.applicationContext.createBean(RxHttpClient,
                                                                                       embeddedServer.getURL())

    private ICompanyService companyService;
    private ICompanyMapper companyModelEdoMapper;
    private ICompanyWorkflowTypeOcrSettingPresetMapper presetModelEdoMapper;

    private String authenticationToken

    void setup() {
        companyModelEdoMapper = new CompanyMapper()
        presetModelEdoMapper = new CompanyWorkflowTypeOcrSettingPresetMapper()

        companyService = Mock()

        embeddedServer.applicationContext.registerSingleton(companyService)
        embeddedServer.applicationContext.registerSingleton(companyModelEdoMapper)
        embeddedServer.applicationContext.registerSingleton(presetModelEdoMapper)

        def credentialsValid = new UsernamePasswordCredentials("admin", "password")

        def accessTokenRequestValid = HttpRequest.POST("/api001/profile001/authentication/login", credentialsValid)
        def accessTokenResponseValid = lowLevelClient.toBlocking()
                .exchange(accessTokenRequestValid, BearerAccessRefreshToken)
        authenticationToken = accessTokenResponseValid.body().accessToken

    }

    void "verify readCompany fails and is unauthenticated"() {

        given:
            def testId = UUID.randomUUID()
        when:

            def documentUploadRequest = HttpRequest.
                    GET(ApiUrlConstants.ProfileUrlConstants.API001_CORE001_COMPANY + "/read/" + testId.toString())

            def documentMetaDataResponse = lowLevelClient.toBlocking()
                                                         .exchange(documentUploadRequest, CompanyEdo)

        then:

            def documentMetaDataResponseException = thrown(HttpClientResponseException)

            documentMetaDataResponseException.status == HttpStatus.UNAUTHORIZED
            documentMetaDataResponseException.response.body() == null
            documentMetaDataResponse == null

    }


    void "verify readCompany is ok and return correct company"() {

        given:
            def companyEntity = createTestCompanyEntity(1)
            def testId = UUID.randomUUID()
        when:

            def documentUploadRequest = HttpRequest.
                    GET(ApiUrlConstants.ProfileUrlConstants.API001_CORE001_COMPANY + "/read/" + testId.toString())
                    .bearerAuth(authenticationToken)

            def documentMetaDataResponse = lowLevelClient.toBlocking()
                    .exchange(documentUploadRequest, CompanyEdo)

        then:

            noExceptionThrown()
            documentMetaDataResponse != null
            documentMetaDataResponse.status == HttpStatus.OK
            documentMetaDataResponse.body() != null

            def companyEdo = documentMetaDataResponse.body()
            verifyCompanyEdoAndEntity(companyEdo, companyEntity)

        and:
            1 * companyService.getById(_) >> Optional.of(companyEntity)
    }

    void "verify saveCompany fails and is unauthenticated"() {

        given:
            def companyEdo = new CompanyEdo()

        when:

            def documentUploadRequest = HttpRequest.
                    POST(ApiUrlConstants.ProfileUrlConstants.API001_CORE001_COMPANY + "/save", companyEdo)

            def documentMetaDataResponse = lowLevelClient.toBlocking()
                    .exchange(documentUploadRequest, CompanyEdo)

        then:

            def documentMetaDataResponseException = thrown(HttpClientResponseException)

            documentMetaDataResponseException.status == HttpStatus.UNAUTHORIZED
            documentMetaDataResponseException.response.body() == null
            documentMetaDataResponse == null

    }

    void "verify saveCompany is ok and return created companyEdo"() {

        given:
            def companyEdo = createTestCompanyEdo(1)
            def companyEntity = createTestCompanyEntity(1)

        when:

            def documentUploadRequest = HttpRequest.
                    POST(ApiUrlConstants.ProfileUrlConstants.API001_CORE001_COMPANY + "/save", companyEdo)
                    .bearerAuth(authenticationToken)

            def documentMetaDataResponse = lowLevelClient.toBlocking()
                                                         .exchange(documentUploadRequest, CompanyEdo)

        then:

            noExceptionThrown()
            documentMetaDataResponse != null
            documentMetaDataResponse.status == HttpStatus.CREATED
            documentMetaDataResponse.body() != null

            def companyEdoSaved = documentMetaDataResponse.body()
            verifyCompanyEdoAndEntity(companyEdoSaved, companyEntity)

        and:
            1 * companyService.save(_) >> Optional.of(companyEntity)
    }

    void "verify readCompanyWorkflowtypeItemOcrSettings fails and is unauthenticated"() {

        given:

        when:

            def documentUploadRequest = HttpRequest.
                    GET(ApiUrlConstants.ProfileUrlConstants.API001_CORE001_COMPANY + "/readwtoctsettings/identity1")

            def documentMetaDataResponse = lowLevelClient.toBlocking()
                    .exchange(documentUploadRequest, CompanyEdo)

        then:

            def documentMetaDataResponseException = thrown(HttpClientResponseException)

            documentMetaDataResponseException.status == HttpStatus.UNAUTHORIZED
            documentMetaDataResponseException.response.body() == null
            documentMetaDataResponse == null


    }

    void "verify readCompanyWorkflowtypeItemOcrSettings ok and return correct edoList"() {

        given:
            def entityList = new ArrayList<CompanyWorkflowTypeOcrSettingPresetEntity>();
            for(int i=1; i<4; i++){
                def companyWorkflowTypeOcrSettingPresetEntity = new CompanyWorkflowTypeOcrSettingPresetEntity()
                companyWorkflowTypeOcrSettingPresetEntity.companyId = UUID.randomUUID()
                companyWorkflowTypeOcrSettingPresetEntity.workflowTypeId = UUID.randomUUID()
                companyWorkflowTypeOcrSettingPresetEntity.identity = generateRandomString(15)
                companyWorkflowTypeOcrSettingPresetEntity.presetName = "Test-Company-" + i + "-" + generateRandomString(5)
                companyWorkflowTypeOcrSettingPresetEntity.version = 1+i
                companyWorkflowTypeOcrSettingPresetEntity.status = 1

                entityList.add(companyWorkflowTypeOcrSettingPresetEntity)
            }

            def testId = UUID.randomUUID()
        when:

            def documentUploadRequest = HttpRequest.
                    GET(ApiUrlConstants.ProfileUrlConstants.API001_CORE001_COMPANY + "/readwtoctsettings/" + testId.toString())
                    .bearerAuth(authenticationToken)

            def documentMetaDataResponse = lowLevelClient.toBlocking()
                                                         .exchange(documentUploadRequest, CompanyWorkflowtypeItemOcrSettingPresetListEdo)

        then:

            noExceptionThrown()
            documentMetaDataResponse != null
            documentMetaDataResponse.status == HttpStatus.OK
            documentMetaDataResponse.body() != null
            def respBody = documentMetaDataResponse.body()
            respBody.companyWorkflowtypeItemOcrSettings.size() == entityList.size()

        and:
            1 * companyService.getCompanyWorkflowtypeItemOcrSettingListByCompanyIdentity(_) >> entityList

    }

    void "verify saveCompanyWorkflowtypeItemOcrSettings fails and is unauthenticated"() {

        given:
            def preset = new CompanyWorkflowtypeItemOcrSettingPresetEdo()

        when:

            def documentUploadRequest = HttpRequest.
                    POST(ApiUrlConstants.ProfileUrlConstants.API001_CORE001_COMPANY + "/savewtoctsettings", preset)

            def documentMetaDataResponse = lowLevelClient.toBlocking()
                    .exchange(documentUploadRequest, CompanyEdo)

        then:

            def documentMetaDataResponseException = thrown(HttpClientResponseException)

            documentMetaDataResponseException.status == HttpStatus.UNAUTHORIZED
            documentMetaDataResponseException.response.body() == null
            documentMetaDataResponse == null

    }


    void "verify saveCompanyWorkflowtypeItemOcrSettings ok and return savedEdo"() {

        given:
            def preset =  createTestCompanyWorkflowtypeItemOcrSettingPresetEdo(1)
            def modelInput = createTestCompanyWorkflowTypeOcrSettingPresetEntity(1)

        when:

        def documentUploadRequest = HttpRequest.
                POST(ApiUrlConstants.ProfileUrlConstants.API001_CORE001_COMPANY + "/savewtoctsettings",
                        preset)
                .bearerAuth(authenticationToken)

        def documentMetaDataResponse = lowLevelClient.toBlocking()
                .exchange(documentUploadRequest, CompanyEdo)

        then:

        noExceptionThrown()
        documentMetaDataResponse != null
        documentMetaDataResponse.status == HttpStatus.CREATED
        documentMetaDataResponse.body() != null

        def modelEdoSaved = documentMetaDataResponse.body()
        //verifyCompanyEdoAndEntity(modelEdoSaved, modelInput)

        and:
        1 * companyService.saveCompanyWorkflowtypeItemOcrSetting(_) >> Optional.of(modelInput )

    }

    void "verify deleteCompanyWorkflowtypeItemOcrSettings fails and is unauthenticated"() {

        given:
            def preset = new CompanyWorkflowtypeItemOcrSettingPresetEdo()

        when:

            def documentUploadRequest = HttpRequest.
                    POST(ApiUrlConstants.ProfileUrlConstants.API001_CORE001_COMPANY + "/deletewtoctsettings", preset)

            def documentMetaDataResponse = lowLevelClient.toBlocking()
                    .exchange(documentUploadRequest, CompanyEdo)

        then:

            def documentMetaDataResponseException = thrown(HttpClientResponseException)

            documentMetaDataResponseException.status == HttpStatus.UNAUTHORIZED
            documentMetaDataResponseException.response.body() == null
            documentMetaDataResponse == null

    }

    void "verify deleteCompanyWorkflowtypeItemOcrSettings ok"() {

        given:
        def preset =  createTestCompanyWorkflowtypeItemOcrSettingPresetEdo(1)
        def modelInput = createTestCompanyWorkflowTypeOcrSettingPresetEntity(1)

        when:

        def documentUploadRequest = HttpRequest.
                POST(ApiUrlConstants.ProfileUrlConstants.API001_CORE001_COMPANY + "/deletewtoctsettings", preset)
                .bearerAuth(authenticationToken)

        def documentMetaDataResponse = lowLevelClient.toBlocking()
                .exchange(documentUploadRequest, CompanyEdo)

        then:

        noExceptionThrown()
        documentMetaDataResponse != null
        documentMetaDataResponse.status == HttpStatus.ACCEPTED

        and:
        1 * companyService.deleteCompanyWorkflowtypeItemOcrSetting(_)

    }


    private void verifyCompany(CompanyEntity testCompanyEntity,
                               CompanyEntity CompanyEntity) {
        testCompanyEntity.companyName == CompanyEntity.companyName
        testCompanyEntity.identity == CompanyEntity.identity
        testCompanyEntity.companyType == CompanyEntity.companyType
        testCompanyEntity.companyTypeCustome == CompanyEntity.companyTypeCustome
        testCompanyEntity.status == CompanyEntity.status

    }



    private void verifyCompanyEdoAndEntity(CompanyEdo companyEdo,
                                           CompanyEntity companyEntity) {
        companyEdo.companyName == companyEntity.companyName
        companyEdo.identity == companyEntity.identity
        companyEdo.companyType == companyEntity.companyType
        companyEdo.companyTypeCustome == companyEntity.companyTypeCustome
        companyEdo.status == companyEntity.status

    }

}
