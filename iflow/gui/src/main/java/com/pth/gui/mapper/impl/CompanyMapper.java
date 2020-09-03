package com.pth.gui.mapper.impl;

import com.pth.common.edo.CompanyEdo;
import com.pth.common.mapping.ModelEdoMapperBase;
import com.pth.common.utils.MappingUtils;
import com.pth.profile.entities.CompanyEntity;
import com.pth.profile.mapper.ICompanyMapper;

import javax.inject.Singleton;

@Singleton
public class CompanyMapper extends ModelEdoMapperBase<CompanyEntity, CompanyEdo>
        implements ICompanyMapper {
    @Override
    public CompanyEntity fromEdo(CompanyEdo edo) {
        CompanyEntity model = MappingUtils.copyProperties(edo, new CompanyEntity());

        return model;
    }

    @Override
    public CompanyEdo toEdo(CompanyEntity model) {
        CompanyEdo edo = MappingUtils.copyProperties(model, new CompanyEdo());

        return edo;
    }

}
