import com.pth.profile.entities.UserEntity
import com.pth.profile.repositories.ICompanyRepository
import com.pth.profile.repositories.IDepartmentRepository
import com.pth.profile.repositories.IUserRepository
import com.pth.profile.repositories.impl.CompanyRepository
import com.pth.profile.repositories.impl.DepartmentRepository
import com.pth.profile.repositories.impl.UserRepository
import com.pth.profile.test.ProfileTestDataProvider
import io.micronaut.context.ApplicationContext
import io.micronaut.runtime.server.EmbeddedServer
import spock.lang.AutoCleanup
import spock.lang.Shared

class UserRepositorySpec extends ProfileTestDataProvider {

    @Shared
    @AutoCleanup
    private EmbeddedServer embeddedServer = ApplicationContext.run(EmbeddedServer, "testjpa")

    private IUserRepository userRepository

    private ICompanyRepository companyRepository

    private IDepartmentRepository departmentRepository

    void setup() {
        userRepository = embeddedServer.applicationContext.createBean(UserRepository)
        companyRepository = embeddedServer.applicationContext.createBean(CompanyRepository)
        departmentRepository = embeddedServer.applicationContext.createBean(DepartmentRepository)

        if(testCompany == null){
            createTestCompany(companyRepository)
        }

        if(testDepartment1 == null){
            createTestDepartments(departmentRepository)
        }



    }

    void cleanup() {
        def list = userRepository.getAll()

        for (UserEntity entity : list) {
            userRepository.delete(entity)
        }

    }

    void "verify new created user can be queried by id and equals original user"() {

        given:
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

        when:
            userRepository.save(userEntity)
            def userOptional = userRepository.getById(userEntity.id)

        then:
            userOptional.isPresent()

            verifyUser(userOptional.get(), userEntity)

    }

    void "verify edited user can be queried by id and equals original user"() {

        given:
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
        userRepository.save(userEntity)
        def userOptional = userRepository.getById(userEntity.id)
        def userSaved =  userOptional.get()
        userSaved.username = "username_edited"
        userSaved.firstName = "username_edited"
        userSaved.lastName = "username_edited"

        when:
        userRepository.update(userSaved)
        userOptional = userRepository.getById(userEntity.id)

        then:
        userOptional.isPresent()

        verifyUser(userOptional.get(), userSaved)

    }

    void "verify deleted user can not be queried by id"() {

        given:
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
            userRepository.save(userEntity)

        when:
            userRepository.delete(userEntity)
            def userOptional = userRepository.getById(userEntity.id)

        then:
            userOptional.isPresent() == false

    }

    void "verify new created user list can be queried by get-all and equals original users"() {

        given:
        Map<UUID, UserEntity> map = new HashMap<>()

        for(int i=1; i<=10; i++){
            def userEntity = new UserEntity()
            userEntity.passwordSalt = "passwordSalt" + i
            userEntity.passwordHash = "passwordHash" + i
            userEntity.birthDate = new Date()
            userEntity.username = "username" + i
            userEntity.companyId = testCompanyId
            userEntity.firstName = "fname" + i
            def identity = generateRandomString(15)
            userEntity.identity = identity
            userEntity.lastName = "lname" + i
            userEntity.permission = 1
            userRepository.save(userEntity)
            map.put(userEntity.id, userEntity)
        }

        when:

            def userList = userRepository.getAll()

        then:
            userList.isEmpty() == false
            for(UserEntity entity: userList){
                verifyUser(map.get(entity.id), entity)
            }


    }

    void "verify new created user can be queried by username and equals original user"() {

        given:
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

        when:
        userRepository.save(userEntity)
        def userOptional = userRepository.getByUsername(userEntity.username)

        then:
        userOptional.isPresent()

        verifyUser(userOptional.get(), userEntity)

    }

    void "verify new created user can be queried by identity and equals original user"() {

        given:
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

        when:
        userRepository.save(userEntity)
        def userOptional = userRepository.getByIdentity(userEntity.identity)

        then:
        userOptional.isPresent()

        verifyUser(userOptional.get(), userEntity)

    }

    void "verify new created user can be queried by email and equals original user"() {

        given:
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
        userEntity.email = "useremail@domain.de"

        when:
        userRepository.save(userEntity)
        def userOptional = userRepository.getUserByUsername(userEntity.email)

        then:
        userOptional.isPresent()

        verifyUser(userOptional.get(), userEntity)

    }

    void "verify new created user list can be queried by company-id and equals original users"() {

        given:
        Map<UUID, UserEntity> map = new HashMap<>()

        for(int i=1; i<=10; i++){
            def userEntity = new UserEntity()
            userEntity.passwordSalt = "passwordSalt" + i
            userEntity.passwordHash = "passwordHash" + i
            userEntity.birthDate = new Date()
            userEntity.username = "username" + i
            userEntity.companyId = testCompanyId
            userEntity.firstName = "fname" + i
            def identity = generateRandomString(15)
            userEntity.identity = identity
            userEntity.lastName = "lname" + i
            userEntity.permission = 1
            userRepository.save(userEntity)
            map.put(userEntity.id, userEntity)
        }

        when:

        def userList = userRepository.getUserListByCompanyId(testCompanyId)

        then:
        userList.isEmpty() == false
        for(UserEntity entity: userList){
            verifyUser(map.get(entity.id), entity)
        }


    }

    void "verify user list can be queried by department-id and equals original users"() {

        given:
        Map<UUID, UserEntity> map = new HashMap<>()

        for(int i=1; i<=10; i++){
            def userEntity = new UserEntity()
            userEntity.passwordSalt = "passwordSalt" + i
            userEntity.passwordHash = "passwordHash" + i
            userEntity.birthDate = new Date()
            userEntity.username = "username" + i
            userEntity.companyId = testCompanyId
            userEntity.firstName = "fname" + i
            def identity = generateRandomString(15)
            userEntity.identity = identity
            userEntity.lastName = "lname" + i
            userEntity.permission = 1
            userEntity.addUserDepartment(testDepartment1, 1)
            userRepository.save(userEntity)
            map.put(userEntity.id, userEntity)
        }

        when:

            def userList = userRepository.getUserListByCompanyId(testCompanyId)
            userList.size() == map.size()
        then:
            userList.isEmpty() == false
            for(UserEntity entity: userList){
                verifyUser(map.get(entity.id), entity)
            }

    }

    void "verify user list can be queried by getUserListByIdentityList and equals original users"() {

        given:
        Map<String, UserEntity> map = new HashMap<>()

        for(int i=1; i<=10; i++){
            def userEntity = new UserEntity()
            userEntity.passwordSalt = "passwordSalt" + i
            userEntity.passwordHash = "passwordHash" + i
            userEntity.birthDate = new Date()
            userEntity.username = "username" + i
            userEntity.companyId = testCompanyId
            userEntity.firstName = "fname" + i
            def identity = generateRandomString(15)
            userEntity.identity = identity
            userEntity.lastName = "lname" + i
            userEntity.permission = 1
            userEntity.addUserDepartment(testDepartment1, 1)
            userRepository.save(userEntity)
            map.put(userEntity.identity, userEntity)
        }

        when:

            def userList = userRepository.getUserListByIdentityList(map.keySet())
            userList.size() == map.size()
        then:
            userList.isEmpty() == false
            for(UserEntity entity: userList){
                verifyUser(map.get(entity.identity), entity)
            }

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