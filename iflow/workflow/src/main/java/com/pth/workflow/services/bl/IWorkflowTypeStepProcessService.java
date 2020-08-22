package com.pth.workflow.services.bl;

import com.pth.common.exceptions.IFlowMessageConversionFailureException;
import com.pth.workflow.entities.workflow.WorkflowTypeStepEntity;
import io.micronaut.security.authentication.Authentication;

import java.net.MalformedURLException;
import java.util.List;
import java.util.Set;

public interface IWorkflowTypeStepProcessService {

  public WorkflowTypeStepEntity getByIdentity(String identity,
                                              Authentication authentication)
      throws IFlowMessageConversionFailureException;

  public List<WorkflowTypeStepEntity> getListByWorkflowIdentity(final String workflowIdentity,
                                                          Authentication authentication)
      throws IFlowMessageConversionFailureException;

  public List<WorkflowTypeStepEntity> getListByIdentityList(final Set<String> identityList,
                                                      Authentication authentication)
      throws IFlowMessageConversionFailureException;
}
