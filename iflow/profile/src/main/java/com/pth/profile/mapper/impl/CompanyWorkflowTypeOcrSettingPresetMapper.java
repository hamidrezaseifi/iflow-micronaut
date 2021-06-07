package com.pth.profile.mapper.impl;

import com.pth.common.edo.CompanyWorkflowtypeItemOcrSettingPresetEdo;
import com.pth.common.edo.CompanyWorkflowtypeItemOcrSettingPresetItemEdo;
import com.pth.common.mapping.IModelEdoMapper;
import com.pth.common.mapping.ModelEdoMapperBase;
import com.pth.common.utils.MappingUtils;
import com.pth.profile.entities.CompanyWorkflowTypeOcrSettingPresetEntity;
import com.pth.profile.entities.CompanyWorkflowTypeOcrSettingPresetItemEntity;
import com.pth.profile.mapper.ICompanyWorkflowTypeOcrSettingPresetMapper;

import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;

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

        List<CompanyWorkflowtypeItemOcrSettingPresetItemEdo> itemList = new ArrayList();
        for(CompanyWorkflowTypeOcrSettingPresetItemEntity item: model.getItems()){
            CompanyWorkflowtypeItemOcrSettingPresetItemEdo itemEdo = MappingUtils.copyProperties(item, new CompanyWorkflowtypeItemOcrSettingPresetItemEdo());
            itemList.add(itemEdo);
        }
        edo.setItems(itemList);
        return edo;
    }

}
