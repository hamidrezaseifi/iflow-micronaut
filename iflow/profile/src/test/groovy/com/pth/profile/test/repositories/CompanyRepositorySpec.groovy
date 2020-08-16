package com.pth.profile.test.repositories

import com.pth.common.edo.enums.ECompanyType
import com.pth.profile.entities.CompanyEntity
import com.pth.profile.repositories.ICompanyRepository
import com.pth.profile.repositories.impl.CompanyRepository
import com.pth.profile.test.ProfileTestDataProvider
import io.micronaut.context.ApplicationContext
import io.micronaut.runtime.server.EmbeddedServer
import spock.lang.AutoCleanup
import spock.lang.Shared

class CompanyRepositorySpec extends ProfileTestDataProvider {

    @Shared
    @AutoCleanup
    private EmbeddedServer embeddedServer = ApplicationContext.run(EmbeddedServer, "testjpa")

    private ICompanyRepository companyRepository

    private List<CompanyEntity> searchTestList = new ArrayList<>()

    void setup() {
        companyRepository = embeddedServer.applicationContext.createBean(CompanyRepository)
    }

    void cleanup() {
    }

    void "verify new created company can be queried by id and equals original company"() {

        given:

            def companyEntity = new CompanyEntity()
            companyEntity.companyName = "Test-Company" + generateRandomString(5)
            companyEntity.identity = generateRandomString(15)
            companyEntity.companyType = ECompanyType.EINZELUNTERNEHMEN.enumValue
            companyEntity.companyTypeCustome = "Test-Company"
            companyEntity.status = 1

        when:
            companyRepository.save(companyEntity)
            def companyOptional = companyRepository.getById(companyEntity.id)

        then:
            companyOptional.isPresent()

            verifyCompany(companyOptional.get(), companyEntity)

    }

    void "verify edited company can be queried by id and equals original company"() {

        given:

            def companyEntity = new CompanyEntity()
            companyEntity.companyName = "Test-Company" + generateRandomString(5)
            companyEntity.identity = generateRandomString(15)
            companyEntity.companyType = ECompanyType.EINZELUNTERNEHMEN.enumValue
            companyEntity.companyTypeCustome = "Test-Company"
            companyEntity.status = 1
            companyRepository.save(companyEntity)
            def companyOptional = companyRepository.getById(companyEntity.id)
            def editingCompanyEntity = companyOptional.get()
        when:

            editingCompanyEntity.companyName = "Test-Company-edited" + generateRandomString(5)
            editingCompanyEntity.identity = "edited-" + generateRandomString(15)
            editingCompanyEntity.companyType = ECompanyType.GBR.enumValue
            editingCompanyEntity.companyTypeCustome = "Test-Company-edited"
            companyRepository.update(editingCompanyEntity)
            companyOptional = companyRepository.getById(companyEntity.id)


        then:
            companyOptional.isPresent()

            verifyCompany(companyOptional.get(), editingCompanyEntity)

    }


    void "verify deleted company can not be queried by id"() {

        given:

            def companyEntity = new CompanyEntity()
            companyEntity.companyName = "Test-Company" + generateRandomString(5)
            companyEntity.identity = generateRandomString(15)
            companyEntity.companyType = ECompanyType.EINZELUNTERNEHMEN.enumValue
            companyEntity.companyTypeCustome = "Test-Company"
            companyEntity.status = 1
            companyRepository.save(companyEntity)
            def companyOptional = companyRepository.getById(companyEntity.id)
         when:

            companyRepository.delete(companyOptional.get())
            companyOptional = companyRepository.getById(companyEntity.id)


        then:
            companyOptional.isPresent() == false


    }

    private void verifyCompany(CompanyEntity testCompanyEntity,
                               CompanyEntity CompanyEntity) {
        testCompanyEntity.companyName == CompanyEntity.companyName
        testCompanyEntity.identity == CompanyEntity.identity
        testCompanyEntity.companyType == CompanyEntity.companyType
        testCompanyEntity.companyTypeCustome == CompanyEntity.companyTypeCustome
        testCompanyEntity.status == CompanyEntity.status

    }

}