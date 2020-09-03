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
        Company model = MappingUtils.copyProperties(edo, new Company());

        return model;
    }

    @Override
    public CompanyEdo toEdo(Company model) {
        CompanyEdo edo = MappingUtils.copyProperties(model, new CompanyEdo());

        return edo;
    }

}
