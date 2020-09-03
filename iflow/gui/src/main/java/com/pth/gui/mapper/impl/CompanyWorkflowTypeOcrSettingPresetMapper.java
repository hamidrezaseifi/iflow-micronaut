package com.pth.gui.mapper.impl;

import com.pth.common.edo.CompanyWorkflowtypeItemOcrSettingPresetEdo;
import com.pth.common.mapping.ModelEdoMapperBase;
import com.pth.common.utils.MappingUtils;
import com.pth.gui.mapper.ICompanyWorkflowTypeOcrSettingPresetMapper;
import com.pth.gui.models.CompanyWorkflowtypeItemOcrSettingPreset;

import javax.inject.Singleton;

@Singleton
public class CompanyWorkflowTypeOcrSettingPresetMapper
        extends ModelEdoMapperBase<CompanyWorkflowtypeItemOcrSettingPreset, CompanyWorkflowtypeItemOcrSettingPresetEdo>
        implements ICompanyWorkflowTypeOcrSettingPresetMapper {
    @Override
    public CompanyWorkflowtypeItemOcrSettingPreset fromEdo(CompanyWorkflowtypeItemOcrSettingPresetEdo edo) {
        CompanyWorkflowtypeItemOcrSettingPreset model = MappingUtils.copyProperties(edo, new CompanyWorkflowtypeItemOcrSettingPreset());

        return model;
    }

    @Override
    public CompanyWorkflowtypeItemOcrSettingPresetEdo toEdo(CompanyWorkflowtypeItemOcrSettingPreset model) {
        CompanyWorkflowtypeItemOcrSettingPresetEdo edo = MappingUtils.copyProperties(model, new CompanyWorkflowtypeItemOcrSettingPresetEdo());

        return edo;
    }

}
