package com.pth.gui.mapper.impl;

import com.pth.common.edo.CompanyWorkflowTypeControllerEdo;
import com.pth.common.edo.CompanyWorkflowtypeItemOcrSettingPresetEdo;
import com.pth.common.mapping.ModelEdoMapperBase;
import com.pth.gui.mapper.ICompanyWorkflowTypeControllerMapper;
import com.pth.gui.mapper.ICompanyWorkflowTypeOcrSettingPresetItemMapper;
import com.pth.gui.mapper.ICompanyWorkflowTypeOcrSettingPresetMapper;
import com.pth.gui.models.CompanyWorkflowTypeController;
import com.pth.gui.models.CompanyWorkflowtypeItemOcrSettingPreset;

import javax.inject.Singleton;

@Singleton
public class CompanyWorkflowTypeControllerMapper
        extends ModelEdoMapperBase<CompanyWorkflowTypeController, CompanyWorkflowTypeControllerEdo>
        implements ICompanyWorkflowTypeControllerMapper {


    public CompanyWorkflowTypeControllerMapper() {

    }


    @Override
    public CompanyWorkflowTypeController fromEdo(CompanyWorkflowTypeControllerEdo edo) {

        CompanyWorkflowTypeController model = new CompanyWorkflowTypeController();

        model.setPriority(edo.getPriority());
        model.setUserId(edo.getUserId());
        model.setWorkflowTypeId(edo.getWorkflowTypeId());
        model.setId(edo.getId());

        return model;
    }

    @Override
    public CompanyWorkflowTypeControllerEdo toEdo(CompanyWorkflowTypeController model) {
        CompanyWorkflowTypeControllerEdo edo = new CompanyWorkflowTypeControllerEdo();

        edo.setPriority(model.getPriority());
        edo.setUserId(model.getUserId());
        edo.setWorkflowTypeId(model.getWorkflowTypeId());
        edo.setId(model.getId());


        return edo;
    }
}
