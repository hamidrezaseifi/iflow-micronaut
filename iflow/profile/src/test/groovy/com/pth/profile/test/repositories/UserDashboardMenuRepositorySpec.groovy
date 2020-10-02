package com.pth.profile.test.repositories

import com.pth.profile.entities.UserDashboardMenuEntity
import com.pth.profile.repositories.ICompanyRepository
import com.pth.profile.repositories.IUserDashboardMenuRepository
import com.pth.profile.repositories.IUserRepository
import com.pth.profile.repositories.impl.CompanyRepository
import com.pth.profile.repositories.impl.UserDashboardMenuRepository
import com.pth.profile.repositories.impl.UserRepository
import com.pth.profile.test.ProfileTestDataProvider
import io.micronaut.context.ApplicationContext
import io.micronaut.runtime.server.EmbeddedServer
import spock.lang.AutoCleanup
import spock.lang.Shared

class UserDashboardMenuRepositorySpec extends ProfileTestDataProvider {

    @Shared
    @AutoCleanup
    private EmbeddedServer embeddedServer = ApplicationContext.run(EmbeddedServer, "testjpa")

    private ICompanyRepository companyRepository

    private IUserRepository userRepository

    private IUserDashboardMenuRepository userDashboardMenuRepository

    void setup() {
        companyRepository = embeddedServer.applicationContext.createBean(CompanyRepository)
        userDashboardMenuRepository = embeddedServer.applicationContext.createBean(UserDashboardMenuRepository)
        userRepository = embeddedServer.applicationContext.createBean(UserRepository)

        if(testCompany == null){
            createTestCompanyEntity(companyRepository)
        }
        if(testUser1 == null){
            createTestUser(userRepository)
        }


    }

    void cleanup() {
        def list = userDashboardMenuRepository.getAll()

        for (UserDashboardMenuEntity entity : list) {
            userDashboardMenuRepository.delete(entity)
        }

    }

    void "verify new created userDashboardMenu can be queried by id and equals original userDashboardMenu"() {

        given:
            def userDashboardMenuEntity = new UserDashboardMenuEntity()
            userDashboardMenuEntity.userId = testUserId1
            userDashboardMenuEntity.menuId = "menu-1"
            userDashboardMenuEntity.appId = "app-1"
            userDashboardMenuEntity.rowIndex = 1
            userDashboardMenuEntity.columnIndex = 1

        when:
            userDashboardMenuRepository.save(userDashboardMenuEntity)
            def userDashboardMenuOptional = userDashboardMenuRepository.getById(userDashboardMenuEntity.id)

        then:
            userDashboardMenuOptional.isPresent()

            verifyUserDashboardMenu(userDashboardMenuOptional.get(), userDashboardMenuEntity)

    }

    void "verify edited user can be queried by id and equals original user"() {

        given:
            def userDashboardMenuEntity = new UserDashboardMenuEntity()
            userDashboardMenuEntity.userId = testUserId1
            userDashboardMenuEntity.menuId = "menu-1"
            userDashboardMenuEntity.appId = "app-1"
            userDashboardMenuEntity.rowIndex = 1
            userDashboardMenuEntity.columnIndex = 1
            userDashboardMenuRepository.save(userDashboardMenuEntity)
            def userDashboardMenuOptional = userDashboardMenuRepository.getById(userDashboardMenuEntity.id)
            def userDashboardMenuSaved =  userDashboardMenuOptional.get()
            userDashboardMenuSaved.menuId = "menu-edited"
            userDashboardMenuSaved.appId = "app-edited"
            userDashboardMenuSaved.rowIndex = 2
            userDashboardMenuSaved.columnIndex = 2


        when:
            userDashboardMenuRepository.update(userDashboardMenuSaved)
            userDashboardMenuOptional = userDashboardMenuRepository.getById(userDashboardMenuEntity.id)

        then:
            userDashboardMenuOptional.isPresent()

            verifyUserDashboardMenu(userDashboardMenuOptional.get(), userDashboardMenuSaved)

    }

    void "verify deleted user can not be queried by id"() {

        given:
            def userDashboardMenuEntity = new UserDashboardMenuEntity()
            userDashboardMenuEntity.userId = testUserId1
            userDashboardMenuEntity.menuId = "menu-1"
            userDashboardMenuEntity.appId = "app-1"
            userDashboardMenuEntity.rowIndex = 1
            userDashboardMenuEntity.columnIndex = 1
            userDashboardMenuRepository.save(userDashboardMenuEntity)

        when:
            userDashboardMenuRepository.delete(userDashboardMenuEntity)
            def userDashboardMenuOptional = userDashboardMenuRepository.getById(userDashboardMenuEntity.id)

        then:
            userDashboardMenuOptional.isPresent() == false

    }

    void "verify new created user list can be queried by get-all and equals original users"() {

        given:
        Map<UUID, UserDashboardMenuEntity> map = new HashMap<>()

        for(int i=1; i<=10; i++){
            def userDashboardMenuEntity = new UserDashboardMenuEntity()
            userDashboardMenuEntity.userId = testUserId1
            userDashboardMenuEntity.menuId = "menu-" + i
            userDashboardMenuEntity.appId = "app-1"
            userDashboardMenuEntity.rowIndex = i
            userDashboardMenuEntity.columnIndex = i
            userDashboardMenuRepository.save(userDashboardMenuEntity)
            map.put(userDashboardMenuEntity.id, userDashboardMenuEntity)
        }

        when:

            def userDashboardMenuList = userDashboardMenuRepository.getAll()

        then:
            userDashboardMenuList.isEmpty() == false
            for(UserDashboardMenuEntity entity: userDashboardMenuList){
                verifyUserDashboardMenu(map.get(entity.id), entity)
            }


    }



    private void verifyUserDashboardMenu(UserDashboardMenuEntity testUserDashboardMenuEntity,
                                         UserDashboardMenuEntity userDashboardMenuEntity) {
        testUserDashboardMenuEntity.userId == userDashboardMenuEntity.userId
        testUserDashboardMenuEntity.menuId == userDashboardMenuEntity.menuId
        testUserDashboardMenuEntity.appId == userDashboardMenuEntity.appId
        testUserDashboardMenuEntity.rowIndex == userDashboardMenuEntity.rowIndex
        testUserDashboardMenuEntity.columnIndex == userDashboardMenuEntity.columnIndex
    }

}