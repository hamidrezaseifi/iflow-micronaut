package com.pth.workflow.services.bl.strategy.factory;

import java.net.MalformedURLException;

import com.pth.common.exceptions.EIFlowErrorType;
import com.pth.workflow.entities.workflow.InvoiceWorkflowEntity;
import com.pth.workflow.exceptions.WorkflowCustomizedException;
import com.pth.workflow.models.base.IWorkflowSaveRequest;
import com.pth.workflow.repositories.IInvoiceWorkflowRepository;
import com.pth.workflow.repositories.IWorkflowMessageRepository;
import com.pth.workflow.services.bl.IDepartmentDataService;
import com.pth.workflow.services.bl.IGuiCachDataDataService;
import com.pth.workflow.services.bl.IWorkflowPrepare;
import com.pth.workflow.services.bl.strategy.IWorkflowSaveStrategy;
import com.pth.workflow.services.bl.strategy.IWorkflowSaveStrategyFactory;
import com.pth.workflow.services.bl.strategy.strategies.*;
import com.pth.workflow.services.bl.strategy.strategies.validation.*;
import io.micronaut.security.authentication.Authentication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;


@Singleton
public class InvoiceWorkflowSaveStrategyFactory implements IWorkflowSaveStrategyFactory<InvoiceWorkflowEntity> {

  private static final Logger logger = LoggerFactory.getLogger(InvoiceWorkflowSaveStrategyFactory.class);

  private final IInvoiceWorkflowRepository invoiceWorkflowRepository;

  private final IDepartmentDataService departmentDataService;

  private final IWorkflowMessageRepository workflowMessageRepository;

  private final IGuiCachDataDataService cachDataDataService;

  private final IWorkflowPrepare<InvoiceWorkflowEntity> invoiceWorkflowPrepare;

  public InvoiceWorkflowSaveStrategyFactory(IInvoiceWorkflowRepository invoiceWorkflowRepository,
                                            IDepartmentDataService departmentDataService,
                                            IWorkflowMessageRepository workflowMessageRepository,
                                            IGuiCachDataDataService cachDataDataService,
                                            IWorkflowPrepare<InvoiceWorkflowEntity> invoiceWorkflowPrepare) {

    this.invoiceWorkflowRepository = invoiceWorkflowRepository;
    this.departmentDataService = departmentDataService;
    this.workflowMessageRepository = workflowMessageRepository;
    this.cachDataDataService = cachDataDataService;
    this.invoiceWorkflowPrepare = invoiceWorkflowPrepare;

  }

  @Override
  public IWorkflowSaveStrategy<InvoiceWorkflowEntity>
    selectSaveWorkStrategy(final IWorkflowSaveRequest<InvoiceWorkflowEntity> workflowSaveRequest,
                           final String authorization) throws WorkflowCustomizedException {

    logger.debug("selecting save strategy for workflow");

    if (workflowSaveRequest.isCreateCommand()) {
      if (workflowSaveRequest.getWorkflow().getWorkflowType().isAssignTypeManual()) {
        logger.debug("The CreateManualAssignWorkflowStrategy is selected for workflow save");
        return new CreateManualAssignWorkflowStrategy<InvoiceWorkflowEntity>(workflowSaveRequest,
                                                                             authorization,
                                                                             departmentDataService,
                                                                             workflowMessageRepository,
                                                                             cachDataDataService,
                                                                             invoiceWorkflowRepository,
                                                                             invoiceWorkflowPrepare);
      }

      if (workflowSaveRequest.getWorkflow().getWorkflowType().isAssignTypeOffering()) {
        logger.debug("The CreateOfferlAssignWorkflowStrategy is selected for workflow save");
        return new CreateOfferlAssignWorkflowStrategy<InvoiceWorkflowEntity>(workflowSaveRequest,
                                                                             authorization,
                                                                             departmentDataService,
                                                                             workflowMessageRepository,
                                                                             cachDataDataService,
                                                                             invoiceWorkflowRepository,
                                                                             invoiceWorkflowPrepare);
      }
    }

    if (workflowSaveRequest.isArchiveCommand()) {
      logger.debug("The ArchivingWorkflowStrategy is selected for workflow save");
      return new ArchivingWorkflowStrategy<InvoiceWorkflowEntity>(workflowSaveRequest,
                                                                  authorization,
                                                                  departmentDataService,
                                                                  workflowMessageRepository,
                                                                  cachDataDataService,
                                                                  invoiceWorkflowRepository,
                                                                  invoiceWorkflowPrepare);
    }

    if (workflowSaveRequest.isSaveCommand()) {
      logger.debug("The SaveExistingWorkflowStrategy is selected for workflow save");
      return new SaveExistingWorkflowStrategy<InvoiceWorkflowEntity>(workflowSaveRequest,
                                                                     authorization,
                                                                     departmentDataService,
                                                                     workflowMessageRepository,
                                                                     cachDataDataService,
                                                                     invoiceWorkflowRepository,
                                                                     invoiceWorkflowPrepare);
    }

    if (workflowSaveRequest.isAssignCommand()) {
      logger.debug("The AssignWorkflowStrategy is selected for workflow save");
      return new AssignWorkflowStrategy<InvoiceWorkflowEntity>(workflowSaveRequest,
                                                               authorization,
                                                               departmentDataService,
                                                               workflowMessageRepository,
                                                               cachDataDataService,
                                                               invoiceWorkflowRepository,
                                                               invoiceWorkflowPrepare);
    }

    if (workflowSaveRequest.isDoneCommand()) {
      logger.debug("The DoneExistingWorkflowStrategy is selected for workflow save");
      return new DoneExistingWorkflowStrategy<InvoiceWorkflowEntity>(workflowSaveRequest,
          authorization,
          departmentDataService,
          workflowMessageRepository,
          cachDataDataService,
          invoiceWorkflowRepository,
          invoiceWorkflowPrepare);
    }

    throw new WorkflowCustomizedException("Unknown workflow save strategy ", EIFlowErrorType.UNKNOWN_WORKFLOW_STRATEGY);
  }

  @Override
  public IWorkflowSaveStrategy<InvoiceWorkflowEntity>
      selectValidationWorkStrategy(final IWorkflowSaveRequest<InvoiceWorkflowEntity> workflowSaveRequest, final String authorization)
          throws WorkflowCustomizedException {

    logger.debug("selecting validation strategy for workflow");

    if (workflowSaveRequest.isCreateCommand()) {
      if (workflowSaveRequest.getWorkflow().getWorkflowType().isAssignTypeManual()) {
        logger.debug("The CreateManualAssignWorkflowValidationStrategy is selected for workflow validation");
        return new CreateManualAssignWorkflowValidationStrategy<InvoiceWorkflowEntity>(workflowSaveRequest,
                                                                                       authorization,
                                                                                       departmentDataService,
                                                                                       workflowMessageRepository,
                                                                                       cachDataDataService,
                                                                                       invoiceWorkflowRepository,
                                                                                       invoiceWorkflowPrepare);
      }

      if (workflowSaveRequest.getWorkflow().getWorkflowType().isAssignTypeOffering()) {
        logger.debug("The CreateOfferlAssignWorkflowValidationStrategy is selected for workflow validation");
        return new CreateOfferlAssignWorkflowValidationStrategy<InvoiceWorkflowEntity>(workflowSaveRequest,
                                                                                       authorization,
                                                                                       departmentDataService,
                                                                                       workflowMessageRepository,
                                                                                       cachDataDataService,
                                                                                       invoiceWorkflowRepository,
                                                                                       invoiceWorkflowPrepare);
      }
    }

    if (workflowSaveRequest.isArchiveCommand()) {
      logger.debug("The ArchivingWorkflowValidationStrategy is selected for workflow validation");
      return new ArchivingWorkflowValidationStrategy<InvoiceWorkflowEntity>(workflowSaveRequest,
                                                                            authorization,
                                                                            departmentDataService,
                                                                            workflowMessageRepository,
                                                                            cachDataDataService,
                                                                            invoiceWorkflowRepository,
                                                                            invoiceWorkflowPrepare);
    }

    if (workflowSaveRequest.isSaveCommand()) {
      logger.debug("The SaveExistingWorkflowValidationStrategy is selected for workflow validation");
      return new SaveExistingWorkflowValidationStrategy<InvoiceWorkflowEntity>(workflowSaveRequest,
                                                                               authorization,
                                                                               departmentDataService,
                                                                               workflowMessageRepository,
                                                                               cachDataDataService,
                                                                               invoiceWorkflowRepository,
                                                                               invoiceWorkflowPrepare);
    }

    if (workflowSaveRequest.isAssignCommand()) {
      logger.debug("The AssignWorkflowValidationStrategy is selected for workflow validation");
      return new AssignWorkflowValidationStrategy<InvoiceWorkflowEntity>(workflowSaveRequest,
                                                                         authorization,
                                                                         departmentDataService,
                                                                         workflowMessageRepository,
                                                                         cachDataDataService,
                                                                         invoiceWorkflowRepository,
                                                                         invoiceWorkflowPrepare);
    }

    if (workflowSaveRequest.isDoneCommand()) {
      logger.debug("The DoneExistingWorkflowValidationStrategy is selected for workflow validation");
      return new DoneExistingWorkflowValidationStrategy<InvoiceWorkflowEntity>(workflowSaveRequest,
          authorization,
          departmentDataService,
          workflowMessageRepository,
          cachDataDataService,
          invoiceWorkflowRepository,
          invoiceWorkflowPrepare);
    }

    throw new WorkflowCustomizedException("Unknown workflow validation strategy ", EIFlowErrorType.UNKNOWN_WORKFLOW_STRATEGY);
  }
}
