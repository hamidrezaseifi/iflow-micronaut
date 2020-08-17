package com.pth.profile.test.services.data

import com.pth.common.edo.enums.ECompanyType
import com.pth.profile.entities.CompanyEntity
import com.pth.profile.entities.CompanyWorkflowTypeOcrSettingPresetEntity
import com.pth.profile.entities.UserEntity
import com.pth.profile.repositories.ICompanyRepository
import com.pth.profile.repositories.ICompanyWorkflowTypeOcrSettingPresetRepository
import com.pth.profile.repositories.IDepartmentRepository
import com.pth.profile.repositories.IRefreshTokenRepository
import com.pth.profile.repositories.IUserDashboardMenuRepository
import com.pth.profile.repositories.IUserGroupRepository
import com.pth.profile.repositories.IUserRepository
import com.pth.profile.services.data.ICompanyService
import com.pth.profile.services.data.IUsersService
import com.pth.profile.services.data.impl.CompanyService
import com.pth.profile.services.data.impl.UsersService
import com.pth.profile.test.ProfileTestDataProvider
import io.micronaut.context.ApplicationContext
import io.micronaut.runtime.server.EmbeddedServer

import java.util.stream.Collectors

class UserServiceSpec extends ProfileTestDataProvider {

    private EmbeddedServer embeddedServer = ApplicationContext.run(EmbeddedServer)

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
        embeddedServer.applicationContext.registerSingleton(companyRepository)

        workflowTypeOcrSettingPresetRepository = Mock()
        embeddedServer.applicationContext.registerSingleton(workflowTypeOcrSettingPresetRepository)

        userRepository = Mock()
        embeddedServer.applicationContext.registerSingleton(userRepository)

        userGroupRepository = Mock()
        embeddedServer.applicationContext.registerSingleton(userGroupRepository)

        departmentRepository = Mock()
        embeddedServer.applicationContext.registerSingleton(departmentRepository)

        refreshTokenRepository = Mock()
        embeddedServer.applicationContext.registerSingleton(refreshTokenRepository)

        userDashboardMenuRepository = Mock()
        embeddedServer.applicationContext.registerSingleton(userDashboardMenuRepository)

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

    void "verify new created company can be saved and result is equals original company"() {

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