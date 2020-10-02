package com.pth.profile.test.repositories

import com.pth.profile.entities.DepartmentEntity
import com.pth.profile.repositories.ICompanyRepository
import com.pth.profile.repositories.IDepartmentRepository
import com.pth.profile.repositories.impl.CompanyRepository
import com.pth.profile.repositories.impl.DepartmentRepository
import com.pth.profile.test.ProfileTestDataProvider
import io.micronaut.context.ApplicationContext
import io.micronaut.runtime.server.EmbeddedServer
import spock.lang.AutoCleanup
import spock.lang.Shared

class DepartmentRepositorySpec extends ProfileTestDataProvider {

    @Shared
    @AutoCleanup
    private EmbeddedServer embeddedServer = ApplicationContext.run(EmbeddedServer, "testjpa")

    private ICompanyRepository companyRepository

    private IDepartmentRepository departmentRepository

    void setup() {
        companyRepository = embeddedServer.applicationContext.createBean(CompanyRepository)
        departmentRepository = embeddedServer.applicationContext.createBean(DepartmentRepository)

        if(testCompany == null){
            createTestCompanyEntity(companyRepository)
        }


    }

    void cleanup() {
        def list = departmentRepository.getAll()

        for (DepartmentEntity entity : list) {
            departmentRepository.delete(entity)
        }

    }

    void "verify new created department can be queried by id and equals original department"() {

        given:
            def departmentEntity = new DepartmentEntity()
            departmentEntity.title = "test-department"
            departmentEntity.companyId = testCompanyId
            def identity = generateRandomString(15)
            departmentEntity.identity = identity

        when:
            departmentRepository.save(departmentEntity)
            def departmentOptional = departmentRepository.getById(departmentEntity.id)

        then:
            departmentOptional.isPresent()

            verifyDepartment(departmentOptional.get(), departmentEntity)

    }

    void "verify edited user can be queried by id and equals original user"() {

        given:
            def departmentEntity = new DepartmentEntity()
            departmentEntity.title = "test-department"
            departmentEntity.companyId = testCompanyId
            def identity = generateRandomString(15)
            departmentEntity.identity = identity
            departmentRepository.save(departmentEntity)
            def departmentOptional = departmentRepository.getById(departmentEntity.id)
            def departmentSaved =  departmentOptional.get()
            departmentSaved.title = "test-department_edited"


        when:
            departmentRepository.update(departmentSaved)
            departmentOptional = departmentRepository.getById(departmentEntity.id)

        then:
            departmentOptional.isPresent()

            verifyDepartment(departmentOptional.get(), departmentSaved)

    }

    void "verify deleted user can not be queried by id"() {

        given:
            def departmentEntity = new DepartmentEntity()
            departmentEntity.title = "test-department"
            departmentEntity.companyId = testCompanyId
            def identity = generateRandomString(15)
            departmentEntity.identity = identity
            departmentRepository.save(departmentEntity)

        when:
            departmentRepository.delete(departmentEntity)
            def departmentOptional = departmentRepository.getById(departmentEntity.id)

        then:
            departmentOptional.isPresent() == false

    }

    void "verify new created user list can be queried by get-all and equals original users"() {

        given:
        Map<UUID, DepartmentEntity> map = new HashMap<>()

        for(int i=1; i<=10; i++){
            def departmentEntity = new DepartmentEntity()
            departmentEntity.title = "test-department-" + 1
            departmentEntity.companyId = testCompanyId
            def identity = generateRandomString(15)
            departmentEntity.identity = identity
            departmentRepository.save(departmentEntity)
            map.put(departmentEntity.id, departmentEntity)
        }

        when:

            def departmentList = departmentRepository.getAll()

        then:
            departmentList.isEmpty() == false
            for(DepartmentEntity entity: departmentList){
                verifyDepartment(map.get(entity.id), entity)
            }


    }



    private void verifyDepartment(DepartmentEntity testDepartmentEntity,
                                  DepartmentEntity departmentEntity) {
        testDepartmentEntity.title == departmentEntity.title
        testDepartmentEntity.companyId == departmentEntity.companyId
        testDepartmentEntity.identity == departmentEntity.identity
    }

}