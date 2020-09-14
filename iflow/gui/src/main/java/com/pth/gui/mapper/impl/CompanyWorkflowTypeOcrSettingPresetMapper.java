package com.pth.gui.mapper.impl;

import com.pth.common.edo.CompanyWorkflowtypeItemOcrSettingPresetEdo;
import com.pth.common.mapping.ModelEdoMapperBase;
import com.pth.gui.mapper.ICompanyWorkflowTypeOcrSettingPresetItemMapper;
import com.pth.gui.mapper.ICompanyWorkflowTypeOcrSettingPresetMapper;
import com.pth.gui.models.CompanyWorkflowtypeItemOcrSettingPreset;

import javax.inject.Singleton;

@Singleton
public class CompanyWorkflowTypeOcrSettingPresetMapper
        extends ModelEdoMapperBase<CompanyWorkflowtypeItemOcrSettingPreset, CompanyWorkflowtypeItemOcrSettingPresetEdo>
        implements ICompanyWorkflowTypeOcrSettingPresetMapper {

    private final ICompanyWorkflowTypeOcrSettingPresetItemMapper companyWorkflowTypeOcrSettingPresetItemMapper;

    public CompanyWorkflowTypeOcrSettingPresetMapper(ICompanyWorkflowTypeOcrSettingPresetItemMapper
                                                             companyWorkflowTypeOcrSettingPresetItemMapper) {
        this.companyWorkflowTypeOcrSettingPresetItemMapper = companyWorkflowTypeOcrSettingPresetItemMapper;
    }

    @Override
    public CompanyWorkflowtypeItemOcrSettingPreset fromEdo(CompanyWorkflowtypeItemOcrSettingPresetEdo edo) {
        final CompanyWorkflowtypeItemOcrSettingPreset model = new CompanyWorkflowtypeItemOcrSettingPreset();
        model.setPresetName(edo.getPresetName());
        model.setStatus(edo.getStatus());
        model.setVersion(edo.getVersion());
        model.setWorkflowTypeId(edo.getWorkflowTypeId());
        model.setCompanyId(edo.getCompanyId());
        model.setItems(companyWorkflowTypeOcrSettingPresetItemMapper.fromEdoList(edo.getItems()));
        model.setIdentity(edo.getIdentity());

        return model;
    }

    @Override
    public CompanyWorkflowtypeItemOcrSettingPresetEdo toEdo(CompanyWorkflowtypeItemOcrSettingPreset model) {
        final CompanyWorkflowtypeItemOcrSettingPresetEdo edo = new CompanyWorkflowtypeItemOcrSettingPresetEdo();
        edo.setPresetName(model.getPresetName());
        edo.setStatus(model.getStatus());
        edo.setVersion(model.getVersion());
        edo.setWorkflowTypeId(model.getWorkflowTypeId());
        edo.setCompanyId(model.getCompanyId());
        edo.setItems(companyWorkflowTypeOcrSettingPresetItemMapper.toEdoList(model.getItems()));
        edo.setIdentity(model.getIdentity());

        return edo;
    }

}
