package com.pth.gui.mapper.impl;

import com.pth.common.edo.CompanyWorkflowtypeItemOcrSettingPresetEdo;
import com.pth.common.edo.CompanyWorkflowtypeItemOcrSettingPresetItemEdo;
import com.pth.common.mapping.ModelEdoMapperBase;
import com.pth.gui.mapper.ICompanyWorkflowTypeOcrSettingPresetItemMapper;
import com.pth.gui.mapper.ICompanyWorkflowTypeOcrSettingPresetMapper;
import com.pth.gui.models.CompanyWorkflowtypeItemOcrSettingPreset;
import com.pth.gui.models.CompanyWorkflowtypeItemOcrSettingPresetItem;

import javax.inject.Singleton;

@Singleton
public class CompanyWorkflowTypeOcrSettingPresetItemMapper
        extends ModelEdoMapperBase<CompanyWorkflowtypeItemOcrSettingPresetItem,
        CompanyWorkflowtypeItemOcrSettingPresetItemEdo>
        implements ICompanyWorkflowTypeOcrSettingPresetItemMapper {
    @Override
    public CompanyWorkflowtypeItemOcrSettingPresetItem fromEdo(CompanyWorkflowtypeItemOcrSettingPresetItemEdo edo) {
        final CompanyWorkflowtypeItemOcrSettingPresetItem model = new CompanyWorkflowtypeItemOcrSettingPresetItem();
        model.setPropertyName(edo.getPropertyName());
        model.setStatus(edo.getStatus());
        model.setVersion(edo.getVersion());
        model.setValue(edo.getValue());
        model.setOcrType(edo.getOcrType());
        model.setId(edo.getId());

        return model;
    }

    @Override
    public CompanyWorkflowtypeItemOcrSettingPresetItemEdo toEdo(CompanyWorkflowtypeItemOcrSettingPresetItem model) {
        final CompanyWorkflowtypeItemOcrSettingPresetItemEdo edo = new CompanyWorkflowtypeItemOcrSettingPresetItemEdo();
        edo.setPropertyName(model.getPropertyName());
        edo.setStatus(model.getStatus());
        edo.setVersion(model.getVersion());
        edo.setValue(model.getValue());
        edo.setOcrType(model.getOcrType());
        edo.setId(model.getId());

        return edo;
    }

}
