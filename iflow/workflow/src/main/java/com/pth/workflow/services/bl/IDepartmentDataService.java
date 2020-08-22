package com.pth.workflow.services.bl;

import com.pth.common.exceptions.IFlowMessageConversionFailureException;
import com.pth.workflow.models.User;
import io.micronaut.security.authentication.Authentication;

import java.net.MalformedURLException;
import java.util.List;

public interface IDepartmentDataService {

  public List<User> getUserListByDepartmentIdentity(String departmentIdentity,
                                                    Authentication authentication)
      throws IFlowMessageConversionFailureException;

}
