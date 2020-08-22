package com.pth.profile.test.services.data

import com.pth.profile.authentication.entities.RefreshTokenEntity
import com.pth.profile.entities.CompanyWorkflowTypeOcrSettingPresetEntity
import com.pth.profile.entities.UserDashboardMenuEntity
import com.pth.profile.entities.UserEntity
import com.pth.profile.repositories.ICompanyRepository
import com.pth.profile.repositories.ICompanyWorkflowTypeOcrSettingPresetRepository
import com.pth.profile.repositories.IDepartmentRepository
import com.pth.profile.repositories.IRefreshTokenRepository
import com.pth.profile.repositories.IUserDashboardMenuRepository
import com.pth.profile.repositories.IUserGroupRepository
import com.pth.profile.repositories.IUserRepository
import com.pth.profile.services.data.IUsersService
import com.pth.profile.services.data.impl.UsersService
import com.pth.profile.test.ProfileTestDataProvider
import io.micronaut.context.ApplicationContext
import io.micronaut.runtime.server.EmbeddedServer
import spock.lang.Shared


class UserServiceSpec extends ProfileTestDataProvider {

    private IUserRepository userRepository;
    private ICompanyRepository companyRepository;
    private IUserGroupRepository userGroupRepository;
    private IDepartmentRepository departmentRepository;
    private ICompanyWorkflowTypeOcrSettingPresetRepository workflowTypeOcrSettingPresetRepository;
    private IRefreshTokenRepository refreshTokenRepository;
    private IUserDashboardMenuRepository userDashboardMenuRepository;

    private IUsersService userService

    void setup() {
        companyRepository = Mock()

        workflowTypeOcrSettingPresetRepository = Mock()

        userRepository = Mock()

        userGroupRepository = Mock()

        departmentRepository = Mock()

        refreshTokenRepository = Mock()

        userDashboardMenuRepository = Mock()

        userService = new UsersService(userRepository,
                                       companyRepository,
                                       userGroupRepository,
                                       departmentRepository,
                                       workflowTypeOcrSettingPresetRepository,
                                       refreshTokenRepository,
                                       userDashboardMenuRepository)
    }

    void cleanup() {

    }

    void "verify new created user can be saved and result is equals original user"() {

        given:

            def userEntity = createTestUser(1)

        when:
            def userOptional = userService.save(userEntity)

        then:
            userOptional.isPresent()
            verifyUser(userOptional.get(), userEntity)
        and:
            1 * userRepository.save(_)
            1 * userRepository.getById(_) >> Optional.of(userEntity)

    }

    void "verify by calling delete user userRepository.delete will be called "() {

        given:

            def userEntity = createTestUser(1)
        when:
            userService.delete(userEntity)

        then:
            1 * userRepository.delete(_)

    }

    void "verify by calling getUserByIdentity userRepository.getByIdentity will be called "() {

        given:

            def userEntity = createTestUser(1)
        when:
            def userOptional = userService.getUserByIdentity("user-identity")

        then:
            userOptional.isPresent()
            verifyUser(userOptional.get(), userEntity)
        and:
            1 * userRepository.getByIdentity(_) >> Optional.of(userEntity)

    }

    void "verify by calling getUserByEmail userRepository.getUserByEmail will be called "() {

        given:

            def userEntity = createTestUser(1)
        when:
            def userOptional = userService.getUserByEmail("user-email")

        then:
            userOptional.isPresent()
            verifyUser(userOptional.get(), userEntity)
        and:
            1 * userRepository.getUserByEmail(_) >> Optional.of(userEntity)

    }

    void "verify by calling getProfileResponseByEmail userRepository.getUserByEmail will be called "() {

        given:
            def username = "username"
            def accessToken = "accessToken"
            def refreshToken = "refreshToken"

            def userEntity = createTestUser(1)
            def dashboardMenuList = new  ArrayList<UserDashboardMenuEntity>()
            def presetList = new ArrayList<CompanyWorkflowTypeOcrSettingPresetEntity>()
            def refreshTokenEntity = new RefreshTokenEntity(username,
                                                            accessToken,
                                                            refreshToken,
                                                            new Date())

        when:
            def profileResponseOptional = userService.getProfileResponseByEmail("app", "user-email")

        then:
            profileResponseOptional.isPresent()
            profileResponseOptional.get().token == refreshToken
            verifyUser(profileResponseOptional.get().user, userEntity)


        and:
            1 * userRepository.getUserByEmail(_) >> Optional.of(userEntity)
            1 * refreshTokenRepository.findByUsername(_) >> Optional.of(refreshTokenEntity)
            1 * workflowTypeOcrSettingPresetRepository.getByCompanyId(_) >> presetList
            1 * userDashboardMenuRepository.getByUserId(_, _) >> dashboardMenuList

    }

    void "verify by calling getProfileResponseByIdentity userRepository.getUserByEmail will be called "() {

        given:
            def username = "username"
            def accessToken = "accessToken"
            def refreshToken = "refreshToken"

            def userEntity = createTestUser(1)
            def dashboardMenuList = new  ArrayList<UserDashboardMenuEntity>()
            def presetList = new ArrayList<CompanyWorkflowTypeOcrSettingPresetEntity>()
            def refreshTokenEntity = new RefreshTokenEntity(username,
                                                            accessToken,
                                                            refreshToken,
                                                            new Date())

        when:
            def profileResponseOptional = userService.getProfileResponseByIdentity("app", "user-identity")

        then:
            profileResponseOptional.isPresent()
            profileResponseOptional.get().token == refreshToken
            verifyUser(profileResponseOptional.get().user, userEntity)


        and:
            1 * userRepository.getByIdentity(_) >> Optional.of(userEntity)
            1 * refreshTokenRepository.findByUsername(_) >> Optional.of(refreshTokenEntity)
            1 * workflowTypeOcrSettingPresetRepository.getByCompanyId(_) >> presetList
            1 * userDashboardMenuRepository.getByUserId(_, _) >> dashboardMenuList

    }

    void "verify by calling getUserGroups "() {

        given:
            def userEntity = createTestUser(1)
            userEntity.addUserGroup(createTestUserGroupEntity(1))
            userEntity.addUserGroup(createTestUserGroupEntity(2))


        when:
            def groupList = userService.getUserGroups("user-identity")

        then:
            groupList.isEmpty() == false
            groupList.size() == 2

        and:
            1 * userRepository.getByIdentity(_) >> Optional.of(userEntity)

    }

    void "verify by calling getUserDepartments "() {

        given:
            def userEntity = createTestUser(1)
            userEntity.addUserDepartment(createTestDepartments(1), 1)
            userEntity.addUserDepartment(createTestDepartments(2), 2)


        when:
            def departmentList = userService.getUserDepartments("user-identity")

        then:
            departmentList.isEmpty() == false
            departmentList.size() == 2

        and:
            1 * userRepository.getByIdentity(_) >> Optional.of(userEntity)

    }

    void "verify by calling getUserDeputies "() {

        given:
            def userEntity = createTestUser(1)
            userEntity.addDeputy(createTestUser(2))
            userEntity.addDeputy(createTestUser(3))


        when:
            def deputyList = userService.getUserDeputies("user-identity")

        then:
            deputyList.isEmpty() == false
            deputyList.size() == 2

        and:
            1 * userRepository.getByIdentity(_) >> Optional.of(userEntity)

    }

    void "verify by calling getCompanyUsers "() {

        given:
            def companyEntity = createTestCompany(1)

        when:
            def userList = userService.getCompanyUsers("company-identity")

        then:
            userList.isEmpty() == false
            userList.size() == 3

        and:
            1 * companyRepository.getByIdentity(_) >> Optional.of(companyEntity)
            1 * userRepository.getUserListByCompanyId(_) >> Arrays.asList(createTestUser(1),
                                                                          createTestUser(2),
                                                                          createTestUser(3))
    }

    void "verify by calling getAllUserIdentityListByDepartmentIdentity "() {

        given:

        when:
            def userList = userService.getAllUserIdentityListByDepartmentIdentity("department-identity")

        then:
            userList.isEmpty() == false
            userList.size() == 3

        and:
            1 * userRepository.getUserListByDepartmentIdentity(_) >> Arrays.asList(createTestUser(1),
                                                                                 createTestUser(2),
                                                                                 createTestUser(3))
    }

    void "verify by calling getUserListByIdentityList "() {

        given:
            Set<String> identityList = new HashSet<>()
            identityList.add("identity-1")
            identityList.add("identity-2")
            identityList.add("identity-3")
        when:
            def userList = userService.getUserListByIdentityList(identityList)

        then:
            userList.isEmpty() == false
            userList.size() == 3

        and:
            1 * userRepository.getUserListByIdentityList(_) >> Arrays.asList(createTestUser(1),
                                                                             createTestUser(2),
                                                                             createTestUser(3))
    }

    void "verify by calling getUserDashboardMenuListByUserIdentity"() {

        given:
            def userEntity = createTestUser(1)
        when:
            def dashboardMenuList = userService.getUserDashboardMenuListByUserIdentity("test-app", "test-user")

        then:
            dashboardMenuList.isEmpty() == false
            dashboardMenuList.size() == 3

        and:
            1 * userRepository.getByIdentity(_) >> Optional.of(userEntity)
            1 * userDashboardMenuRepository.getByUserId(_, _) >> Arrays.asList(createTestUserDashboardMenuEntity(1),
                                                                               createTestUserDashboardMenuEntity(2),
                                                                               createTestUserDashboardMenuEntity(3))
    }

    void "verify by calling saveUserDashboardMenuListByUserIdentity"() {

        given:
            def userEntity = createTestUser(1)
            List<UserDashboardMenuEntity> userDashboardMenuEntityList = Arrays.asList(createTestUserDashboardMenuEntity(1),
                                                               createTestUserDashboardMenuEntity(2),
                                                               createTestUserDashboardMenuEntity(3))
        when:
            def dashboardMenuList = userService.saveUserDashboardMenuListByUserIdentity("test-app",
                                                                                        "test-user",
                                                                                        userDashboardMenuEntityList)

        then:
            dashboardMenuList.isEmpty() == false
            dashboardMenuList.size() == 3

        and:
            1 * userRepository.getByIdentity(_) >> Optional.of(userEntity)
            3 * userDashboardMenuRepository.save(_)
            1 * userDashboardMenuRepository.getByUserId(_, _) >> Arrays.asList(createTestUserDashboardMenuEntity(1),
                                                                               createTestUserDashboardMenuEntity(2),
                                                                               createTestUserDashboardMenuEntity(3))
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