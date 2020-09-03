package com.pth.gui.mapper.impl;

import com.pth.common.edo.DepartmentEdo;
import com.pth.common.mapping.ModelEdoMapperBase;
import com.pth.common.utils.MappingUtils;
import com.pth.gui.mapper.IDepartmentMapper;
import com.pth.gui.models.Department;

import javax.inject.Singleton;

@Singleton
public class DepartmentMapper extends ModelEdoMapperBase<Department, DepartmentEdo>
        implements IDepartmentMapper {
    @Override
    public Department fromEdo(DepartmentEdo edo) {
        Department model = MappingUtils.copyProperties(edo, new Department());

        return model;
    }

    @Override
    public DepartmentEdo toEdo(Department model) {
        DepartmentEdo edo = MappingUtils.copyProperties(model, new DepartmentEdo());

        return edo;
    }

}
