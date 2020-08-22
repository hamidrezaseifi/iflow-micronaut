package com.pth.workflow.services.bl.impl.workflowservice.invoice;

import java.net.MalformedURLException;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.pth.iflow.common.exceptions.IFlowMessageConversionFailureException;
import com.pth.iflow.workflow.bl.IWorkflowDataService;
import com.pth.iflow.workflow.bl.IWorkflowPrepare;
import com.pth.iflow.workflow.bl.IWorkflowProcessService;
import com.pth.iflow.workflow.bl.strategy.IWorkflowSaveStrategy;
import com.pth.iflow.workflow.bl.strategy.IWorkflowSaveStrategyFactory;
import com.pth.iflow.workflow.exceptions.WorkflowCustomizedException;
import com.pth.iflow.workflow.models.base.IWorkflowSaveRequest;
import com.pth.iflow.workflow.models.workflow.invoice.InvoiceWorkflow;

@Service
public class InvoiceWorkflowProcessService implements IWorkflowProcessService<InvoiceWorkflow> {

  private final Logger logger = LoggerFactory.getLogger(getClass());

  private final IWorkflowDataService<InvoiceWorkflow> invoiceWorkflowDataService;

  private final IWorkflowSaveStrategyFactory<InvoiceWorkflow> workStrategyFactory;

  private final IWorkflowPrepare<InvoiceWorkflow> workflowPrepare;

  public InvoiceWorkflowProcessService(@Autowired final IWorkflowDataService<InvoiceWorkflow> invoiceWorkflowDataService,
      @Autowired final IWorkflowSaveStrategyFactory<InvoiceWorkflow> workStrategyFactory,
      @Autowired final IWorkflowPrepare<InvoiceWorkflow> workflowPrepare) {

    this.invoiceWorkflowDataService = invoiceWorkflowDataService;
    this.workStrategyFactory = workStrategyFactory;
    this.workflowPrepare = workflowPrepare;
  }

  @Override
  public List<InvoiceWorkflow> create(final IWorkflowSaveRequest<InvoiceWorkflow> request, final Authentication authentication)
      throws WorkflowCustomizedException, MalformedURLException, IFlowMessageConversionFailureException {

    validate(request, authentication);

    final IWorkflowSaveStrategy<
        InvoiceWorkflow> workflowStrategy = this.workStrategyFactory.selectSaveWorkStrategy(request, authentication);

    workflowStrategy.process();

    final List<InvoiceWorkflow> result = workflowStrategy.getProceedWorkflowList();

    return workflowPrepare.prepareWorkflowList(authentication, result);
  }

  @Override
  public InvoiceWorkflow save(final IWorkflowSaveRequest<InvoiceWorkflow> request, final Authentication authentication)
      throws WorkflowCustomizedException, MalformedURLException, IFlowMessageConversionFailureException {

    logger.debug("Saving workflow with authentication {}", authentication);

    validate(request, authentication);

    final IWorkflowSaveStrategy<
        InvoiceWorkflow> workflowStrategy = this.workStrategyFactory.selectSaveWorkStrategy(request, authentication);

    workflowStrategy.process();

    final InvoiceWorkflow result = workflowStrategy.getSingleProceedWorkflow();

    return result;
  }

  @Override
  public void validate(final IWorkflowSaveRequest<InvoiceWorkflow> request, final Authentication authentication)
      throws WorkflowCustomizedException, MalformedURLException, IFlowMessageConversionFailureException {

    workflowPrepare.prepareWorkflow(authentication, request.getWorkflow());

    final IWorkflowSaveStrategy<InvoiceWorkflow> workflowStrategy = this.workStrategyFactory
        .selectValidationWorkStrategy(request,
            authentication);

    workflowStrategy.process();
  }

  @Override
  public InvoiceWorkflow getByIdentity(final String identity, final Authentication authentication)
      throws WorkflowCustomizedException, MalformedURLException, IFlowMessageConversionFailureException {

    logger.debug("get workflow by id {} with authentication {}", identity, authentication);

    final InvoiceWorkflow workflow = this.invoiceWorkflowDataService.getByIdentity(identity, authentication);

    return workflowPrepare.prepareWorkflow(authentication, workflow);
  }

  @Override
  public List<InvoiceWorkflow> getListForUser(final String identity, final int status, final Authentication authentication)
      throws WorkflowCustomizedException, MalformedURLException, IFlowMessageConversionFailureException {

    logger.debug("get workflow assigned to user id {} and has status {} with authentication {}", identity, status, authentication);

    final List<InvoiceWorkflow> list = this.invoiceWorkflowDataService.getListForUser(identity, status, authentication);

    return workflowPrepare.prepareWorkflowList(authentication, list);
  }

  @Override
  public List<InvoiceWorkflow> getListByIdentityList(final Set<String> identityList, final Authentication authentication)
      throws WorkflowCustomizedException, MalformedURLException, IFlowMessageConversionFailureException {

    logger.debug("get workflow list by id list with authentication {}", authentication);

    final List<InvoiceWorkflow> list = this.invoiceWorkflowDataService.getListByIdentityList(identityList, authentication);

    return workflowPrepare.prepareWorkflowList(authentication, list);
  }

}
