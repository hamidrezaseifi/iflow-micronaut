package com.pth.clients.clients.profile;

import com.pth.common.edo.DepartmentEdo;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IDepartmentClient {

    List<DepartmentEdo> getCompanyDepartmentList(String authorization, final UUID companyId);

    Optional<DepartmentEdo> create(String authorization, final DepartmentEdo departmentEdo);

    Optional<DepartmentEdo> update(String authorization, final DepartmentEdo departmentEdo);

    void delete(String authorization, final DepartmentEdo departmentEdo);
}
