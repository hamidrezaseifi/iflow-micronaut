package com.pth.workflow.services.bl.strategy.strategies;

import com.pth.common.exceptions.IFlowMessageConversionFailureException;
import com.pth.workflow.models.base.IWorkflowBaseEntity;
import com.pth.workflow.models.base.IWorkflowSaveRequest;
import com.pth.workflow.repositories.IWorkflowBaseRepository;
import com.pth.workflow.repositories.IWorkflowMessageRepository;
import com.pth.workflow.services.bl.IDepartmentDataService;
import com.pth.workflow.services.bl.IGuiCachDataDataService;
import com.pth.workflow.services.bl.IWorkflowPrepare;
import io.micronaut.security.authentication.Authentication;


public class SaveExistingWorkflowStrategy<W extends IWorkflowBaseEntity> extends AbstractWorkflowSaveStrategy<W> {

  public SaveExistingWorkflowStrategy(final IWorkflowSaveRequest<W> workflowCreateRequest,
      final Authentication authentication,
      final IDepartmentDataService departmentDataService,
      final IWorkflowMessageRepository workflowMessageDataService,
      final IGuiCachDataDataService cachDataDataService,
      final IWorkflowBaseRepository<W> workflowDataService,
      final IWorkflowPrepare<W> workflowPrepare)
      throws IFlowMessageConversionFailureException {

    super(workflowCreateRequest,
        authentication,
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
    steps.add(new SaveWorkflowInCoreStep<W>(this, true));

  }

}
