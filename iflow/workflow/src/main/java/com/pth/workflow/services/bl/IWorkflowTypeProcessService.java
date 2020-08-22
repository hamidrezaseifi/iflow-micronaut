package com.pth.workflow.services.bl;

import com.pth.common.exceptions.IFlowMessageConversionFailureException;
import com.pth.workflow.entities.workflow.WorkflowTypeEntity;
import com.pth.workflow.entities.workflow.WorkflowTypeStepEntity;
import io.micronaut.security.authentication.Authentication;

import java.net.MalformedURLException;
import java.util.List;
import java.util.Set;


public interface IWorkflowTypeProcessService {

  public WorkflowTypeEntity getByIdentity(String identity,
                                          final Authentication authentication)
      throws IFlowMessageConversionFailureException;

  public List<WorkflowTypeEntity> getListByCompanyIdentity(String identity,
                                                     final Authentication authentication)
      throws IFlowMessageConversionFailureException;

  public List<WorkflowTypeStepEntity> getStepsByIdentity(String identity,
                                                         final Authentication authentication)
      throws IFlowMessageConversionFailureException;

  public List<WorkflowTypeEntity> getListByIdentityList(final Set<String> identityList,
                                                  final Authentication authentication)
      throws IFlowMessageConversionFailureException;
}
