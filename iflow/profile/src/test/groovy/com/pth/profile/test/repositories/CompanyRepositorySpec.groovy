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

    void setup() {
        companyRepository = embeddedServer.applicationContext.createBean(CompanyRepository)
    }

    void cleanup() {
        def companyList = companyRepository.getAll()
        for(def entity: companyList){
            companyRepository.delete(entity)
        }
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
            companyRepository.create(companyEntity)
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
            companyRepository.create(companyEntity)
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
            companyRepository.create(companyEntity)
            def companyOptional = companyRepository.getById(companyEntity.id)
         when:

            companyRepository.delete(companyOptional.get())
            companyOptional = companyRepository.getById(companyEntity.id)


        then:
            companyOptional.isPresent() == false


    }


    void "verify list new created company can be queried by get-all and equals original company"() {

        given:

            Map<UUID, CompanyEntity> map = new HashMap<>();
            for(int i=1; i<11; i++){
                def companyEntity = new CompanyEntity()
                companyEntity.companyName = "Test-Company-" + i + "-" + generateRandomString(5)
                companyEntity.identity = generateRandomString(15)
                companyEntity.companyType = ECompanyType.EINZELUNTERNEHMEN.enumValue
                companyEntity.companyTypeCustome = "Test-Company-" + i
                companyEntity.status = 1
                companyRepository.create(companyEntity)
                map.put(companyEntity.id, companyEntity)
            }

        when:

            def companyList = companyRepository.getAll()


        then:
            companyList.isEmpty() == false
            companyList.size() == 10
            for(CompanyEntity entity: companyList){
                verifyCompany(entity, map.get(entity.id))
            }



    }

    void "verify new created company can be queried by identity and equals original company"() {

        given:

            def companyEntity = new CompanyEntity()
            companyEntity.companyName = "Test-Company" + generateRandomString(5)
            companyEntity.identity = generateRandomString(15)
            companyEntity.companyType = ECompanyType.EINZELUNTERNEHMEN.enumValue
            companyEntity.companyTypeCustome = "Test-Company"
            companyEntity.status = 1

        when:
            companyRepository.create(companyEntity)
            def companyOptional = companyRepository.getByIdentity(companyEntity.identity)

        then:
            companyOptional.isPresent()

            verifyCompany(companyOptional.get(), companyEntity)

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