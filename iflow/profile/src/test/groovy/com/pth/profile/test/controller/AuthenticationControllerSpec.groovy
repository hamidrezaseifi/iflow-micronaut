package com.pth.profile.test.controller

import com.pth.common.contants.ApiUrlConstants
import com.pth.common.edo.TokenValidationRequestEdo
import com.pth.common.edo.enums.EApplication
import com.pth.profile.mapper.IUserAuthenticationEdoMapper
import com.pth.profile.mapper.impl.UserAuthenticationEdoMapper
import com.pth.profile.services.authentication.IProfileAuthenticationManager
import com.pth.profile.test.ProfileTestDataProvider
import io.micronaut.context.ApplicationContext
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.RxHttpClient
import io.micronaut.http.client.exceptions.HttpClientResponseException
import io.micronaut.runtime.server.EmbeddedServer
import io.micronaut.security.authentication.DefaultAuthentication
import io.micronaut.security.token.jwt.render.BearerAccessRefreshToken
import spock.lang.AutoCleanup

class AuthenticationControllerSpec extends ProfileTestDataProvider {

    @AutoCleanup
    private EmbeddedServer embeddedServer = ApplicationContext.run(EmbeddedServer, "test")

    private RxHttpClient lowLevelClient = embeddedServer.applicationContext.createBean(RxHttpClient,
                                                                                       embeddedServer.getURL())

    private IProfileAuthenticationManager profileAuthenticationManager
    private IUserAuthenticationEdoMapper authenticationEdoMapper

    void setup() {
        authenticationEdoMapper = new UserAuthenticationEdoMapper()

        profileAuthenticationManager = Mock()

        embeddedServer.applicationContext.registerSingleton(profileAuthenticationManager)
        embeddedServer.applicationContext.registerSingleton(authenticationEdoMapper)

    }

    void "verify validateToken fails"() {

        given:
            def request = new TokenValidationRequestEdo()
            request.appId = EApplication.IFLOW.identity
            request.token = "invalid-token"
            request.authentication = new DefaultAuthentication("username" , new HashMap<String, Object>())

        when:

            def documentUploadRequest = HttpRequest.
                    POST(ApiUrlConstants.ProfileUrlConstants.API001_PROFILE001_AUTHENTICATION + "/validateToken", request)

            def documentMetaDataResponse = lowLevelClient.toBlocking()
                                                         .exchange(documentUploadRequest, BearerAccessRefreshToken)

        then:

            def documentMetaDataResponseException = thrown(HttpClientResponseException)

            documentMetaDataResponseException.status == HttpStatus.UNAUTHORIZED
            documentMetaDataResponseException.response.body() == null
            documentMetaDataResponse == null

        and:
           // 1 * jwtTokenValidator.validateToken(_) >> Flowable.just(new DefaultAuthentication("username" , new HashMap<String, Object>()))
            1 * profileAuthenticationManager.validateAuthentication(_) >> Optional.empty()
    }

    void "verify validateToken is ok"() {

        given:
            def request = new TokenValidationRequestEdo()
            request.appId = EApplication.IFLOW.identity
            request.token = "invalid-token"
            request.authentication = new DefaultAuthentication("username" , new HashMap<String, Object>())

            def token = new BearerAccessRefreshToken()
            token.username = request.authentication.name

        when:

            def documentUploadRequest = HttpRequest.
                    POST(ApiUrlConstants.ProfileUrlConstants.API001_PROFILE001_AUTHENTICATION + "/validateToken", request)

            def documentMetaDataResponse = lowLevelClient.toBlocking()
                    .exchange(documentUploadRequest, BearerAccessRefreshToken)

        then:

            noExceptionThrown()

            documentMetaDataResponse != null
            documentMetaDataResponse.status == HttpStatus.OK
            documentMetaDataResponse.body() != null

            def resultToken = documentMetaDataResponse.body()
            resultToken.username == token.username

        and:
            1 * profileAuthenticationManager.validateAuthentication(_) >> Optional.of(token)
    }


    void "verify by validateToken request is invalid"() {

        given:
            def request = new TokenValidationRequestEdo()
            request.appId = EApplication.IFLOW.identity
            request.token = "invalid-token"
            request.authentication = null


        when:

            def documentUploadRequest = HttpRequest.
                    POST(ApiUrlConstants.ProfileUrlConstants.API001_PROFILE001_AUTHENTICATION + "/validateToken", request)

            def documentMetaDataResponse = lowLevelClient.toBlocking()
                    .exchange(documentUploadRequest, BearerAccessRefreshToken)

        then:

            def documentMetaDataResponseException = thrown(HttpClientResponseException)

            documentMetaDataResponseException.status == HttpStatus.BAD_REQUEST
            documentMetaDataResponseException.response.body() == null
            documentMetaDataResponse == null


    }
}
