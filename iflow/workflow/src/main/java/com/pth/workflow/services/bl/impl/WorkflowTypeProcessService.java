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
import com.pth.iflow.workflow.bl.IWorkflowTypeDataService;
import com.pth.iflow.workflow.bl.IWorkflowTypeProcessService;
import com.pth.iflow.workflow.exceptions.WorkflowCustomizedException;
import com.pth.iflow.workflow.models.WorkflowType;
import com.pth.iflow.workflow.models.WorkflowTypeStep;

@Service
public class WorkflowTypeProcessService implements IWorkflowTypeProcessService {

  private static final Logger logger = LoggerFactory.getLogger(WorkflowTypeProcessService.class);

  private final IWorkflowTypeDataService workflowTypeDataService;

  public WorkflowTypeProcessService(@Autowired final IWorkflowTypeDataService workflowTypeDataService) {

    this.workflowTypeDataService = workflowTypeDataService;
  }

  @Override
  public WorkflowType getByIdentity(final String identity, final Authentication authentication)
      throws WorkflowCustomizedException, MalformedURLException, IFlowMessageConversionFailureException {

    logger.debug("Request workflow data for id {}", identity);

    return this.workflowTypeDataService.getByIdentity(identity, authentication);

  }

  @Override
  public List<WorkflowType> getListByCompanyIdentity(final String identity, final Authentication authentication)
      throws WorkflowCustomizedException, MalformedURLException, IFlowMessageConversionFailureException {

    logger.debug("Request workflow list for company id {}", identity);

    return this.workflowTypeDataService.getListByCompanyIdentity(identity, authentication);
  }

  @Override
  public List<WorkflowType> getListByIdentityList(final Set<String> identityList, final Authentication authentication)
      throws WorkflowCustomizedException, MalformedURLException, IFlowMessageConversionFailureException {

    logger.debug("Request workflow list for id list {}");

    return this.workflowTypeDataService.getListByIdentityList(identityList, authentication);
  }

  @Override
  public List<WorkflowTypeStep> getStepsByIdentity(final String identity, final Authentication authentication)
      throws WorkflowCustomizedException, MalformedURLException, IFlowMessageConversionFailureException {

    logger.debug("Request workflow-step list for workflow id {}", identity);

    return this.workflowTypeDataService.getStepsByIdentity(identity, authentication);
  }

}
