package com.pth.workflow.services.bl;

import com.pth.common.exceptions.IFlowMessageConversionFailureException;
import io.micronaut.security.authentication.Authentication;

import java.net.MalformedURLException;
import java.util.Set;

public interface IGuiCachDataDataService {

  public void resetCachDataForUser(String companyIdentity,
                                   String userIdentity,
                                   Authentication authentication)
          throws IFlowMessageConversionFailureException;

  public void resetCachDataForUserList(String companyIdentity,
                                       Set<String> userIdentityList,
                                       Authentication authentication)
      throws IFlowMessageConversionFailureException;

  public void resetCachDataForWorkflow(String companyIdentity,
                                       String workflowIdentity,
                                       Authentication authentication)
      throws IFlowMessageConversionFailureException;

}
