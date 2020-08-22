package com.pth.workflow.services.bl.strategy.factory;

import java.net.MalformedURLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.pth.iflow.common.exceptions.EIFlowErrorType;
import com.pth.iflow.common.exceptions.IFlowMessageConversionFailureException;
import com.pth.iflow.workflow.bl.IDepartmentDataService;
import com.pth.iflow.workflow.bl.IGuiCachDataDataService;
import com.pth.iflow.workflow.bl.IWorkflowDataService;
import com.pth.iflow.workflow.bl.IWorkflowMessageDataService;
import com.pth.iflow.workflow.bl.IWorkflowPrepare;
import com.pth.iflow.workflow.bl.strategy.IWorkflowSaveStrategy;
import com.pth.iflow.workflow.bl.strategy.IWorkflowSaveStrategyFactory;
import com.pth.iflow.workflow.bl.strategy.strategies.ArchivingWorkflowStrategy;
import com.pth.iflow.workflow.bl.strategy.strategies.AssignWorkflowStrategy;
import com.pth.iflow.workflow.bl.strategy.strategies.CreateManualAssignWorkflowStrategy;
import com.pth.iflow.workflow.bl.strategy.strategies.CreateOfferlAssignWorkflowStrategy;
import com.pth.iflow.workflow.bl.strategy.strategies.DoneExistingWorkflowStrategy;
import com.pth.iflow.workflow.bl.strategy.strategies.SaveExistingWorkflowStrategy;
import com.pth.iflow.workflow.bl.strategy.strategies.validation.ArchivingWorkflowValidationStrategy;
import com.pth.iflow.workflow.bl.strategy.strategies.validation.AssignWorkflowValidationStrategy;
import com.pth.iflow.workflow.bl.strategy.strategies.validation.CreateManualAssignWorkflowValidationStrategy;
import com.pth.iflow.workflow.bl.strategy.strategies.validation.CreateOfferlAssignWorkflowValidationStrategy;
import com.pth.iflow.workflow.bl.strategy.strategies.validation.DoneExistingWorkflowValidationStrategy;
import com.pth.iflow.workflow.bl.strategy.strategies.validation.SaveExistingWorkflowValidationStrategy;
import com.pth.iflow.workflow.exceptions.WorkflowCustomizedException;
import com.pth.iflow.workflow.models.base.IWorkflowSaveRequest;
import com.pth.iflow.workflow.models.workflow.singletask.SingleTaskWorkflow;

@Service
public class SingleTaskWorkflowSaveStrategyFactory implements IWorkflowSaveStrategyFactory<SingleTaskWorkflow> {

  private static final Logger logger = LoggerFactory.getLogger(SingleTaskWorkflowSaveStrategyFactory.class);

  private final IWorkflowDataService<SingleTaskWorkflow> workflowDataService;

  private final IDepartmentDataService departmentDataService;

  private final IWorkflowMessageDataService workflowMessageDataService;

  private final IGuiCachDataDataService cachDataDataService;

  private final IWorkflowPrepare<SingleTaskWorkflow> invoiceWorkflowPrepare;

  public SingleTaskWorkflowSaveStrategyFactory(@Autowired final IWorkflowDataService<SingleTaskWorkflow> workflowDataService,
      @Autowired final IDepartmentDataService departmentDataService,
      @Autowired final IWorkflowMessageDataService workflowMessageDataService,
      @Autowired final IGuiCachDataDataService cachDataDataService,
      @Autowired final IWorkflowPrepare<SingleTaskWorkflow> invoiceWorkflowPrepare) {

    this.workflowDataService = workflowDataService;
    this.departmentDataService = departmentDataService;
    this.workflowMessageDataService = workflowMessageDataService;
    this.cachDataDataService = cachDataDataService;
    this.invoiceWorkflowPrepare = invoiceWorkflowPrepare;

  }

  @Override
  public IWorkflowSaveStrategy<SingleTaskWorkflow>
      selectSaveWorkStrategy(final IWorkflowSaveRequest<SingleTaskWorkflow> workflowSaveRequest, final Authentication authentication)
          throws WorkflowCustomizedException, MalformedURLException, IFlowMessageConversionFailureException {

    logger.debug("selecting save strategy for workflow");

    if (workflowSaveRequest.isCreateCommand()) {
      if (workflowSaveRequest.getWorkflow().getWorkflowType().isAssignTypeManual()) {
        logger.debug("The CreateManualAssignWorkflowStrategy is selected for workflow save");
        return new CreateManualAssignWorkflowStrategy<SingleTaskWorkflow>(workflowSaveRequest,
            authentication,
            departmentDataService,
            workflowMessageDataService,
            cachDataDataService,
            workflowDataService,
            invoiceWorkflowPrepare);
      }

      if (workflowSaveRequest.getWorkflow().getWorkflowType().isAssignTypeOffering()) {
        logger.debug("The CreateOfferlAssignWorkflowStrategy is selected for workflow save");
        return new CreateOfferlAssignWorkflowStrategy<SingleTaskWorkflow>(workflowSaveRequest,
            authentication,
            departmentDataService,
            workflowMessageDataService,
            cachDataDataService,
            workflowDataService,
            invoiceWorkflowPrepare);
      }
    }

    if (workflowSaveRequest.isArchiveCommand()) {
      logger.debug("The ArchivingWorkflowStrategy is selected for workflow save");
      return new ArchivingWorkflowStrategy<SingleTaskWorkflow>(workflowSaveRequest,
          authentication,
          departmentDataService,
          workflowMessageDataService,
          cachDataDataService,
          workflowDataService,
          invoiceWorkflowPrepare);
    }

    if (workflowSaveRequest.isSaveCommand()) {
      logger.debug("The SaveExistingWorkflowStrategy is selected for workflow save");
      return new SaveExistingWorkflowStrategy<SingleTaskWorkflow>(workflowSaveRequest,
          authentication,
          departmentDataService,
          workflowMessageDataService,
          cachDataDataService,
          workflowDataService,
          invoiceWorkflowPrepare);
    }

    if (workflowSaveRequest.isAssignCommand()) {
      logger.debug("The AssignWorkflowStrategy is selected for workflow save");
      return new AssignWorkflowStrategy<SingleTaskWorkflow>(workflowSaveRequest,
          authentication,
          departmentDataService,
          workflowMessageDataService,
          cachDataDataService,
          workflowDataService,
          invoiceWorkflowPrepare);
    }

    if (workflowSaveRequest.isDoneCommand()) {
      logger.debug("The DoneExistingWorkflowStrategy is selected for workflow save");
      return new DoneExistingWorkflowStrategy<SingleTaskWorkflow>(workflowSaveRequest,
          authentication,
          departmentDataService,
          workflowMessageDataService,
          cachDataDataService,
          workflowDataService,
          invoiceWorkflowPrepare);
    }

    throw new WorkflowCustomizedException("Unknown workflow save strategy ", EIFlowErrorType.UNKNOWN_WORKFLOW_STRATEGY);
  }

  @Override
  public IWorkflowSaveStrategy<SingleTaskWorkflow>
      selectValidationWorkStrategy(final IWorkflowSaveRequest<SingleTaskWorkflow> workflowSaveRequest, final Authentication authentication)
          throws WorkflowCustomizedException, MalformedURLException, IFlowMessageConversionFailureException {

    logger.debug("selecting validation strategy for workflow");

    if (workflowSaveRequest.isCreateCommand()) {
      if (workflowSaveRequest.getWorkflow().getWorkflowType().isAssignTypeManual()) {
        logger.debug("The CreateManualAssignWorkflowValidationStrategy is selected for workflow validation");
        return new CreateManualAssignWorkflowValidationStrategy<SingleTaskWorkflow>(workflowSaveRequest,
            authentication,
            departmentDataService,
            workflowMessageDataService,
            cachDataDataService,
            workflowDataService,
            invoiceWorkflowPrepare);
      }

      if (workflowSaveRequest.getWorkflow().getWorkflowType().isAssignTypeOffering()) {
        logger.debug("The CreateOfferlAssignWorkflowValidationStrategy is selected for workflow validation");
        return new CreateOfferlAssignWorkflowValidationStrategy<SingleTaskWorkflow>(workflowSaveRequest,
            authentication,
            departmentDataService,
            workflowMessageDataService,
            cachDataDataService,
            workflowDataService,
            invoiceWorkflowPrepare);
      }
    }

    if (workflowSaveRequest.isArchiveCommand()) {
      logger.debug("The ArchivingWorkflowValidationStrategy is selected for workflow validation");
      return new ArchivingWorkflowValidationStrategy<SingleTaskWorkflow>(workflowSaveRequest,
          authentication,
          departmentDataService,
          workflowMessageDataService,
          cachDataDataService,
          workflowDataService,
          invoiceWorkflowPrepare);
    }

    if (workflowSaveRequest.isSaveCommand()) {
      logger.debug("The SaveExistingWorkflowValidationStrategy is selected for workflow validation");
      return new SaveExistingWorkflowValidationStrategy<SingleTaskWorkflow>(workflowSaveRequest,
          authentication,
          departmentDataService,
          workflowMessageDataService,
          cachDataDataService,
          workflowDataService,
          invoiceWorkflowPrepare);
    }

    if (workflowSaveRequest.isAssignCommand()) {
      logger.debug("The AssignWorkflowValidationStrategy is selected for workflow validation");
      return new AssignWorkflowValidationStrategy<SingleTaskWorkflow>(workflowSaveRequest,
          authentication,
          departmentDataService,
          workflowMessageDataService,
          cachDataDataService,
          workflowDataService,
          invoiceWorkflowPrepare);
    }

    if (workflowSaveRequest.isDoneCommand()) {
      logger.debug("The DoneExistingWorkflowValidationStrategy is selected for workflow validation");
      return new DoneExistingWorkflowValidationStrategy<SingleTaskWorkflow>(workflowSaveRequest,
          authentication,
          departmentDataService,
          workflowMessageDataService,
          cachDataDataService,
          workflowDataService,
          invoiceWorkflowPrepare);
    }

    throw new WorkflowCustomizedException("Unknown workflow validation strategy ", EIFlowErrorType.UNKNOWN_WORKFLOW_STRATEGY);
  }
}
