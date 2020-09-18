package com.pth.gui.mapper.impl;

import com.pth.common.edo.CompanyEdo;
import com.pth.common.mapping.ModelEdoMapperBase;
import com.pth.common.utils.MappingUtils;
import com.pth.gui.mapper.ICompanyMapper;
import com.pth.gui.models.Company;

import javax.inject.Singleton;

@Singleton
public class CompanyMapper extends ModelEdoMapperBase<Company, CompanyEdo>
        implements ICompanyMapper {
    @Override
    public Company fromEdo(CompanyEdo edo) {
        final Company model = new Company();
        model.setCompanyName(edo.getCompanyName());
        model.setIdentity(edo.getIdentity());
        model.setStatus(edo.getStatus());
        model.setVersion(edo.getVersion());
        model.setCompanyType(edo.getCompanyType());
        model.setCompanyTypeCustome(edo.getCompanyTypeCustome());
        model.setId(edo.getId());

        return model;
    }

    @Override
    public CompanyEdo toEdo(Company model) {
        final CompanyEdo edo = new CompanyEdo();
        edo.setCompanyName(model.getCompanyName());
        edo.setIdentity(model.getIdentity());
        edo.setStatus(model.getStatus());
        edo.setVersion(model.getVersion());
        edo.setCompanyType(model.getCompanyType());
        edo.setCompanyTypeCustome(model.getCompanyTypeCustome() == null ? "" : model.getCompanyTypeCustome());
        edo.setId(model.getId());

        return edo;
    }

}
