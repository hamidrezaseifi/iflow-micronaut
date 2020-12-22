package com.pth.workflow.services.bl.strategy.strategies;

import com.pth.common.exceptions.IFlowMessageConversionFailureException;
import com.pth.workflow.exceptions.WorkflowCustomizedException;
import com.pth.workflow.models.base.IWorkflowBaseEntity;
import com.pth.workflow.models.base.IWorkflowSaveRequest;
import com.pth.workflow.repositories.IWorkflowBaseRepository;
import com.pth.workflow.repositories.IWorkflowMessageRepository;
import com.pth.workflow.services.bl.IDepartmentDataService;
import com.pth.workflow.services.bl.IGuiCachDataDataService;
import com.pth.workflow.services.bl.IWorkflowPrepare;
import com.pth.workflow.services.bl.strategy.steps.*;
import io.micronaut.security.authentication.Authentication;


public class SaveExistingWorkflowStrategy<W extends IWorkflowBaseEntity> extends AbstractWorkflowSaveStrategy<W> {

  public SaveExistingWorkflowStrategy(final IWorkflowSaveRequest<W> workflowCreateRequest,
                                      final String authorization,
                                      final IDepartmentDataService departmentDataService,
                                      final IWorkflowMessageRepository workflowMessageDataService,
                                      final IGuiCachDataDataService cachDataDataService,
                                      final IWorkflowBaseRepository<W> workflowDataService,
                                      final IWorkflowPrepare<W> workflowPrepare)
                                          throws WorkflowCustomizedException {

    super(workflowCreateRequest,
          authorization,
          departmentDataService,
          workflowMessageDataService,
          cachDataDataService,
          workflowDataService,
          workflowPrepare);

  }

  @Override
  public void setup() {

    steps.add(new ValidateWorkflowDetailStrategyStep<W>(this));
    steps.add(new ValidateWorkflowActiveActionStrategyStep<W>(this));
    steps.add(new ValidateWorkflowTypeStepStrategyStep<W>(this));
    steps.add(new ValidateCurrentStepExistsInWorkflowTypeStrategyStep<W>(this));
    steps.add(new ValidateWorkflowAssignedUserStrategyStep<W>(this));
    steps.add(new UpdateWorkflowInCoreStep(this, true));

  }

}
