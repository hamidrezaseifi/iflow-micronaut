package com.pth.gui.mapper.impl;

import com.pth.common.edo.DepartmentEdo;
import com.pth.common.mapping.ModelEdoMapperBase;
import com.pth.common.utils.MappingUtils;
import com.pth.profile.entities.DepartmentEntity;
import com.pth.profile.mapper.IDepartmentMapper;

import javax.inject.Singleton;

@Singleton
public class DepartmentMapper extends ModelEdoMapperBase<DepartmentEntity, DepartmentEdo>
        implements IDepartmentMapper {
    @Override
    public DepartmentEntity fromEdo(DepartmentEdo edo) {
        DepartmentEntity model = MappingUtils.copyProperties(edo, new DepartmentEntity());

        return model;
    }

    @Override
    public DepartmentEdo toEdo(DepartmentEntity model) {
        DepartmentEdo edo = MappingUtils.copyProperties(model, new DepartmentEdo());

        return edo;
    }

}
