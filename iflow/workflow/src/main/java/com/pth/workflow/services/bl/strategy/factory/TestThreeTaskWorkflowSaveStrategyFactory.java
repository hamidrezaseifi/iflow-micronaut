package com.pth.workflow.services.bl.strategy.factory;


import com.pth.common.exceptions.EIFlowErrorType;
import com.pth.workflow.entities.TestThreeTaskWorkflowEntity;
import com.pth.workflow.exceptions.WorkflowCustomizedException;
import com.pth.workflow.models.base.IWorkflowSaveRequest;
import com.pth.workflow.repositories.ITestThreeTaskWorkflowRepository;
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
public class TestThreeTaskWorkflowSaveStrategyFactory implements
                                                      IWorkflowSaveStrategyFactory<TestThreeTaskWorkflowEntity> {

  private static final Logger logger = LoggerFactory.getLogger(TestThreeTaskWorkflowSaveStrategyFactory.class);

  private final ITestThreeTaskWorkflowRepository testThreeTaskWorkflowRepository;

  private final IDepartmentDataService departmentDataService;

  private final IWorkflowMessageRepository workflowMessageRepository;

  private final IGuiCachDataDataService cachDataDataService;

  private final IWorkflowPrepare<TestThreeTaskWorkflowEntity> testThreeWorkflowPrepare;

  public TestThreeTaskWorkflowSaveStrategyFactory(ITestThreeTaskWorkflowRepository testThreeTaskWorkflowRepository,
                                            IDepartmentDataService departmentDataService,
                                            IWorkflowMessageRepository workflowMessageRepository,
                                            IGuiCachDataDataService cachDataDataService,
                                            IWorkflowPrepare<TestThreeTaskWorkflowEntity> testThreeWorkflowPrepare) {

    this.testThreeTaskWorkflowRepository = testThreeTaskWorkflowRepository;
    this.departmentDataService = departmentDataService;
    this.workflowMessageRepository = workflowMessageRepository;
    this.cachDataDataService = cachDataDataService;
    this.testThreeWorkflowPrepare = testThreeWorkflowPrepare;

  }

  @Override
  public IWorkflowSaveStrategy<TestThreeTaskWorkflowEntity>
      selectSaveWorkStrategy(final IWorkflowSaveRequest<TestThreeTaskWorkflowEntity> workflowSaveRequest,
                             final String authorization)
          throws WorkflowCustomizedException {

    logger.debug("selecting save strategy for workflow");

    if (workflowSaveRequest.isCreateCommand()) {
      if (workflowSaveRequest.getWorkflow().getWorkflowType().isAssignTypeManual()) {
        logger.debug("The CreateManualAssignWorkflowStrategy is selected for workflow save");
        return new CreateManualAssignWorkflowStrategy<TestThreeTaskWorkflowEntity>(workflowSaveRequest,
                                                                                   authorization,
                                                                                   departmentDataService,
                                                                                   workflowMessageRepository,
                                                                                   cachDataDataService,
                                                                                   testThreeTaskWorkflowRepository,
                                                                                   testThreeWorkflowPrepare);
      }

      if (workflowSaveRequest.getWorkflow().getWorkflowType().isAssignTypeOffering()) {
        logger.debug("The CreateOfferlAssignWorkflowStrategy is selected for workflow save");
        return new CreateOfferlAssignWorkflowStrategy<TestThreeTaskWorkflowEntity>(workflowSaveRequest,
                                                                                   authorization,
                                                                                   departmentDataService,
                                                                                   workflowMessageRepository,
                                                                                   cachDataDataService,
                                                                                   testThreeTaskWorkflowRepository,
                                                                                   testThreeWorkflowPrepare);
      }
    }

    if (workflowSaveRequest.isArchiveCommand()) {
      logger.debug("The ArchivingWorkflowStrategy is selected for workflow save");
      return new ArchivingWorkflowStrategy<TestThreeTaskWorkflowEntity>(workflowSaveRequest,
                                                                        authorization,
                                                                        departmentDataService,
                                                                        workflowMessageRepository,
                                                                        cachDataDataService,
                                                                        testThreeTaskWorkflowRepository,
                                                                        testThreeWorkflowPrepare);
    }

    if (workflowSaveRequest.isSaveCommand()) {
      logger.debug("The SaveExistingWorkflowStrategy is selected for workflow save");
      return new SaveExistingWorkflowStrategy<TestThreeTaskWorkflowEntity>(workflowSaveRequest,
          authorization,
          departmentDataService,
          workflowMessageRepository,
          cachDataDataService,
          testThreeTaskWorkflowRepository,
          testThreeWorkflowPrepare);
    }

    if (workflowSaveRequest.isAssignCommand()) {
      logger.debug("The AssignWorkflowStrategy is selected for workflow save");
      return new AssignWorkflowStrategy<TestThreeTaskWorkflowEntity>(workflowSaveRequest,
          authorization,
          departmentDataService,
          workflowMessageRepository,
          cachDataDataService,
          testThreeTaskWorkflowRepository,
          testThreeWorkflowPrepare);
    }

    if (workflowSaveRequest.isDoneCommand()) {
      logger.debug("The DoneExistingWorkflowStrategy is selected for workflow save");
      return new DoneExistingWorkflowStrategy<TestThreeTaskWorkflowEntity>(workflowSaveRequest,
          authorization,
          departmentDataService,
          workflowMessageRepository,
          cachDataDataService,
          testThreeTaskWorkflowRepository,
          testThreeWorkflowPrepare);
    }

    throw new WorkflowCustomizedException("Unknown workflow save strategy ", EIFlowErrorType.UNKNOWN_WORKFLOW_STRATEGY);
  }

  @Override
  public IWorkflowSaveStrategy<TestThreeTaskWorkflowEntity> selectValidationWorkStrategy(
      final IWorkflowSaveRequest<TestThreeTaskWorkflowEntity> workflowSaveRequest,
      final String authorization)
          throws WorkflowCustomizedException {

    logger.debug("selecting validation strategy for workflow");

    if (workflowSaveRequest.isCreateCommand()) {
      if (workflowSaveRequest.getWorkflow().getWorkflowType().isAssignTypeManual()) {
        logger.debug("The CreateManualAssignWorkflowValidationStrategy is selected for workflow validation");
        return new CreateManualAssignWorkflowValidationStrategy<TestThreeTaskWorkflowEntity>(workflowSaveRequest,
                                                                                             authorization,
                                                                                             departmentDataService,
                                                                                             workflowMessageRepository,
                                                                                             cachDataDataService,
                                                                                             testThreeTaskWorkflowRepository,
                                                                                             testThreeWorkflowPrepare);
      }

      if (workflowSaveRequest.getWorkflow().getWorkflowType().isAssignTypeOffering()) {
        logger.debug("The CreateOfferlAssignWorkflowValidationStrategy is selected for workflow validation");
        return new CreateOfferlAssignWorkflowValidationStrategy<TestThreeTaskWorkflowEntity>(workflowSaveRequest,
                                                                                             authorization,
                                                                                             departmentDataService,
                                                                                             workflowMessageRepository,
                                                                                             cachDataDataService,
                                                                                             testThreeTaskWorkflowRepository,
                                                                                             testThreeWorkflowPrepare);
      }
    }

    if (workflowSaveRequest.isArchiveCommand()) {
      logger.debug("The ArchivingWorkflowValidationStrategy is selected for workflow validation");
      return new ArchivingWorkflowValidationStrategy<TestThreeTaskWorkflowEntity>(workflowSaveRequest,
          authorization,
          departmentDataService,
          workflowMessageRepository,
          cachDataDataService,
          testThreeTaskWorkflowRepository,
          testThreeWorkflowPrepare);
    }

    if (workflowSaveRequest.isSaveCommand()) {
      logger.debug("The SaveExistingWorkflowValidationStrategy is selected for workflow validation");
      return new SaveExistingWorkflowValidationStrategy<TestThreeTaskWorkflowEntity>(workflowSaveRequest,
          authorization,
          departmentDataService,
          workflowMessageRepository,
          cachDataDataService,
          testThreeTaskWorkflowRepository,
          testThreeWorkflowPrepare);
    }

    if (workflowSaveRequest.isAssignCommand()) {
      logger.debug("The AssignWorkflowValidationStrategy is selected for workflow validation");
      return new AssignWorkflowValidationStrategy<TestThreeTaskWorkflowEntity>(workflowSaveRequest,
          authorization,
          departmentDataService,
          workflowMessageRepository,
          cachDataDataService,
          testThreeTaskWorkflowRepository,
          testThreeWorkflowPrepare);
    }

    if (workflowSaveRequest.isDoneCommand()) {
      logger.debug("The DoneExistingWorkflowValidationStrategy is selected for workflow validation");
      return new DoneExistingWorkflowValidationStrategy<TestThreeTaskWorkflowEntity>(workflowSaveRequest,
          authorization,
          departmentDataService,
          workflowMessageRepository,
          cachDataDataService,
          testThreeTaskWorkflowRepository,
          testThreeWorkflowPrepare);
    }

    throw new WorkflowCustomizedException("Unknown workflow validation strategy ", EIFlowErrorType.UNKNOWN_WORKFLOW_STRATEGY);
  }
}
