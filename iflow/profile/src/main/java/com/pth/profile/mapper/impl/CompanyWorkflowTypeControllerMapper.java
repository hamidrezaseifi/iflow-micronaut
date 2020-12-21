package com.pth.profile.mapper.impl;

import com.pth.common.edo.CompanyWorkflowTypeControllerEdo;
import com.pth.common.edo.CompanyWorkflowTypeControllerEdo;
import com.pth.common.mapping.ModelEdoMapperBase;
import com.pth.profile.entities.CompanyWorkflowTypeControllerEntity;
import com.pth.profile.entities.CompanyWorkflowTypeControllerEntity;
import com.pth.profile.mapper.ICompanyWorkflowTypeControllerMapper;
import com.pth.profile.mapper.ICompanyWorkflowTypeOcrSettingPresetItemMapper;
import com.pth.profile.mapper.ICompanyWorkflowTypeOcrSettingPresetMapper;

import javax.inject.Singleton;

@Singleton
public class CompanyWorkflowTypeControllerMapper
        extends ModelEdoMapperBase<CompanyWorkflowTypeControllerEntity, CompanyWorkflowTypeControllerEdo>
        implements ICompanyWorkflowTypeControllerMapper {

    private  final ICompanyWorkflowTypeOcrSettingPresetItemMapper companyWorkflowTypeOcrSettingPresetItemMapper;

    public CompanyWorkflowTypeControllerMapper(ICompanyWorkflowTypeOcrSettingPresetItemMapper companyWorkflowTypeOcrSettingPresetItemMapper) {
        this.companyWorkflowTypeOcrSettingPresetItemMapper = companyWorkflowTypeOcrSettingPresetItemMapper;
    }

    @Override
    public CompanyWorkflowTypeControllerEntity fromEdo(CompanyWorkflowTypeControllerEdo edo) {

        CompanyWorkflowTypeControllerEntity model = new CompanyWorkflowTypeControllerEntity();
        model.setCompanyId(edo.getCompanyId());
        model.setWorkflowTypeId(edo.getWorkflowTypeId());
        model.setId(edo.getId());
        model.setPriority(edo.getPriority());
        model.setUserId(edo.getUserId());
        model.setWorkflowTypeId(edo.getWorkflowTypeId());

        return model;
    }

    @Override
    public CompanyWorkflowTypeControllerEdo toEdo(CompanyWorkflowTypeControllerEntity model) {

        CompanyWorkflowTypeControllerEdo edo = new CompanyWorkflowTypeControllerEdo();
        edo.setCompanyId(model.getCompanyId());
        edo.setWorkflowTypeId(model.getWorkflowTypeId());
        edo.setId(model.getId());
        edo.setPriority(model.getPriority());
        edo.setUserId(model.getUserId());
        edo.setWorkflowTypeId(model.getWorkflowTypeId());

        return edo;
    }

}
