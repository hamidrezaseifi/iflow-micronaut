package com.pth.profile.test.repositories

import com.pth.profile.authentication.entities.RefreshTokenEntity
import com.pth.profile.repositories.IRefreshTokenRepository
import com.pth.profile.repositories.impl.H2RefreshTokenRepository
import com.pth.profile.test.ProfileTestDataProvider
import io.micronaut.context.ApplicationContext
import io.micronaut.runtime.server.EmbeddedServer
import spock.lang.AutoCleanup
import spock.lang.Shared

class RefreshTokenRepositorySpec extends ProfileTestDataProvider {

    @Shared
    @AutoCleanup
    private EmbeddedServer embeddedServer = ApplicationContext.run(EmbeddedServer, "testjpa")

    private IRefreshTokenRepository refreshTokenRepository

    void setup() {
        refreshTokenRepository = embeddedServer.applicationContext.createBean(H2RefreshTokenRepository)

    }

    void cleanup() {
        def list = refreshTokenRepository.getAll()

        for (RefreshTokenEntity entity : list) {
            refreshTokenRepository.delete(entity)
        }

    }

    void "verify new created refreshToken can be queried by refreshToken and equals original refreshToken"() {

        given:
            def username = "username"
            def accessToken = "access-token"
            def refreshToken = "refresh-token"
            def issuedAt = new Date()

        when:
            refreshTokenRepository.save(username, accessToken, refreshToken, issuedAt)
            def tokenOptional = refreshTokenRepository.findByRefreshToken(refreshToken)

        then:
            tokenOptional.isPresent()

            verifyRefreshToken(tokenOptional.get(), username, accessToken, refreshToken, issuedAt)

    }


    void "verify updateOrCreate refreshToken create new refreshToken for new username and update it for existing username"() {

        given:
            def username = "username"
            def accessToken = "access-token"
            def refreshToken = "refresh-token"
            def issuedAt = new Date()

        when:
            refreshTokenRepository.updateOrCreate(username, accessToken, refreshToken, issuedAt)
            def tokenOptional = refreshTokenRepository.findByUsername(username)

        then:
            tokenOptional.isPresent()

            verifyRefreshToken(tokenOptional.get(), username, accessToken, refreshToken, issuedAt)

        when:
            def tokenFirst = tokenOptional.get()
            refreshTokenRepository.updateOrCreate(username, accessToken + "_updated", refreshToken + "_updated", issuedAt)
            tokenOptional = refreshTokenRepository.findByUsername(username)
            def tokenUpdated = tokenOptional.get()

        then:
            tokenOptional.isPresent()

            verifyRefreshToken(tokenOptional.get(), username, accessToken + "_updated", refreshToken + "_updated", issuedAt)

            tokenFirst.id == tokenUpdated.id

    }


    void "verify new created refreshToken can be queried by username and equals original refreshToken"() {

        given:
            def username = "username"
            def accessToken = "access-token"
            def refreshToken = "refresh-token"
            def issuedAt = new Date()

        when:
            refreshTokenRepository.save(username, accessToken, refreshToken, issuedAt)
            def tokenOptional = refreshTokenRepository.findByUsername(username)

        then:
            tokenOptional.isPresent()

        verifyRefreshToken(tokenOptional.get(), username, accessToken, refreshToken, issuedAt)

    }

    void "verify edited refreshToken can be queried by id and equals original refreshToken"() {

        given:
            def username = "username"
            def accessToken = "access-token"
            def refreshToken = "refresh-token"
            def issuedAt = new Date()
            refreshTokenRepository.save(username, accessToken, refreshToken, issuedAt)
            def tokenOptional = refreshTokenRepository.findByUsername(username)
            def tokenSaved =  tokenOptional.get()
            tokenSaved.accessToken = "access_edited"
            tokenSaved.refreshToken = "refresh_edited"
            tokenSaved.issuedAt = new Date()


        when:
            refreshTokenRepository.update(tokenSaved)
            tokenOptional = refreshTokenRepository.findByUsername(username)

        then:
            tokenOptional.isPresent()

            verifyRefreshToken(tokenOptional.get(),
                                tokenSaved.username,
                                tokenSaved.accessToken,
                                tokenSaved.refreshToken,
                                tokenSaved.issuedAt)

    }

    void "verify deleted refreshToken can not be queried by username"() {

        given:
            def username = "username"
            def accessToken = "access-token"
            def refreshToken = "refresh-token"
            def issuedAt = new Date()
            refreshTokenRepository.save(username, accessToken, refreshToken, issuedAt)
            def tokenOptional = refreshTokenRepository.findByUsername(username)
            def tokenEntity = tokenOptional.get()

        when:
            refreshTokenRepository.delete(tokenEntity)
            tokenOptional = refreshTokenRepository.findByUsername(username)

        then:
            tokenOptional.isPresent() == false

    }


    private void verifyRefreshToken(RefreshTokenEntity testTokenEntity,
                                    String username,
                                    String accessToken,
                                    String refreshToken,
                                    Date issuedAt) {
        testTokenEntity.username == username
        testTokenEntity.accessToken == accessToken
        testTokenEntity.refreshToken == refreshToken
        testTokenEntity.issuedAt == issuedAt
    }

}