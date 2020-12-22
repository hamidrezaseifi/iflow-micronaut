package com.pth.profile.test

import com.pth.common.edo.CompanyEdo
import com.pth.common.edo.CompanyWorkflowtypeItemOcrSettingPresetEdo
import com.pth.common.edo.enums.ECompanyType
import com.pth.profile.entities.CompanyEntity
import com.pth.profile.entities.CompanyWorkflowTypeOcrSettingPresetEntity
import com.pth.profile.entities.DepartmentEntity
import com.pth.profile.entities.UserDashboardMenuEntity
import com.pth.profile.entities.UserEntity
import com.pth.profile.entities.UserGroupEntity
import com.pth.profile.repositories.ICompanyRepository
import com.pth.profile.repositories.IDepartmentRepository
import com.pth.profile.repositories.IUserRepository
import org.apache.commons.lang3.RandomStringUtils
import spock.lang.Specification

class ProfileTestDataProvider extends Specification {

    protected UUID testCompanyId = UUID.fromString("c7ada239-26a7-4402-ad12-ceae751fa7d8")

    protected UUID testDepartmentId1 = UUID.fromString("c7ada239-26a7-4402-ad12-ceae751fa7da")
    protected UUID testDepartmentId2 = UUID.fromString("c7ada239-26a7-4402-ad12-ceae751fa7db")
    protected UUID testDepartmentId3 = UUID.fromString("c7ada239-26a7-4402-ad12-ceae751fa7dc")

    protected UUID testUserId1 = UUID.fromString("c7ada239-26a7-4402-ad12-ceae751fa711")

    protected CompanyEntity testCompany = null

    protected DepartmentEntity testDepartment1 = null
    protected DepartmentEntity testDepartment2 = null
    protected DepartmentEntity testDepartment3 = null

    protected UserEntity testUser1 = null

    protected String generateRandomString(int length) {
        String generatedString = RandomStringUtils.random(length, true, true)

        return generatedString
    }

    protected CompanyEntity createTestCompanyEntity(ICompanyRepository companyRepository) {

        def companyEntityOptional = companyRepository.getById(testCompanyId)
        if(companyEntityOptional.isPresent()){
            testCompany = companyEntityOptional.get()
            return testCompany
        }
        testCompany = new CompanyEntity()
        testCompany.companyName = "Test-Company"
        testCompany.identity = "Test-Company"
        testCompany.companyType = ECompanyType.EINZELUNTERNEHMEN.enumValue
        testCompany.companyTypeCustome = "Test-Company"
        testCompany.status = 1
        testCompany.id = testCompanyId
        companyRepository.create(testCompany)

        return testCompany
    }

    protected CompanyEntity createTestCompanyEntity(int identifier) {


        def company = new CompanyEntity()
        company.companyName = "Test-Company" + identifier
        company.identity = "Test-Company" + identifier
        company.companyType = ECompanyType.EINZELUNTERNEHMEN.enumValue
        company.companyTypeCustome = "Test-Company" + identifier
        company.status = 1
        company.id = testCompanyId
        company.version = 1

        return company
    }

    protected CompanyEdo createTestCompanyEdo(int identifier) {


        def company = new CompanyEdo()
        company.companyName = "Test-Company" + identifier
        company.identity = "Test-Company" + identifier
        company.companyType = ECompanyType.EINZELUNTERNEHMEN.enumValue
        company.companyTypeCustome = "Test-Company" + identifier
        company.status = 1
        company.id = testCompanyId
        company.version = 1

        return company
    }

    protected UserEntity createTestUser(IUserRepository userRepository) {

        def userEntityOptional = userRepository.getById(testUserId1)
        if(userEntityOptional.isPresent()){
            testUser1 = userEntityOptional.get()
            return testUser1
        }
        testUser1 = new UserEntity()
        testUser1.passwordSalt = "passwordSalt"
        testUser1.passwordHash = "passwordHash"
        testUser1.birthDate = new Date()
        testUser1.username = "username"
        testUser1.companyId = testCompanyId
        testUser1.firstName = "fname"
        def identity = generateRandomString(15)
        testUser1.identity = identity
        testUser1.lastName = "lname"
        testUser1.permission = 1
        testUser1.id = testUserId1
        userRepository.create(testUser1)

        return testUser1
    }

    protected UserEntity createTestUser(int identifier) {

        def userEntity = new UserEntity()
        userEntity.passwordSalt = "passwordSalt" + identifier
        userEntity.passwordHash = "passwordHash" + identifier
        userEntity.birthDate = new Date()
        userEntity.username = "username" + identifier
        userEntity.companyId = testCompanyId
        userEntity.firstName = "fname" + identifier
        def identity = generateRandomString(15)
        userEntity.identity = identity
        userEntity.lastName = "lname" + identifier
        userEntity.permission = 1

        return userEntity
    }

    protected void createTestDepartments(IDepartmentRepository departmentRepository) {

        List<DepartmentEntity> list = departmentRepository.getAll()
        Map<UUID, DepartmentEntity> map = list.collectEntries{[it.id, it]}

        if(map.containsKey(testDepartmentId1)){
            testDepartment1 = map.get(testDepartmentId1)
        }
        else{
            testDepartment1 = new DepartmentEntity()
            testDepartment1.id = testDepartmentId1
            testDepartment1.identity = "test-department-1"
            testDepartment1.companyId = testCompanyId
            testDepartment1.title = "Test Department 1"
            departmentRepository.create(testDepartment1)
        }

        if(map.containsKey(testDepartmentId2)){
            testDepartment2 = map.get(testDepartmentId2)
        }
        else{
            testDepartment2 = new DepartmentEntity()
            testDepartment2.id = testDepartmentId2
            testDepartment2.identity = "test-department-2"
            testDepartment2.companyId = testCompanyId
            testDepartment2.title = "Test Department 2"
            departmentRepository.create(testDepartment2)
        }

        if(map.containsKey(testDepartmentId3)){
            testDepartment3 = map.get(testDepartmentId3)
        }
        else{
            testDepartment3 = new DepartmentEntity()
            testDepartment3.id = testDepartmentId3
            testDepartment3.identity = "test-department-3"
            testDepartment3.companyId = testCompanyId
            testDepartment3.title = "Test Department 3"
            departmentRepository.create(testDepartment3)
        }
    }


    protected DepartmentEntity createTestDepartments(int identifier) {

        def department = new DepartmentEntity()
        department.id = UUID.randomUUID()
        department.identity = "test-department-1"
        department.companyId = testCompanyId
        department.title = "Test Department 1"
        return department
    }

    protected UserGroupEntity createTestUserGroupEntity(int identifier){
        UserGroupEntity userGroupEntity = new UserGroupEntity()
        userGroupEntity.identity = "test-identity" + identifier
        userGroupEntity.companyId = testCompanyId
        userGroupEntity.title = "group-" + identifier

        return userGroupEntity
    }

    protected UserDashboardMenuEntity createTestUserDashboardMenuEntity(int identifier){
        UserDashboardMenuEntity dashboardMenuEntity = new UserDashboardMenuEntity()
        dashboardMenuEntity.userId = UUID.randomUUID()
        dashboardMenuEntity.appId = "app-id"
        dashboardMenuEntity.menuId = "menu-" + identifier
        dashboardMenuEntity.rowIndex = identifier
        dashboardMenuEntity.columnIndex = identifier

        return dashboardMenuEntity
    }

    protected CompanyWorkflowTypeOcrSettingPresetEntity createTestCompanyWorkflowTypeOcrSettingPresetEntity(int identifier){
        def setting = new CompanyWorkflowTypeOcrSettingPresetEntity()
        setting.identity = "test-identity" + identifier
        setting.companyId = testCompanyId
        setting.workflowTypeId = UUID.randomUUID()
        setting.presetName = "test-name" + identifier
        return setting
    }

    protected CompanyWorkflowTypeOcrSettingPresetEntity createTestCompanyWorkflowTypeOcrSettingPresetEdo(int identifier){
        def setting = new CompanyWorkflowTypeOcrSettingPresetEntity()
        setting.identity = "test-identity" + identifier
        setting.companyId = testCompanyId
        setting.workflowTypeId = UUID.randomUUID()
        setting.presetName = "test-name" + identifier
        setting.status = 1
        setting.version = 1

        return setting
    }

    protected CompanyWorkflowtypeItemOcrSettingPresetEdo createTestCompanyWorkflowtypeItemOcrSettingPresetEdo(int identifier){
        def setting = new CompanyWorkflowtypeItemOcrSettingPresetEdo()
        setting.id = UUID.randomUUID()
        setting.identity = "test-identity" + identifier
        setting.companyId = testCompanyId
        setting.workflowTypeId = UUID.randomUUID()
        setting.presetName = "test-name" + identifier
        setting.status = 1
        setting.version = 1

        return setting
    }

}
