package com.pth.profile.test.services.data

import com.pth.common.edo.enums.EUserDepartmentMemberType
import com.pth.profile.entities.DepartmentEntity
import com.pth.profile.entities.UserEntity
import com.pth.profile.repositories.IDepartmentRepository
import com.pth.profile.repositories.IUserRepository
import com.pth.profile.services.data.IDepartmentService
import com.pth.profile.services.data.impl.DepartmentService
import com.pth.profile.test.ProfileTestDataProvider

import java.util.stream.Collectors

class DepartmentServiceSpec extends ProfileTestDataProvider {

    private IDepartmentRepository departmentRepository
    private IUserRepository userRepository

    private IDepartmentService departmentService

    void setup() {
        departmentRepository = Mock()

        userRepository = Mock()

        departmentService = new DepartmentService(departmentRepository, userRepository)
    }

    void cleanup() {

    }

    void "verify new created department can be saved and result is equals original department"() {

        given:

            def documentEntity = new DepartmentEntity()
            documentEntity.title = "Test-Company" + generateRandomString(5)
            documentEntity.identity = generateRandomString(15)
            documentEntity.companyId = testCompanyId
            documentEntity.status = 1

        when:
            def companyOptional = departmentService.update(documentEntity)

        then:
            companyOptional.isPresent()
            verifyDepartment(companyOptional.get(), documentEntity)
        and:
            1 * departmentRepository.create(_)
            1 * departmentRepository.getById(_) >> Optional.of(documentEntity)

    }

    void "verify department can queried by identity"() {

        given:

            def documentEntity = new DepartmentEntity()
            documentEntity.title = "Test-Company" + generateRandomString(5)
            documentEntity.identity = generateRandomString(15)
            documentEntity.companyId = testCompanyId
            documentEntity.status = 1

        when:
            def companyOptional = departmentService.getById(UUID.randomUUID())

        then:
            companyOptional.isPresent()
            verifyDepartment(companyOptional.get(), documentEntity)
        and:
            1 * departmentRepository.getById(_) >> Optional.of(documentEntity)

    }

    void "verify new created department can be deleted"() {

        given:

        def documentEntity = new DepartmentEntity()
        documentEntity.title = "Test-Company" + generateRandomString(5)
        documentEntity.identity = generateRandomString(15)
        documentEntity.companyId = testCompanyId
        documentEntity.status = 1

        when:
            departmentService.delete(documentEntity)

        then:
            1 * departmentRepository.delete(_)

    }

    void "verify call getListByIdentityList"() {

        given:

            def departmentList = new HashMap<UUID, DepartmentEntity>();
            
            for(int i=1; i<=3; i++){
                def documentEntity = new DepartmentEntity()
                documentEntity.title = "Test-Company" + generateRandomString(5)
                documentEntity.identity = generateRandomString(15)
                documentEntity.companyId = testCompanyId
                documentEntity.status = 1
                departmentList.put(documentEntity.id, documentEntity)
            }
            

        when:
            def resultDepartmentList = departmentService.getListByIdentityList(Arrays.asList("identity1", "identity2", "identity3"))

        then:
            resultDepartmentList != null
            resultDepartmentList.size() == departmentList.size()
            for(def entity: resultDepartmentList){
                verifyDepartment(entity, departmentList.get(entity.id))
            }
        and:
            1 * departmentRepository.getListByIdentityList(_) >> departmentList.values().stream().collect(Collectors.toList())

    }

    void "verify call getListByIdCompanyIdentity"() {

        given:

            def departmentList = new HashMap<UUID, DepartmentEntity>();

            for(int i=1; i<=3; i++){
                def documentEntity = new DepartmentEntity()
                documentEntity.title = "Test-Company" + generateRandomString(5)
                documentEntity.identity = generateRandomString(15)
                documentEntity.companyId = testCompanyId
                documentEntity.status = 1
                departmentList.put(documentEntity.id, documentEntity)
            }


        when:
            def resultDepartmentList = departmentService.getListByIdCompanyId(UUID.randomUUID())

            then:
            resultDepartmentList != null
            resultDepartmentList.size() == departmentList.size()
            for(def entity: resultDepartmentList){
                verifyDepartment(entity, departmentList.get(entity.id))
            }
        and:
            1 * departmentRepository.getListByIdCompanyId(_) >> departmentList.values().stream().collect(Collectors.toList())

    }

    void "verify call getDepartmentManager"() {

        given:

            def documentEntity = new DepartmentEntity()
            documentEntity.title = "Test-Company" + generateRandomString(5)
            documentEntity.identity = generateRandomString(15)
            documentEntity.companyId = testCompanyId
            documentEntity.status = 1

            List<UserEntity> userList = new ArrayList<>()

            def manager = null;
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
                userEntity.addUserDepartment(documentEntity,
                        i == 5 ? EUserDepartmentMemberType.MANAGER.value: EUserDepartmentMemberType.MEMBER.value)
                if(i == 5){
                    manager = userEntity
                }
                userList.add(userEntity)
            }


        when:
            def resultUserEntityOptional = departmentService.getDepartmentManager(documentEntity.id)

        then:
            resultUserEntityOptional != null
            resultUserEntityOptional.isPresent()
            resultUserEntityOptional.get() == manager
        and:
            1 * userRepository.getUserListByDepartmentId(_) >> userList

    }

    void "verify call getDepartmentManager not found"() {

        given:

            def documentEntity = new DepartmentEntity()
            documentEntity.title = "Test-Company" + generateRandomString(5)
            documentEntity.identity = generateRandomString(15)
            documentEntity.companyId = testCompanyId
            documentEntity.status = 1

            List<UserEntity> userList = new ArrayList<>()

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
                userEntity.addUserDepartment(documentEntity, EUserDepartmentMemberType.MEMBER.value)
                userList.add(userEntity)
            }


        when:
            def resultUserEntityOptional = departmentService.getDepartmentManager(documentEntity.id)

        then:
            resultUserEntityOptional != null
            resultUserEntityOptional.isPresent() == false

        and:
            1 * userRepository.getUserListByDepartmentId(_) >> userList

    }

    void "verify call getDepartmentDeputy"() {

        given:

        def documentEntity = new DepartmentEntity()
        documentEntity.title = "Test-Company" + generateRandomString(5)
        documentEntity.identity = generateRandomString(15)
        documentEntity.companyId = testCompanyId
        documentEntity.status = 1

        List<UserEntity> userList = new ArrayList<>()

        def deputy = null;
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
            userEntity.addUserDepartment(documentEntity,
                    i == 5 ? EUserDepartmentMemberType.DEPUTY.value: EUserDepartmentMemberType.MEMBER.value)
            if(i == 5){
                deputy = userEntity
            }
            userList.add(userEntity)
        }


        when:
        def resultUserEntityOptional = departmentService.getDepartmentDeputy(documentEntity.id)

        then:
        resultUserEntityOptional != null
        resultUserEntityOptional.isPresent()
        resultUserEntityOptional.get() == deputy
        and:
        1 * userRepository.getUserListByDepartmentId(_) >> userList

    }

    void "verify call getDepartmentDeputy not found"() {

        given:

            def documentEntity = new DepartmentEntity()
            documentEntity.title = "Test-Company" + generateRandomString(5)
            documentEntity.identity = generateRandomString(15)
            documentEntity.companyId = testCompanyId
            documentEntity.status = 1

            List<UserEntity> userList = new ArrayList<>()

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
                userEntity.addUserDepartment(documentEntity, EUserDepartmentMemberType.MEMBER.value)

                userList.add(userEntity)
            }


        when:
            def resultUserEntityOptional = departmentService.getDepartmentDeputy(documentEntity.id)

        then:
            resultUserEntityOptional != null
            resultUserEntityOptional.isPresent() == false

        and:
            1 * userRepository.getUserListByDepartmentId(_) >> userList

    }

    private void verifyDepartment(DepartmentEntity testDepartmentEntity,
                                  DepartmentEntity departmentEntity) {
        testDepartmentEntity.title == departmentEntity.title
        testDepartmentEntity.identity == departmentEntity.identity
        testDepartmentEntity.companyId == departmentEntity.companyId
        testDepartmentEntity.status == departmentEntity.status

    }

}