package com.pth.iflow.profile.service.handler;

import java.net.MalformedURLException;
import java.util.List;

import com.pth.iflow.common.exceptions.IFlowMessageConversionFailureException;
import com.pth.iflow.profile.exceptions.ProfileCustomizedException;
import com.pth.iflow.profile.model.Department;

public interface IDepartmentsHandlerService {

  public Department getDepartmentByIdentity(final String identity)
      throws ProfileCustomizedException, MalformedURLException, IFlowMessageConversionFailureException;

  public List<Department> getDepartmentListByCompanyIdentity(final String companyId)
      throws ProfileCustomizedException, MalformedURLException, IFlowMessageConversionFailureException;

  public Department saveDepartment(final Department department) throws MalformedURLException, IFlowMessageConversionFailureException;

  public void deleteDepartment(Department department) throws MalformedURLException, IFlowMessageConversionFailureException;

}
