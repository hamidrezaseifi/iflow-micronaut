package com.pth.profile.test.services.authentication

import com.pth.common.credentials.IPasswordHashGenerator
import com.pth.common.edo.enums.EApplication
import com.pth.profile.authentication.entities.RefreshTokenEntity
import com.pth.profile.models.TokenValidationRequest
import com.pth.profile.repositories.IRefreshTokenRepository
import com.pth.profile.services.authentication.ProfileAuthenticationManager
import com.pth.profile.services.authentication.IProfileAuthenticationManager
import com.pth.profile.test.ProfileTestDataProvider
import io.micronaut.context.ApplicationContext
import io.micronaut.runtime.server.EmbeddedServer
import io.micronaut.security.authentication.DefaultAuthentication
import io.micronaut.security.token.jwt.generator.JwtGeneratorConfigurationProperties
import spock.lang.Shared

class ProfileAuthenticationManagerSpec extends ProfileTestDataProvider {

    @Shared
    private EmbeddedServer embeddedServer = ApplicationContext.run(EmbeddedServer)

    private IPasswordHashGenerator passwordHashGenerator;
    private IRefreshTokenRepository refreshTokenRepository;
    private JwtGeneratorConfigurationProperties jwtConfigurationProperties;

    private IProfileAuthenticationManager authenticationManager

    void setup() {
        passwordHashGenerator = Mock()
        embeddedServer.applicationContext.registerSingleton(passwordHashGenerator)

        refreshTokenRepository = Mock()
        embeddedServer.applicationContext.registerSingleton(refreshTokenRepository)

        jwtConfigurationProperties = Mock()
        embeddedServer.applicationContext.registerSingleton(jwtConfigurationProperties)

        authenticationManager = new ProfileAuthenticationManager(passwordHashGenerator, refreshTokenRepository, jwtConfigurationProperties)
    }

    void cleanup() {

    }

    void "verify authenticate through AuthenticationManager"() {

        given:
            def username = "username"
            def accessToken = "accessToken"
            def refreshToken = "refreshToken"
            Map<String, Object> attributes = new HashMap<>()

            List<String> roles = Arrays.asList("ROLE1", "ROLE2")
            attributes.put("roles", roles)
            attributes.put("sub", username)
            attributes.put("iat", new Date().toTimestamp().time)

            def authentication = new DefaultAuthentication(username, attributes)
            def request = new TokenValidationRequest()
            request.token = refreshToken
            request.authentication = authentication
            request.app = EApplication.IFLOW

            def refreshTokenEntity = new RefreshTokenEntity(username,
                                                            accessToken,
                                                            refreshToken,
                                                            new Date())

        when:
            def refreshTokenOptional = authenticationManager.validateAuthentication(request)

        then:
            refreshTokenOptional.isPresent()
            refreshTokenOptional.get().username == request.authentication.name
            refreshTokenOptional.get().refreshToken == request.token
            refreshTokenOptional.get().accessToken == request.token
            for(String role: roles){
                refreshTokenOptional.get().roles.contains(role)
            }

        and:
            1 * refreshTokenRepository.findByUsername(_) >>Optional.of(refreshTokenEntity)
            1 * jwtConfigurationProperties.getRefreshTokenExpiration() >> 7200

    }

}