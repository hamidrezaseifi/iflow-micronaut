package com.pth.profile.mapper.impl;

import com.pth.common.edo.CompanyWorkflowtypeItemOcrSettingPresetEdo;
import com.pth.common.edo.CompanyWorkflowtypeItemOcrSettingPresetItemEdo;
import com.pth.common.mapping.ModelEdoMapperBase;
import com.pth.common.utils.MappingUtils;
import com.pth.profile.entities.CompanyWorkflowTypeOcrSettingPresetEntity;
import com.pth.profile.entities.CompanyWorkflowTypeOcrSettingPresetItemEntity;
import com.pth.profile.mapper.ICompanyWorkflowTypeOcrSettingPresetItemMapper;
import com.pth.profile.mapper.ICompanyWorkflowTypeOcrSettingPresetMapper;

import javax.inject.Singleton;

@Singleton
public class CompanyWorkflowTypeOcrSettingPresetItemMapper
        extends ModelEdoMapperBase<CompanyWorkflowTypeOcrSettingPresetItemEntity, CompanyWorkflowtypeItemOcrSettingPresetItemEdo>
        implements ICompanyWorkflowTypeOcrSettingPresetItemMapper {

    @Override
    public CompanyWorkflowTypeOcrSettingPresetItemEntity fromEdo(CompanyWorkflowtypeItemOcrSettingPresetItemEdo edo) {
       CompanyWorkflowTypeOcrSettingPresetItemEntity model = MappingUtils.copyProperties(edo, new CompanyWorkflowTypeOcrSettingPresetItemEntity());

        return model;
    }

    @Override
    public CompanyWorkflowtypeItemOcrSettingPresetItemEdo toEdo(CompanyWorkflowTypeOcrSettingPresetItemEntity model) {
        CompanyWorkflowtypeItemOcrSettingPresetItemEdo edo = MappingUtils.copyProperties(model, new CompanyWorkflowtypeItemOcrSettingPresetItemEdo());

        return edo;
    }

}
