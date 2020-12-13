package com.pth.gui.services;

import com.pth.common.edo.DepartmentEdo;
import com.pth.gui.models.Department;
import com.pth.gui.models.gui.uisession.SessionData;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface IDepartmentHandler {

  List<Department> getCompanyDepartmentList(String authorization, final UUID companyId);

  Optional<Department> createDepartment(String authorization, final UUID companyId, final Department department);

  Optional<Department> updateDepartment(String authorization, final UUID companyId, final Department department);

  void deleteDepartment(String authorization, Department department);

}
