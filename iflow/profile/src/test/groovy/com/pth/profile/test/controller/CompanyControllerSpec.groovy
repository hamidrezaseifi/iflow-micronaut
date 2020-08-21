package com.pth.profile.test.controller

import com.pth.common.contants.ApiUrlConstants
import com.pth.common.edo.CompanyEdo
import com.pth.common.edo.CompanyWorkflowtypeItemOcrSettingPresetEdo
import com.pth.common.edo.TokenValidationRequestEdo
import com.pth.common.edo.enums.EApplication
import com.pth.profile.entities.CompanyEntity
import com.pth.profile.mapper.ICompanyMapper
import com.pth.profile.mapper.ICompanyWorkflowTypeOcrSettingPresetMapper
import com.pth.profile.mapper.IUserAuthenticationEdoMapper
import com.pth.profile.mapper.impl.CompanyMapper
import com.pth.profile.mapper.impl.CompanyWorkflowTypeOcrSettingPresetMapper
import com.pth.profile.mapper.impl.UserAuthenticationEdoMapper
import com.pth.profile.services.authentication.IProfileAuthenticationManager
import com.pth.profile.services.data.ICompanyService
import com.pth.profile.test.ProfileTestDataProvider
import io.micronaut.context.ApplicationContext
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.RxHttpClient
import io.micronaut.http.client.exceptions.HttpClientResponseException
import io.micronaut.runtime.server.EmbeddedServer
import io.micronaut.security.authentication.DefaultAuthentication
import io.micronaut.security.authentication.UsernamePasswordCredentials
import io.micronaut.security.token.jwt.render.BearerAccessRefreshToken

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

        when:

        def documentUploadRequest = HttpRequest.
                GET(ApiUrlConstants.ProfileUrlConstants.API001_CORE001_COMPANY + "/readbyidentity/identity1")

        def documentMetaDataResponse = lowLevelClient.toBlocking()
                .exchange(documentUploadRequest, CompanyEdo)

        then:

        def documentMetaDataResponseException = thrown(HttpClientResponseException)

        documentMetaDataResponseException.status == HttpStatus.UNAUTHORIZED
        documentMetaDataResponseException.response.body() == null
        documentMetaDataResponse == null

        //and:
        // 1 * jwtTokenValidator.validateToken(_) >> Flowable.just(new DefaultAuthentication("username" , new HashMap<String, Object>()))
        //1 * profileAuthenticationManager.validateAuthentication(_) >> Optional.empty()
    }


    void "verify readCompany is ok and resturn correct company"() {

        given:
            def companyEntity = createTestCompany(1)
        when:

            def documentUploadRequest = HttpRequest.
                    GET(ApiUrlConstants.ProfileUrlConstants.API001_CORE001_COMPANY + "/readbyidentity/identity1")
                    .bearerAuth(authenticationToken)

            def documentMetaDataResponse = lowLevelClient.toBlocking()
                    .exchange(documentUploadRequest, CompanyEdo)

        then:

            noExceptionThrown()
            documentMetaDataResponse != null
            documentMetaDataResponse.status == HttpStatus.OK
            documentMetaDataResponse.body() != null

            def companyEdo = documentMetaDataResponse.body()
            verifyCompanyEdoANdEntity(companyEdo, companyEntity)

        and:
            1 * companyService.getByIdentity(_) >> Optional.of(companyEntity)
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

        //and:
        // 1 * jwtTokenValidator.validateToken(_) >> Flowable.just(new DefaultAuthentication("username" , new HashMap<String, Object>()))
        //1 * profileAuthenticationManager.validateAuthentication(_) >> Optional.empty()
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

        //and:
        // 1 * jwtTokenValidator.validateToken(_) >> Flowable.just(new DefaultAuthentication("username" , new HashMap<String, Object>()))
        //1 * profileAuthenticationManager.validateAuthentication(_) >> Optional.empty()
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

        //and:
        // 1 * jwtTokenValidator.validateToken(_) >> Flowable.just(new DefaultAuthentication("username" , new HashMap<String, Object>()))
        //1 * profileAuthenticationManager.validateAuthentication(_) >> Optional.empty()
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

        //and:
        // 1 * jwtTokenValidator.validateToken(_) >> Flowable.just(new DefaultAuthentication("username" , new HashMap<String, Object>()))
        //1 * profileAuthenticationManager.validateAuthentication(_) >> Optional.empty()
    }



    private void verifyCompany(CompanyEntity testCompanyEntity,
                               CompanyEntity CompanyEntity) {
        testCompanyEntity.companyName == CompanyEntity.companyName
        testCompanyEntity.identity == CompanyEntity.identity
        testCompanyEntity.companyType == CompanyEntity.companyType
        testCompanyEntity.companyTypeCustome == CompanyEntity.companyTypeCustome
        testCompanyEntity.status == CompanyEntity.status

    }



    private void verifyCompanyEdoANdEntity(CompanyEdo companyEdo,
                               CompanyEntity companyEntity) {
        companyEdo.companyName == companyEntity.companyName
        companyEdo.identity == companyEntity.identity
        companyEdo.companyType == companyEntity.companyType
        companyEdo.companyTypeCustome == companyEntity.companyTypeCustome
        companyEdo.status == companyEntity.status

    }

}
