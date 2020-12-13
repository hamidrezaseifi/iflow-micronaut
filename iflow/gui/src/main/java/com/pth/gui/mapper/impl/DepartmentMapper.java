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
        final Department model = new Department();

        model.setTitle(edo.getTitle());
        model.setStatus(edo.getStatus());
        model.setIdentity(edo.getIdentity());
        model.setVersion(edo.getVersion());
        model.setCompanyId(edo.getCompanyId());
        model.setId(edo.getId());

        return model;
    }

    @Override
    public DepartmentEdo toEdo(Department model) {
        final DepartmentEdo edo = new DepartmentEdo();
        edo.setTitle(model.getTitle());
        edo.setStatus(model.getStatus());
        edo.setIdentity(model.getIdentity());
        edo.setVersion(model.getVersion());
        edo.setCompanyId(model.getCompanyId());
        edo.setId(model.getId());

        return edo;
    }

}
