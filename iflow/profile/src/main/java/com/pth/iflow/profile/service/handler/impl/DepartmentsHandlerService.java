package com.pth.iflow.profile.service.handler.impl;

import java.net.MalformedURLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pth.iflow.common.exceptions.IFlowMessageConversionFailureException;
import com.pth.iflow.profile.exceptions.ProfileCustomizedException;
import com.pth.iflow.profile.model.Department;
import com.pth.iflow.profile.service.access.IDepartmentAccessService;
import com.pth.iflow.profile.service.handler.IDepartmentsHandlerService;

@Service
public class DepartmentsHandlerService implements IDepartmentsHandlerService {

  private final IDepartmentAccessService departmentAccessService;

  public DepartmentsHandlerService(@Autowired final IDepartmentAccessService departmentAccessService) {

    this.departmentAccessService = departmentAccessService;
  }

  @Override
  public Department getDepartmentByIdentity(final String identity)
      throws ProfileCustomizedException, MalformedURLException, IFlowMessageConversionFailureException {

    return this.departmentAccessService.getByIdentity(identity);
  }

  @Override
  public List<Department> getDepartmentListByCompanyIdentity(final String companyId)
      throws ProfileCustomizedException, MalformedURLException, IFlowMessageConversionFailureException {

    return this.departmentAccessService.getListByCompanyIdentity(companyId);
  }

  @Override
  public Department saveDepartment(final Department department) throws MalformedURLException, IFlowMessageConversionFailureException {

    return this.departmentAccessService.saveDepartment(department);
  }

  @Override
  public void deleteDepartment(final Department department) throws MalformedURLException, IFlowMessageConversionFailureException {

    this.departmentAccessService.deleteDepartment(department);

  }

}
