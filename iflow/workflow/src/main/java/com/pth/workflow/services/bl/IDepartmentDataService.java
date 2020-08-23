package com.pth.workflow.services.bl;

import com.pth.common.exceptions.IFlowMessageConversionFailureException;
import com.pth.workflow.exceptions.WorkflowCustomizedException;
import com.pth.workflow.models.User;
import io.micronaut.security.authentication.Authentication;

import java.net.MalformedURLException;
import java.util.List;
import java.util.UUID;

public interface IDepartmentDataService {

  public List<User> getUserListByDepartmentId(UUID departmentId, String authorization)
          throws WorkflowCustomizedException;

}
