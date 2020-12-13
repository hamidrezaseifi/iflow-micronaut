package com.pth.gui.services;

import com.pth.gui.models.Department;
import java.util.List;


public interface IDepartmentHandler {

  List<Department> getCompanyDepartmentList(final String companyIdentity);

  Department createDepartment(final Department department);

  Department updateDepartment(final Department department);

  void deleteDepartment(Department department);

}
