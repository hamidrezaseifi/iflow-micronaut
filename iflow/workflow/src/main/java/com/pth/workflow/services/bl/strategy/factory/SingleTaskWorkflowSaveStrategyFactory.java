package com.pth.workflow.services.bl.strategy.factory;


import com.pth.common.exceptions.EIFlowErrorType;
import com.pth.workflow.entities.workflow.InvoiceWorkflowEntity;
import com.pth.workflow.entities.workflow.SingleTaskWorkflowEntity;
import com.pth.workflow.exceptions.WorkflowCustomizedException;
import com.pth.workflow.models.base.IWorkflowSaveRequest;
import com.pth.workflow.repositories.IInvoiceWorkflowRepository;
import com.pth.workflow.repositories.ISingleTaskWorkflowRepository;
import com.pth.workflow.repositories.IWorkflowMessageRepository;
import com.pth.workflow.services.bl.IDepartmentDataService;
import com.pth.workflow.services.bl.IGuiCachDataDataService;
import com.pth.workflow.services.bl.IWorkflowPrepare;
import com.pth.workflow.services.bl.strategy.IWorkflowSaveStrategy;
import com.pth.workflow.services.bl.strategy.IWorkflowSaveStrategyFactory;
import com.pth.workflow.services.bl.strategy.strategies.*;
import com.pth.workflow.services.bl.strategy.strategies.validation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;

@Singleton
public class SingleTaskWorkflowSaveStrategyFactory implements IWorkflowSaveStrategyFactory<SingleTaskWorkflowEntity> {

  private static final Logger logger = LoggerFactory.getLogger(SingleTaskWorkflowSaveStrategyFactory.class);

  private final ISingleTaskWorkflowRepository singleTaskWorkflowRepository;

  private final IDepartmentDataService departmentDataService;

  private final IWorkflowMessageRepository workflowMessageRepository;

  private final IGuiCachDataDataService cachDataDataService;

  private final IWorkflowPrepare<SingleTaskWorkflowEntity> singleTasWorkflowPrepare;

  public SingleTaskWorkflowSaveStrategyFactory(ISingleTaskWorkflowRepository singleTaskWorkflowRepository,
                                            IDepartmentDataService departmentDataService,
                                            IWorkflowMessageRepository workflowMessageRepository,
                                            IGuiCachDataDataService cachDataDataService,
                                            IWorkflowPrepare<SingleTaskWorkflowEntity> singleTasWorkflowPrepare) {

    this.singleTaskWorkflowRepository = singleTaskWorkflowRepository;
    this.departmentDataService = departmentDataService;
    this.workflowMessageRepository = workflowMessageRepository;
    this.cachDataDataService = cachDataDataService;
    this.singleTasWorkflowPrepare = singleTasWorkflowPrepare;

  }

  @Override
  public IWorkflowSaveStrategy<SingleTaskWorkflowEntity>
      selectSaveWorkStrategy(final IWorkflowSaveRequest<SingleTaskWorkflowEntity> workflowSaveRequest,
                             final String authorization)
          throws WorkflowCustomizedException {

    logger.debug("selecting save strategy for workflow");

    if (workflowSaveRequest.isCreateCommand()) {
      if (workflowSaveRequest.getWorkflow().getWorkflowType().isAssignTypeManual()) {
        logger.debug("The CreateManualAssignWorkflowStrategy is selected for workflow save");
        return new CreateManualAssignWorkflowStrategy<SingleTaskWorkflowEntity>(workflowSaveRequest,
                                                                                authorization,
                                                                                departmentDataService,
                                                                                workflowMessageRepository,
                                                                                cachDataDataService,
                                                                                singleTaskWorkflowRepository,
                                                                                singleTasWorkflowPrepare);
      }

      if (workflowSaveRequest.getWorkflow().getWorkflowType().isAssignTypeOffering()) {
        logger.debug("The CreateOfferlAssignWorkflowStrategy is selected for workflow save");
        return new CreateOfferlAssignWorkflowStrategy<SingleTaskWorkflowEntity>(workflowSaveRequest,
                                                                                authorization,
                                                                                departmentDataService,
                                                                                workflowMessageRepository,
                                                                                cachDataDataService,
                                                                                singleTaskWorkflowRepository,
                                                                                singleTasWorkflowPrepare);
      }
    }

    if (workflowSaveRequest.isArchiveCommand()) {
      logger.debug("The ArchivingWorkflowStrategy is selected for workflow save");
      return new ArchivingWorkflowStrategy<SingleTaskWorkflowEntity>(workflowSaveRequest,
                                                                     authorization,
                                                                     departmentDataService,
                                                                     workflowMessageRepository,
                                                                     cachDataDataService,
                                                                     singleTaskWorkflowRepository,
                                                                     singleTasWorkflowPrepare);
    }

    if (workflowSaveRequest.isSaveCommand()) {
      logger.debug("The SaveExistingWorkflowStrategy is selected for workflow save");
      return new SaveExistingWorkflowStrategy<SingleTaskWorkflowEntity>(workflowSaveRequest,
                                                                        authorization,
                                                                        departmentDataService,
                                                                        workflowMessageRepository,
                                                                        cachDataDataService,
                                                                        singleTaskWorkflowRepository,
                                                                        singleTasWorkflowPrepare);
    }

    if (workflowSaveRequest.isAssignCommand()) {
      logger.debug("The AssignWorkflowStrategy is selected for workflow save");
      return new AssignWorkflowStrategy<SingleTaskWorkflowEntity>(workflowSaveRequest,
                                                                  authorization,
                                                                  departmentDataService,
                                                                  workflowMessageRepository,
                                                                  cachDataDataService,
                                                                  singleTaskWorkflowRepository,
                                                                  singleTasWorkflowPrepare);
    }

    if (workflowSaveRequest.isDoneCommand()) {
      logger.debug("The DoneExistingWorkflowStrategy is selected for workflow save");
      return new DoneExistingWorkflowStrategy<SingleTaskWorkflowEntity>(workflowSaveRequest,
          authorization,
          departmentDataService,
          workflowMessageRepository,
          cachDataDataService,
          singleTaskWorkflowRepository,
          singleTasWorkflowPrepare);
    }

    throw new WorkflowCustomizedException("Unknown workflow save strategy ", EIFlowErrorType.UNKNOWN_WORKFLOW_STRATEGY);
  }

  @Override
  public IWorkflowSaveStrategy<SingleTaskWorkflowEntity>
      selectValidationWorkStrategy(final IWorkflowSaveRequest<SingleTaskWorkflowEntity> workflowSaveRequest,
                                   final String authorization)
          throws WorkflowCustomizedException {

    logger.debug("selecting validation strategy for workflow");

    if (workflowSaveRequest.isCreateCommand()) {
      if (workflowSaveRequest.getWorkflow().getWorkflowType().isAssignTypeManual()) {
        logger.debug("The CreateManualAssignWorkflowValidationStrategy is selected for workflow validation");
        return new CreateManualAssignWorkflowValidationStrategy<SingleTaskWorkflowEntity>(workflowSaveRequest,
                                                                                          authorization,
                                                                                          departmentDataService,
                                                                                          workflowMessageRepository,
                                                                                          cachDataDataService,
                                                                                          singleTaskWorkflowRepository,
                                                                                          singleTasWorkflowPrepare);
      }

      if (workflowSaveRequest.getWorkflow().getWorkflowType().isAssignTypeOffering()) {
        logger.debug("The CreateOfferlAssignWorkflowValidationStrategy is selected for workflow validation");
        return new CreateOfferlAssignWorkflowValidationStrategy<SingleTaskWorkflowEntity>(workflowSaveRequest,
                                                                                          authorization,
                                                                                          departmentDataService,
                                                                                          workflowMessageRepository,
                                                                                          cachDataDataService,
                                                                                          singleTaskWorkflowRepository,
                                                                                          singleTasWorkflowPrepare);
      }
    }

    if (workflowSaveRequest.isArchiveCommand()) {
      logger.debug("The ArchivingWorkflowValidationStrategy is selected for workflow validation");
      return new ArchivingWorkflowValidationStrategy<SingleTaskWorkflowEntity>(workflowSaveRequest,
                                                                               authorization,
                                                                               departmentDataService,
                                                                               workflowMessageRepository,
                                                                               cachDataDataService,
                                                                               singleTaskWorkflowRepository,
                                                                               singleTasWorkflowPrepare);
    }

    if (workflowSaveRequest.isSaveCommand()) {
      logger.debug("The SaveExistingWorkflowValidationStrategy is selected for workflow validation");
      return new SaveExistingWorkflowValidationStrategy<SingleTaskWorkflowEntity>(workflowSaveRequest,
                                                                                  authorization,
                                                                                  departmentDataService,
                                                                                  workflowMessageRepository,
                                                                                  cachDataDataService,
                                                                                  singleTaskWorkflowRepository,
                                                                                  singleTasWorkflowPrepare);
    }

    if (workflowSaveRequest.isAssignCommand()) {
      logger.debug("The AssignWorkflowValidationStrategy is selected for workflow validation");
      return new AssignWorkflowValidationStrategy<SingleTaskWorkflowEntity>(workflowSaveRequest,
                                                                            authorization,
                                                                            departmentDataService,
                                                                            workflowMessageRepository,
                                                                            cachDataDataService,
                                                                            singleTaskWorkflowRepository,
                                                                            singleTasWorkflowPrepare);
    }

    if (workflowSaveRequest.isDoneCommand()) {
      logger.debug("The DoneExistingWorkflowValidationStrategy is selected for workflow validation");
      return new DoneExistingWorkflowValidationStrategy<SingleTaskWorkflowEntity>(workflowSaveRequest,
          authorization,
          departmentDataService,
          workflowMessageRepository,
          cachDataDataService,
          singleTaskWorkflowRepository,
          singleTasWorkflowPrepare);
    }

    throw new WorkflowCustomizedException("Unknown workflow validation strategy ", EIFlowErrorType.UNKNOWN_WORKFLOW_STRATEGY);
  }
}
