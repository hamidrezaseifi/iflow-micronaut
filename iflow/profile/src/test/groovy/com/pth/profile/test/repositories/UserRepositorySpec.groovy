import com.pth.common.edo.enums.ECompanyType
import com.pth.profile.entities.CompanyEntity
import com.pth.profile.entities.UserEntity
import com.pth.profile.repositories.ICompanyRepository
import com.pth.profile.repositories.IUserRepository
import com.pth.profile.repositories.impl.CompanyRepository
import com.pth.profile.repositories.impl.UserRepository
import com.pth.profile.test.ProfileTestDataProvider
import io.micronaut.context.ApplicationContext
import io.micronaut.runtime.server.EmbeddedServer
import spock.lang.AutoCleanup
import spock.lang.Shared
import spock.lang.Specification

import java.time.LocalDateTime
import java.time.Month

class UserRepositorySpec extends ProfileTestDataProvider {

    @Shared
    @AutoCleanup
    private EmbeddedServer embeddedServer = ApplicationContext.run(EmbeddedServer, "testjpa")

    private IUserRepository userRepository

    private ICompanyRepository companyRepository

    private List<UserEntity> searchTestList = new ArrayList<>()

    void setup() {
        userRepository = embeddedServer.applicationContext.createBean(UserRepository)
        companyRepository = embeddedServer.applicationContext.createBean(CompanyRepository)

        createTestCompany(companyRepository)
    }

    void cleanup() {
        if (searchTestList != null && !searchTestList.isEmpty()) {

            for (UserEntity entity : searchTestList) {
                userRepository.delete(entity)
            }

        }
    }

    void "verify new created user can be queried by id and equals original user"() {

        given: "is a single document"
        def userEntity = new UserEntity()
        userEntity.passwordSalt = "passwordSalt"
        userEntity.passwordHash = "passwordHash"
        userEntity.birthDate = new Date()
        userEntity.username = "username"
        userEntity.companyId = testCompanyId
        userEntity.firstName = "fname"
        def identity = generateRandomString(15)
        userEntity.identity = identity
        userEntity.lastName = "lname"
        userEntity.permission = 1

        when: "new document is saved"
            userRepository.save(userEntity)
            def userOptional = userRepository.getById(userEntity.id)

        then: "document should be accessible via id and be equal"
            userOptional.isPresent()

            verifyUser(userOptional.get(), userEntity)

    }

    private void verifyUser(UserEntity testUserEntity,
                            UserEntity userEntity) {
        testUserEntity.passwordSalt == userEntity.passwordSalt
        testUserEntity.passwordHash == userEntity.passwordHash
        testUserEntity.birthDate == userEntity.birthDate
        testUserEntity.username == userEntity.username
        testUserEntity.companyId == userEntity.companyId
        testUserEntity.firstName == userEntity.firstName
        testUserEntity.identity == userEntity.identity
        testUserEntity.lastName == userEntity.lastName
        testUserEntity.permission == userEntity.permission
    }

}