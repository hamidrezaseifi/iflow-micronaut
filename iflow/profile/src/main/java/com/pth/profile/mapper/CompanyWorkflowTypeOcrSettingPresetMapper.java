package com.pth.profile.mapper;

import com.pth.common.edo.CompanyWorkflowtypeItemOcrSettingPresetEdo;
import com.pth.common.mapping.IModelEdoMapper;
import com.pth.common.mapping.ModelEdoMapperBase;
import com.pth.common.utils.MappingUtils;
import com.pth.profile.entities.CompanyWorkflowTypeOcrSettingPresetEntity;

import javax.inject.Singleton;

@Singleton
public class CompanyWorkflowTypeOcrSettingPresetMapper
        extends ModelEdoMapperBase<CompanyWorkflowTypeOcrSettingPresetEntity, CompanyWorkflowtypeItemOcrSettingPresetEdo>
        implements ICompanyWorkflowTypeOcrSettingPresetMapper {
    @Override
    public CompanyWorkflowTypeOcrSettingPresetEntity fromEdo(CompanyWorkflowtypeItemOcrSettingPresetEdo edo) {
        CompanyWorkflowTypeOcrSettingPresetEntity model = MappingUtils.copyProperties(edo, new CompanyWorkflowTypeOcrSettingPresetEntity());

        return model;
    }

    @Override
    public CompanyWorkflowtypeItemOcrSettingPresetEdo toEdo(CompanyWorkflowTypeOcrSettingPresetEntity model) {
        CompanyWorkflowtypeItemOcrSettingPresetEdo edo = MappingUtils.copyProperties(model, new CompanyWorkflowtypeItemOcrSettingPresetEdo());

        return edo;
    }

}
