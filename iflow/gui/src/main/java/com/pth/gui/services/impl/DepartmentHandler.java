package com.pth.gui.services.impl;

import com.pth.clients.clients.profile.IDepartmentClient;
import com.pth.common.edo.DepartmentEdo;
import com.pth.gui.mapper.IDepartmentMapper;
import com.pth.gui.models.Department;
import com.pth.gui.models.gui.uisession.SessionData;
import com.pth.gui.services.IDepartmentHandler;
import org.apache.commons.lang3.RandomStringUtils;

import javax.inject.Singleton;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Singleton
public class DepartmentHandler implements IDepartmentHandler {

    private final IDepartmentClient departmentClient;
    private final IDepartmentMapper departmentMapper;

    public DepartmentHandler(final IDepartmentClient departmentClient, final IDepartmentMapper departmentMapper) {

        this.departmentClient = departmentClient;
        this.departmentMapper = departmentMapper;
    }

    @Override
    public List<Department> getCompanyDepartmentList(String authorization, final UUID companyId) {

        final List<DepartmentEdo> readEdoList =
                this.departmentClient.getCompanyDepartmentList(authorization, companyId);
        final List<Department> readDepartmentList = this.departmentMapper.fromEdoList(readEdoList);

        return readDepartmentList;
    }

    @Override
    public Optional<Department>
        createDepartment(String authorization, final UUID companyId, final Department department) {

        department.setCompanyId(companyId);
        final String identity = department.getTitle().toLowerCase() + "-" + RandomStringUtils.randomAlphanumeric(8);
        department.setIdentity(identity);

        Optional<DepartmentEdo> createdEdoOptional =
                this.departmentClient.create(authorization, departmentMapper.toEdo(department));
        if(createdEdoOptional.isPresent()){
            return Optional.of(departmentMapper.fromEdo(createdEdoOptional.get()));
        }

        return Optional.empty();
    }

    @Override
    public Optional<Department>
        updateDepartment(String authorization, final UUID companyId, final Department department) {

        department.setCompanyId(companyId);
        Optional<DepartmentEdo> createdEdoOptional =
                this.departmentClient.update(authorization, departmentMapper.toEdo(department));
        if(createdEdoOptional.isPresent()){
            return Optional.of(departmentMapper.fromEdo(createdEdoOptional.get()));
        }

        return Optional.empty();
    }

    @Override
    public void deleteDepartment(String authorization, final Department department) {

        this.departmentClient.delete(authorization, departmentMapper.toEdo(department));

    }
}
