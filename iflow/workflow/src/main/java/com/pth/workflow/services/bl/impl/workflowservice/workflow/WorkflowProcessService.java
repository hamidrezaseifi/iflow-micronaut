package com.pth.workflow.services.bl.impl.workflowservice.workflow;

import java.net.MalformedURLException;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.pth.iflow.common.exceptions.EIFlowErrorType;
import com.pth.iflow.common.exceptions.IFlowMessageConversionFailureException;
import com.pth.iflow.workflow.bl.IWorkflowDataService;
import com.pth.iflow.workflow.bl.IWorkflowPrepare;
import com.pth.iflow.workflow.bl.IWorkflowProcessService;
import com.pth.iflow.workflow.exceptions.WorkflowCustomizedException;
import com.pth.iflow.workflow.models.base.IWorkflowSaveRequest;
import com.pth.iflow.workflow.models.workflow.Workflow;

@Service
public class WorkflowProcessService implements IWorkflowProcessService<Workflow> {

  private final Logger logger = LoggerFactory.getLogger(getClass());

  private final IWorkflowDataService<Workflow> invoiceWorkflowDataService;

  private final IWorkflowPrepare<Workflow> workflowPrepare;

  public WorkflowProcessService(@Autowired final IWorkflowDataService<Workflow> invoiceWorkflowDataService,
      @Autowired final IWorkflowPrepare<Workflow> workflowPrepare) {

    this.invoiceWorkflowDataService = invoiceWorkflowDataService;
    this.workflowPrepare = workflowPrepare;
  }

  @Override
  public List<Workflow> create(final IWorkflowSaveRequest<Workflow> request, final Authentication authentication)
      throws WorkflowCustomizedException, MalformedURLException, IFlowMessageConversionFailureException {

    throw new WorkflowCustomizedException("not implemented", EIFlowErrorType.SERVICE_NOT_IMPLEMENTED);
  }

  @Override
  public Workflow save(final IWorkflowSaveRequest<Workflow> request, final Authentication authentication)
      throws WorkflowCustomizedException, MalformedURLException, IFlowMessageConversionFailureException {

    throw new WorkflowCustomizedException("not implemented", EIFlowErrorType.SERVICE_NOT_IMPLEMENTED);
  }

  @Override
  public void validate(final IWorkflowSaveRequest<Workflow> request, final Authentication authentication)
      throws WorkflowCustomizedException, MalformedURLException, IFlowMessageConversionFailureException {

    throw new WorkflowCustomizedException("not implemented", EIFlowErrorType.SERVICE_NOT_IMPLEMENTED);
  }

  @Override
  public Workflow getByIdentity(final String identity, final Authentication authentication)
      throws WorkflowCustomizedException, MalformedURLException, IFlowMessageConversionFailureException {

    logger.debug("get workflow by id {} with authentication {}", identity, authentication);

    final Workflow workflow = this.invoiceWorkflowDataService.getByIdentity(identity, authentication);

    return workflowPrepare.prepareWorkflow(authentication, workflow);
  }

  @Override
  public List<Workflow> getListForUser(final String identity, final int status, final Authentication authentication)
      throws WorkflowCustomizedException, MalformedURLException, IFlowMessageConversionFailureException {

    logger.debug("get workflow assigned to user id {} and has status {} with authentication {}", identity, status, authentication);

    final List<Workflow> list = this.invoiceWorkflowDataService.getListForUser(identity, status, authentication);

    return workflowPrepare.prepareWorkflowList(authentication, list);
  }

  @Override
  public List<Workflow> getListByIdentityList(final Set<String> identityList, final Authentication authentication)
      throws WorkflowCustomizedException, MalformedURLException, IFlowMessageConversionFailureException {

    logger.debug("get workflow list by id list with authentication {}", authentication);

    final List<Workflow> list = this.invoiceWorkflowDataService.getListByIdentityList(identityList, authentication);

    return workflowPrepare.prepareWorkflowList(authentication, list);
  }

}
