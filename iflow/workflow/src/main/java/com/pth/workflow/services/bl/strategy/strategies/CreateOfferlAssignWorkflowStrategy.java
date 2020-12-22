package com.pth.workflow.services.bl.strategy.strategies;


import com.pth.workflow.exceptions.WorkflowCustomizedException;
import com.pth.workflow.models.base.IWorkflowBaseEntity;
import com.pth.workflow.models.base.IWorkflowSaveRequest;
import com.pth.workflow.repositories.IWorkflowBaseRepository;
import com.pth.workflow.repositories.IWorkflowMessageRepository;
import com.pth.workflow.repositories.IWorkflowRepository;
import com.pth.workflow.services.bl.IDepartmentDataService;
import com.pth.workflow.services.bl.IGuiCachDataDataService;
import com.pth.workflow.services.bl.IWorkflowPrepare;
import com.pth.workflow.services.bl.strategy.steps.*;
import io.micronaut.security.authentication.Authentication;


public class CreateOfferlAssignWorkflowStrategy<W extends IWorkflowBaseEntity> extends AbstractWorkflowSaveStrategy<W> {

  public CreateOfferlAssignWorkflowStrategy(final IWorkflowSaveRequest<W> workflowCreateRequest,
                                            final String authorization,
                                            final IDepartmentDataService departmentDataService,
                                            final IWorkflowMessageRepository workflowMessageRepository,
                                            final IGuiCachDataDataService cachDataDataService,
                                            final IWorkflowBaseRepository<W> workflowRepository,
                                            final IWorkflowPrepare<W> workflowPrepare)
          throws WorkflowCustomizedException{

    super(workflowCreateRequest,
          authorization,
          departmentDataService,
          workflowMessageRepository,
          cachDataDataService,
          workflowRepository,
          workflowPrepare);

  }

  @Override
  public void setup() {

    steps.add(new ValidateWorkflowDetailStrategyStep<W>(this));
    steps.add(new ValidateAssignInSaveRequestStrategyStep<W>(this));
    steps.add(new InitializeWorkflowInitialActionStrategyStep<W>(this));
    steps.add(new InitializeWorkflowActiveActionStrategyStep<W>(this));
    steps.add(new ValidateWorkflowActiveActionStrategyStep<W>(this));
    steps.add(new ValidateWorkflowTypeStepStrategyStep<W>(this));
    steps.add(new ValidateCurrentStepExistsInWorkflowTypeStrategyStep<W>(this));
    steps.add(new CreateWorkflowInCoreStep<W>(this, true));
    steps.add(new CollectAssignedUserIdListStep<W>(this));
    steps.add(new SaveWorkflowOfferForAssignedUseresInCoreStep<W>(this));
    steps.add(new SendWorkflowOffersForUsersToProfileStep<W>(this));

  }

}
