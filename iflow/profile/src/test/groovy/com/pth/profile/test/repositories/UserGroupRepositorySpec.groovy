package com.pth.profile.test.repositories


import com.pth.profile.entities.UserGroupEntity
import com.pth.profile.repositories.ICompanyRepository
import com.pth.profile.repositories.IUserGroupRepository
import com.pth.profile.repositories.impl.CompanyRepository
import com.pth.profile.repositories.impl.UserGroupRepository
import com.pth.profile.test.ProfileTestDataProvider
import io.micronaut.context.ApplicationContext
import io.micronaut.runtime.server.EmbeddedServer
import spock.lang.AutoCleanup
import spock.lang.Shared

class UserGroupRepositorySpec extends ProfileTestDataProvider {

    @Shared
    @AutoCleanup
    private EmbeddedServer embeddedServer = ApplicationContext.run(EmbeddedServer, "testjpa")

    private ICompanyRepository companyRepository

    private IUserGroupRepository userGroupRepository

    void setup() {
        companyRepository = embeddedServer.applicationContext.createBean(CompanyRepository)
        userGroupRepository = embeddedServer.applicationContext.createBean(UserGroupRepository)

        if(testCompany == null){
            createTestCompany(companyRepository)
        }


    }

    void cleanup() {
        def list = userGroupRepository.getAll()

        for (UserGroupEntity entity : list) {
            userGroupRepository.delete(entity)
        }

    }

    void "verify new created userGroup can be queried by id and equals original userGroup"() {

        given:
            def userGroupEntity = new UserGroupEntity()
            userGroupEntity.title = "test-userGroup"
            userGroupEntity.companyId = testCompanyId
            def identity = generateRandomString(15)
            userGroupEntity.identity = identity

        when:
            userGroupRepository.save(userGroupEntity)
            def userGroupOptional = userGroupRepository.getById(userGroupEntity.id)

        then:
            userGroupOptional.isPresent()

            verifyUserGroup(userGroupOptional.get(), userGroupEntity)

    }

    void "verify edited user can be queried by id and equals original user"() {

        given:
            def userGroupEntity = new UserGroupEntity()
            userGroupEntity.title = "test-userGroup"
            userGroupEntity.companyId = testCompanyId
            def identity = generateRandomString(15)
            userGroupEntity.identity = identity
            userGroupRepository.save(userGroupEntity)
            def userGroupOptional = userGroupRepository.getById(userGroupEntity.id)
            def userGroupSaved =  userGroupOptional.get()
            userGroupSaved.title = "test-userGroup_edited"


        when:
            userGroupRepository.update(userGroupSaved)
            userGroupOptional = userGroupRepository.getById(userGroupEntity.id)

        then:
            userGroupOptional.isPresent()

            verifyUserGroup(userGroupOptional.get(), userGroupSaved)

    }

    void "verify deleted user can not be queried by id"() {

        given:
            def userGroupEntity = new UserGroupEntity()
            userGroupEntity.title = "test-userGroup"
            userGroupEntity.companyId = testCompanyId
            def identity = generateRandomString(15)
            userGroupEntity.identity = identity
            userGroupRepository.save(userGroupEntity)

        when:
            userGroupRepository.delete(userGroupEntity)
            def userGroupOptional = userGroupRepository.getById(userGroupEntity.id)

        then:
            userGroupOptional.isPresent() == false

    }

    void "verify new created user list can be queried by get-all and equals original users"() {

        given:
        Map<UUID, UserGroupEntity> map = new HashMap<>()

        for(int i=1; i<=10; i++){
            def userGroupEntity = new UserGroupEntity()
            userGroupEntity.title = "test-userGroup-" + 1
            userGroupEntity.companyId = testCompanyId
            def identity = generateRandomString(15)
            userGroupEntity.identity = identity
            userGroupRepository.save(userGroupEntity)
            map.put(userGroupEntity.id, userGroupEntity)
        }

        when:

            def userGroupList = userGroupRepository.getAll()

        then:
            userGroupList.isEmpty() == false
            for(UserGroupEntity entity: userGroupList){
                verifyUserGroup(map.get(entity.id), entity)
            }


    }



    private void verifyUserGroup(UserGroupEntity testDepartmentEntity,
                                 UserGroupEntity userGroupEntity) {
        testDepartmentEntity.title == userGroupEntity.title
        testDepartmentEntity.companyId == userGroupEntity.companyId
        testDepartmentEntity.identity == userGroupEntity.identity
    }

}