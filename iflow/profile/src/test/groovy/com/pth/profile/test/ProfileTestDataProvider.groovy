package com.pth.profile.test

import com.pth.common.edo.enums.ECompanyType
import com.pth.profile.entities.CompanyEntity
import com.pth.profile.entities.DepartmentEntity
import com.pth.profile.repositories.ICompanyRepository
import com.pth.profile.repositories.IDepartmentRepository
import com.pth.profile.repositories.impl.CompanyRepository
import com.pth.profile.repositories.impl.UserRepository
import org.apache.commons.lang3.RandomStringUtils
import spock.lang.Specification

import java.nio.charset.Charset
import java.util.UUID
import java.util.stream.Collectors

import static java.lang.StringBuilder.*;

class ProfileTestDataProvider extends Specification {

    protected UUID testCompanyId = UUID.fromString("c7ada239-26a7-4402-ad12-ceae751fa7d8")
    protected UUID testDepartmentId1 = UUID.fromString("c7ada239-26a7-4402-ad12-ceae751fa7da")
    protected UUID testDepartmentId2 = UUID.fromString("c7ada239-26a7-4402-ad12-ceae751fa7db")
    protected UUID testDepartmentId3 = UUID.fromString("c7ada239-26a7-4402-ad12-ceae751fa7dc")

    protected CompanyEntity testCompany = null
    protected DepartmentEntity testDepartment1 = null
    protected DepartmentEntity testDepartment2 = null
    protected DepartmentEntity testDepartment3 = null

    protected String generateRandomString(int length) {
        String generatedString = RandomStringUtils.random(length, true, true)

        return generatedString
    }

    protected CompanyEntity createTestCompany(ICompanyRepository companyRepository) {

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
        companyRepository.save(testCompany)

        return testCompany
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
            departmentRepository.save(testDepartment1)
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
            departmentRepository.save(testDepartment2)
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
            departmentRepository.save(testDepartment3)
        }
    }

}
