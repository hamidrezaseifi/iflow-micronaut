package com.pth.profile.test

import com.pth.common.edo.enums.ECompanyType
import com.pth.profile.entities.CompanyEntity
import com.pth.profile.repositories.ICompanyRepository
import com.pth.profile.repositories.impl.CompanyRepository
import com.pth.profile.repositories.impl.UserRepository
import org.apache.commons.lang3.RandomStringUtils
import spock.lang.Specification

import java.nio.charset.Charset
import java.util.UUID

import static java.lang.StringBuilder.*;

class ProfileTestDataProvider extends Specification {

    protected UUID testCompanyId = UUID.fromString("c7ada239-26a7-4402-ad12-ceae751fa7d8")
    protected CompanyEntity testCompany = null

    protected String generateRandomString(int length) {
        String generatedString = RandomStringUtils.random(length, true, true)

        return generatedString
    }

    protected CompanyEntity createTestCompany(ICompanyRepository companyRepository) {

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

}
