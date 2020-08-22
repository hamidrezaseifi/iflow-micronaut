package com.pth.workflow.services.bl.impl;

import java.net.MalformedURLException;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.pth.iflow.common.exceptions.IFlowMessageConversionFailureException;
import com.pth.iflow.workflow.bl.IWorkflowTypeStepDataService;
import com.pth.iflow.workflow.bl.IWorkflowTypeStepProcessService;
import com.pth.iflow.workflow.exceptions.WorkflowCustomizedException;
import com.pth.iflow.workflow.models.WorkflowTypeStep;

@Service
public class WorkflowTypeStepProcessService implements IWorkflowTypeStepProcessService {

  private static final Logger logger = LoggerFactory.getLogger(WorkflowTypeStepProcessService.class);

  private final IWorkflowTypeStepDataService workflowTypeStepDataService;

  public WorkflowTypeStepProcessService(@Autowired final IWorkflowTypeStepDataService workflowTypeStepDataService) {

    this.workflowTypeStepDataService = workflowTypeStepDataService;
  }

  @Override
  public WorkflowTypeStep getByIdentity(final String identity, final Authentication authentication)
      throws WorkflowCustomizedException, MalformedURLException, IFlowMessageConversionFailureException {

    logger.debug("Request workflow-step data for id {}", identity);

    return this.workflowTypeStepDataService.getByIdentity(identity, authentication);
  }

  @Override
  public List<WorkflowTypeStep> getListByWorkflowIdentity(final String workflowIdentity, final Authentication authentication)
      throws WorkflowCustomizedException, MalformedURLException, IFlowMessageConversionFailureException {

    logger.debug("Request workflow-step list for workflow id {}", workflowIdentity);

    return this.workflowTypeStepDataService.getListByWorkflowIdentity(workflowIdentity, authentication);
  }

  @Override
  public List<WorkflowTypeStep> getListByIdentityList(final Set<String> identityList, final Authentication authentication)
      throws WorkflowCustomizedException, MalformedURLException, IFlowMessageConversionFailureException {

    logger.debug("Request workflow-step list for id list");

    return this.workflowTypeStepDataService.getListByIdentityList(identityList, authentication);
  }

}
