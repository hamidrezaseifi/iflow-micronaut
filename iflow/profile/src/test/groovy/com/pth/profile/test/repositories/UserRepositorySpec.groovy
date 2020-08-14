import com.pth.profile.entities.UserEntity
import com.pth.profile.repositories.IUserRepository
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

    private List<UserEntity> searchTestList = new ArrayList<>()

    void setup() {
        userRepository = embeddedServer.applicationContext.createBean(UserRepository)
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
        userEntity.setPasswordSalt("passwordSalt")
        userEntity.setPasswordHash("passwordHash")
        userEntity.setBirthDate(new Date())
        userEntity.setUsername("username")
        userEntity.setCompanyId(testCompanyId)
        userEntity.setFirstName("fname")
        def identity = generateRandomString(15)
        userEntity.setIdentity(identity)
        userEntity.setLastName("lname")
        userEntity.setPermission(1)

        when: "new document is saved"
            def userOptional = userRepository.save(userEntity)

        then: "document should be accessible via id and be equal"
            userOptional.isPresent()
            userOptional.get() == userEntity
    }

}