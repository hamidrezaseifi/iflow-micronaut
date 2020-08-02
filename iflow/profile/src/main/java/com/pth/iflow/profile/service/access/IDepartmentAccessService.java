package com.pth.iflow.profile.service.access;

import java.net.MalformedURLException;
import java.util.List;

import com.pth.iflow.common.exceptions.IFlowMessageConversionFailureException;
import com.pth.iflow.profile.exceptions.ProfileCustomizedException;
import com.pth.iflow.profile.model.Department;
import com.pth.iflow.profile.model.User;

public interface IDepartmentAccessService {

  Department getByIdentity(final String identity)
      throws ProfileCustomizedException, MalformedURLException, IFlowMessageConversionFailureException;

  Department saveDepartment(final Department department)
      throws ProfileCustomizedException, MalformedURLException, IFlowMessageConversionFailureException;

  void deleteDepartment(final Department department)
      throws ProfileCustomizedException, MalformedURLException, IFlowMessageConversionFailureException;

  List<Department> getListByCompanyIdentity(final String identity)
      throws ProfileCustomizedException, MalformedURLException, IFlowMessageConversionFailureException;

  List<User> getAllUserListByDepartmentId(final String identity)
      throws ProfileCustomizedException, MalformedURLException, IFlowMessageConversionFailureException;

  User getDepartmentManager(final String identity)
      throws ProfileCustomizedException, MalformedURLException, IFlowMessageConversionFailureException;

  User getDepartmentDeputy(final String identity)
      throws ProfileCustomizedException, MalformedURLException, IFlowMessageConversionFailureException;
}
