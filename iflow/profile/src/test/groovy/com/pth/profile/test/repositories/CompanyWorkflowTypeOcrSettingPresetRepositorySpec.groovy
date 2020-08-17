package com.pth.profile.test.repositories


import com.pth.profile.entities.CompanyWorkflowTypeOcrSettingPresetEntity
import com.pth.profile.repositories.ICompanyRepository
import com.pth.profile.repositories.ICompanyWorkflowTypeOcrSettingPresetRepository
import com.pth.profile.repositories.impl.CompanyRepository
import com.pth.profile.repositories.impl.CompanyWorkflowTypeOcrSettingPresetRepository
import com.pth.profile.test.ProfileTestDataProvider
import io.micronaut.context.ApplicationContext
import io.micronaut.runtime.server.EmbeddedServer
import spock.lang.AutoCleanup
import spock.lang.Shared

class CompanyWorkflowTypeOcrSettingPresetRepositorySpec extends ProfileTestDataProvider {

    @Shared
    @AutoCleanup
    private EmbeddedServer embeddedServer = ApplicationContext.run(EmbeddedServer, "testjpa")

    private ICompanyRepository companyRepository

    private ICompanyWorkflowTypeOcrSettingPresetRepository presetRepository

    void setup() {
        companyRepository = embeddedServer.applicationContext.createBean(CompanyRepository)
        presetRepository = embeddedServer.applicationContext.createBean(CompanyWorkflowTypeOcrSettingPresetRepository)

        if(testCompany == null){
            createTestCompany(companyRepository)
        }



    }

    void cleanup() {
        def list = presetRepository.getAll()

        for (CompanyWorkflowTypeOcrSettingPresetEntity entity : list) {
            presetRepository.delete(entity)
        }

    }

    void "verify new created userDashboardMenu can be queried by id and equals original userDashboardMenu"() {

        given:
            def presetEntity = new CompanyWorkflowTypeOcrSettingPresetEntity()
            presetEntity.companyId = testCompanyId
            presetEntity.workflowTypeId = UUID.randomUUID()
            presetEntity.identity = "preset-1"
            presetEntity.presetName = "preset-name"
            

        when:
            presetRepository.save(presetEntity)
            def presetOptional = presetRepository.getById(presetEntity.id)

        then:
            presetOptional.isPresent()

            verifyCompanyWorkflowTypeOcrSettingPreset(presetOptional.get(), presetEntity)

    }

    void "verify edited user can be queried by id and equals original user"() {

        given:
            def presetEntity = new CompanyWorkflowTypeOcrSettingPresetEntity()
            presetEntity.companyId = testCompanyId
            presetEntity.workflowTypeId = UUID.randomUUID()
            presetEntity.identity = "preset-1"
            presetEntity.presetName = "preset-name"
            presetRepository.save(presetEntity)
            def presetOptional = presetRepository.getById(presetEntity.id)
            def presetEntitySaved =  presetOptional.get()
            presetEntitySaved.companyId = testCompanyId
            presetEntitySaved.workflowTypeId = UUID.randomUUID()
            presetEntitySaved.identity = "preset-edited"
            presetEntitySaved.presetName = "preset-name-edited"



        when:
            presetRepository.update(presetEntitySaved)
            presetOptional = presetRepository.getById(presetEntity.id)

        then:
            presetOptional.isPresent()

            verifyCompanyWorkflowTypeOcrSettingPreset(presetOptional.get(), presetEntitySaved)

    }

    void "verify deleted user can not be queried by id"() {

        given:
            def presetEntity = new CompanyWorkflowTypeOcrSettingPresetEntity()
            presetEntity.companyId = testCompanyId
            presetEntity.workflowTypeId = UUID.randomUUID()
            presetEntity.identity = "preset-1"
            presetEntity.presetName = "preset-name"
            presetRepository.save(presetEntity)

        when:
            presetRepository.delete(presetEntity)
            def presetOptional = presetRepository.getById(presetEntity.id)

        then:
            presetOptional.isPresent() == false

    }

    void "verify new created user list can be queried by get-all and equals original users"() {

        given:
        Map<UUID, CompanyWorkflowTypeOcrSettingPresetEntity> map = new HashMap<>()

        for(int i=1; i<=10; i++){
            def presetEntity = new CompanyWorkflowTypeOcrSettingPresetEntity()
            presetEntity.companyId = testCompanyId
            presetEntity.workflowTypeId = UUID.randomUUID()
            presetEntity.identity = "preset-" + i
            presetEntity.presetName = "preset-name-" + i
            presetRepository.save(presetEntity)
            map.put(presetEntity.id, presetEntity)
        }

        when:

            def userDashboardMenuList = presetRepository.getAll()

        then:
            userDashboardMenuList.isEmpty() == false
            for(CompanyWorkflowTypeOcrSettingPresetEntity entity: userDashboardMenuList){
                verifyCompanyWorkflowTypeOcrSettingPreset(map.get(entity.id), entity)
            }


    }



    private void verifyCompanyWorkflowTypeOcrSettingPreset(CompanyWorkflowTypeOcrSettingPresetEntity testPresetEntity,
                                                           CompanyWorkflowTypeOcrSettingPresetEntity presetEntity) {
        testPresetEntity.companyId == presetEntity.companyId
        testPresetEntity.workflowTypeId == presetEntity.workflowTypeId
        testPresetEntity.identity == presetEntity.identity
        testPresetEntity.presetName == presetEntity.presetName
    }

}