package com.pth.profile.test.services.data

import com.pth.profile.entities.UserGroupEntity
import com.pth.profile.repositories.IUserGroupRepository
import com.pth.profile.services.data.IUserGroupService
import com.pth.profile.services.data.impl.UserGroupService
import com.pth.profile.test.ProfileTestDataProvider
import io.micronaut.context.ApplicationContext
import io.micronaut.runtime.server.EmbeddedServer
import spock.lang.Shared

import java.util.stream.Collectors

class UserGroupServiceSpec extends ProfileTestDataProvider {

    private IUserGroupRepository userGroupRepository

    private IUserGroupService userGroupService

    void setup() {

        userGroupRepository = Mock()

        userGroupService = new UserGroupService(userGroupRepository)
    }

    void cleanup() {

    }

    void "verify new created user-group can be saved and result is equals original user-group"() {

        given:

            def userGroupEntity = new UserGroupEntity()
            userGroupEntity.title = "Test-Company" + generateRandomString(5)
            userGroupEntity.identity = generateRandomString(15)
            userGroupEntity.companyId = testCompanyId
            userGroupEntity.status = 1

        when:
            def companyOptional = userGroupService.save(userGroupEntity)

        then:
            companyOptional.isPresent()
            verifyUserGroup(companyOptional.get(), userGroupEntity)
        and:
            1 * userGroupRepository.save(_)
            1 * userGroupRepository.getById(_) >> Optional.of(userGroupEntity)

    }

    void "verify user-group can queried by identity"() {

        given:

            def userGroupEntity = new UserGroupEntity()
            userGroupEntity.title = "Test-Company" + generateRandomString(5)
            userGroupEntity.identity = generateRandomString(15)
            userGroupEntity.companyId = testCompanyId
            userGroupEntity.status = 1

        when:
            def companyOptional = userGroupService.getById(UUID.randomUUID())

        then:
            companyOptional.isPresent()
            verifyUserGroup(companyOptional.get(), userGroupEntity)
        and:
            1 * userGroupRepository.getById(_) >> Optional.of(userGroupEntity)

    }

    void "verify new created user-group can be deleted"() {

        given:

        def userGroupEntity = new UserGroupEntity()
        userGroupEntity.title = "Test-Company" + generateRandomString(5)
        userGroupEntity.identity = generateRandomString(15)
        userGroupEntity.companyId = testCompanyId
        userGroupEntity.status = 1

        when:
            userGroupService.delete(userGroupEntity)

        then:
            1 * userGroupRepository.delete(_)

    }

    void "verify call getListByIdentityList"() {

        given:

            def userGroupList = new HashMap<UUID, UserGroupEntity>();
            
            for(int i=1; i<=3; i++){
                def userGroupEntity = new UserGroupEntity()
                userGroupEntity.title = "Test-Company" + generateRandomString(5)
                userGroupEntity.identity = generateRandomString(15)
                userGroupEntity.companyId = testCompanyId
                userGroupEntity.status = 1
                userGroupList.put(userGroupEntity.id, userGroupEntity)
            }
            

        when:
            def resultuserGroupList = userGroupService.getListByIdentityList(Arrays.asList("identity1", "identity2", "identity3"))

        then:
            resultuserGroupList != null
            resultuserGroupList.size() == userGroupList.size()
            for(def entity: resultuserGroupList){
                verifyUserGroup(entity, userGroupList.get(entity.id))
            }
        and:
            1 * userGroupRepository.getListByIdentityList(_) >> userGroupList.values().stream().collect(Collectors.toList())

    }

    void "verify call getListByIdCompanyIdentity"() {

        given:

            def userGroupList = new HashMap<UUID, UserGroupEntity>();

            for(int i=1; i<=3; i++){
                def userGroupEntity = new UserGroupEntity()
                userGroupEntity.title = "Test-Company" + generateRandomString(5)
                userGroupEntity.identity = generateRandomString(15)
                userGroupEntity.companyId = testCompanyId
                userGroupEntity.status = 1
                userGroupList.put(userGroupEntity.id, userGroupEntity)
            }


        when:
            def resultuserGroupList = userGroupService.getListByIdCompanyId(UUID.randomUUID())

            then:
            resultuserGroupList != null
            resultuserGroupList.size() == userGroupList.size()
            for(def entity: resultuserGroupList){
                verifyUserGroup(entity, userGroupList.get(entity.id))
            }
        and:
            1 * userGroupRepository.getListByIdCompanyId(_) >> userGroupList.values().stream().collect(Collectors.toList())

    }


    private void verifyUserGroup(UserGroupEntity testUserGroupEntity,
                                 UserGroupEntity refrenceUserGroupEntity) {
        testUserGroupEntity.title == refrenceUserGroupEntity.title
        testUserGroupEntity.identity == refrenceUserGroupEntity.identity
        testUserGroupEntity.companyId == refrenceUserGroupEntity.companyId
        testUserGroupEntity.status == refrenceUserGroupEntity.status

    }

}