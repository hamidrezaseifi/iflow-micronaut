package com.pth.profile.mapper.impl;

import com.pth.common.edo.CompanyWorkflowtypeItemOcrSettingPresetEdo;
import com.pth.common.edo.CompanyWorkflowtypeItemOcrSettingPresetItemEdo;
import com.pth.common.mapping.IModelEdoMapper;
import com.pth.common.mapping.ModelEdoMapperBase;
import com.pth.common.utils.MappingUtils;
import com.pth.profile.entities.CompanyWorkflowTypeOcrSettingPresetEntity;
import com.pth.profile.entities.CompanyWorkflowTypeOcrSettingPresetItemEntity;
import com.pth.profile.mapper.ICompanyWorkflowTypeOcrSettingPresetItemMapper;
import com.pth.profile.mapper.ICompanyWorkflowTypeOcrSettingPresetMapper;

import javax.inject.Singleton;

@Singleton
public class CompanyWorkflowTypeOcrSettingPresetMapper
        extends ModelEdoMapperBase<CompanyWorkflowTypeOcrSettingPresetEntity, CompanyWorkflowtypeItemOcrSettingPresetEdo>
        implements ICompanyWorkflowTypeOcrSettingPresetMapper {

    private  final ICompanyWorkflowTypeOcrSettingPresetItemMapper companyWorkflowTypeOcrSettingPresetItemMapper;

    public CompanyWorkflowTypeOcrSettingPresetMapper(ICompanyWorkflowTypeOcrSettingPresetItemMapper companyWorkflowTypeOcrSettingPresetItemMapper) {
        this.companyWorkflowTypeOcrSettingPresetItemMapper = companyWorkflowTypeOcrSettingPresetItemMapper;
    }

    @Override
    public CompanyWorkflowTypeOcrSettingPresetEntity fromEdo(CompanyWorkflowtypeItemOcrSettingPresetEdo edo) {
       // CompanyWorkflowTypeOcrSettingPresetEntity model = MappingUtils.copyProperties(edo, new CompanyWorkflowTypeOcrSettingPresetEntity());
        CompanyWorkflowTypeOcrSettingPresetEntity model = new CompanyWorkflowTypeOcrSettingPresetEntity();
        model.setCompanyId(edo.getCompanyId());
        model.setIdentity(edo.getIdentity());
        model.setId(edo.getId());
        model.setPresetName(edo.getPresetName());
        model.setStatus(edo.getStatus());
        model.setVersion(edo.getVersion());
        model.setWorkflowTypeId(edo.getWorkflowTypeId());

        model.setItems(companyWorkflowTypeOcrSettingPresetItemMapper.fromEdoList(edo.getItems()));

        return model;
    }

    @Override
    public CompanyWorkflowtypeItemOcrSettingPresetEdo toEdo(CompanyWorkflowTypeOcrSettingPresetEntity model) {
        //CompanyWorkflowtypeItemOcrSettingPresetEdo edo = MappingUtils.copyProperties(model, new CompanyWorkflowtypeItemOcrSettingPresetEdo());
        CompanyWorkflowtypeItemOcrSettingPresetEdo edo = new CompanyWorkflowtypeItemOcrSettingPresetEdo();
        edo.setCompanyId(model.getCompanyId());
        edo.setIdentity(model.getIdentity());
        edo.setId(model.getId());
        edo.setPresetName(model.getPresetName());
        edo.setStatus(model.getStatus());
        edo.setVersion(model.getVersion());
        edo.setWorkflowTypeId(model.getWorkflowTypeId());

        edo.setItems(companyWorkflowTypeOcrSettingPresetItemMapper.toEdoList(model.getItems()));
        return edo;
    }

}
