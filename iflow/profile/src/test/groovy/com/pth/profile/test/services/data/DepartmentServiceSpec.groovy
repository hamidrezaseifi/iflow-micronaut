package com.pth.profile.test.services.data

import com.pth.common.edo.enums.ECompanyType
import com.pth.profile.entities.DepartmentEntity
import com.pth.profile.entities.CompanyWorkflowTypeOcrSettingPresetEntity
import com.pth.profile.repositories.ICompanyRepository
import com.pth.profile.repositories.ICompanyWorkflowTypeOcrSettingPresetRepository
import com.pth.profile.repositories.IDepartmentRepository
import com.pth.profile.repositories.IUserRepository
import com.pth.profile.services.data.ICompanyService
import com.pth.profile.services.data.IDepartmentService
import com.pth.profile.services.data.impl.CompanyService
import com.pth.profile.services.data.impl.DepartmentService
import com.pth.profile.test.ProfileTestDataProvider
import io.micronaut.context.ApplicationContext
import io.micronaut.runtime.server.EmbeddedServer
import spock.lang.Shared

import java.util.stream.Collectors

class DepartmentServiceSpec extends ProfileTestDataProvider {

    @Shared
    private EmbeddedServer embeddedServer = ApplicationContext.run(EmbeddedServer)

    IDepartmentRepository departmentRepository
    IUserRepository userRepository

    private IDepartmentService departmentService

    void setup() {
        departmentRepository = Mock()
        embeddedServer.applicationContext.registerSingleton(departmentRepository)

        userRepository = Mock()
        embeddedServer.applicationContext.registerSingleton(userRepository)

        departmentService = new DepartmentService(departmentRepository, userRepository)
    }

    void cleanup() {

    }

    void "verify new created company can be saved and result is equals original company"() {

        given:

            def departmentEntity = new DepartmentEntity()
            departmentEntity.title = "Test-Department" + generateRandomString(5)
            departmentEntity.identity = generateRandomString(15)
            departmentEntity.companyId = testCompanyId
            departmentEntity.status = 1

        when:
            def departmentOptional = departmentService.save(departmentEntity)

        then:
            departmentOptional.isPresent()
            verifyCompany(departmentOptional.get(), departmentEntity)
        and:
            1 * departmentRepository.save(_)
            1 * departmentRepository.getById(_) >> Optional.of(departmentEntity)

    }

    void "verify company can queried by identity"() {

        given:

            def departmentEntity = new DepartmentEntity()
            departmentEntity.title = "Test-Department" + generateRandomString(5)
            departmentEntity.identity = generateRandomString(15)
            departmentEntity.companyId = testCompanyId
            departmentEntity.status = 1

        when:
            def departmentOptional = departmentService.getByIdentity("test identity")

        then:
            departmentOptional.isPresent()
            verifyCompany(departmentOptional.get(), departmentEntity)
        and:
            1 * departmentRepository.getByIdentity(_) >> Optional.of(departmentEntity)

    }


    private void verifyCompany(DepartmentEntity testDepartmentEntity,
                               DepartmentEntity departmentEntity) {
        testDepartmentEntity.title == departmentEntity.title
        testDepartmentEntity.identity == departmentEntity.identity
        testDepartmentEntity.companyId == departmentEntity.companyId
        testDepartmentEntity.status == departmentEntity.status

    }

}