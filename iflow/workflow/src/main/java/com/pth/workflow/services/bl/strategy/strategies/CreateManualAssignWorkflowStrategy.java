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

import java.net.MalformedURLException;


public class CreateManualAssignWorkflowStrategy<W extends IWorkflowBaseEntity> extends AbstractWorkflowSaveStrategy<W> {

  public CreateManualAssignWorkflowStrategy(IWorkflowSaveRequest<W> workflowCreateRequest,
                                            final String authorization,
                                            IDepartmentDataService departmentDataService,
                                            IWorkflowMessageRepository workflowMessageRepository,
                                            IGuiCachDataDataService cachDataDataService,
                                            IWorkflowBaseRepository<W> workflowBaseRepository,
                                            IWorkflowPrepare<W> workflowPrepare)
          throws WorkflowCustomizedException {

    super(workflowCreateRequest,
          authorization,
          departmentDataService,
          workflowMessageRepository,
          cachDataDataService,
          workflowBaseRepository,
          workflowPrepare);

  }

  @Override
  public void setup() {

    steps.add(new ValidateWorkflowDetailStrategyStep<W>(this));
    steps.add(new ValidateAssignInSaveRequestStrategyStep<W>(this));
    steps.add(new CollectAssignedUserIdListStep<W>(this));
    steps.add(new ValidateWorkflowTypeStepStrategyStep<W>(this));
    steps.add(new ValidateCurrentStepExistsInWorkflowTypeStrategyStep<W>(this));
    steps.add(new InitializeWorkflowInitialActionStrategyStep<W>(this));
    steps.add(new InitializeWorkflowActiveActionStrategyStep<W>(this));
    steps.add(new ValidateWorkflowActiveActionStrategyStep<W>(this));
    steps.add(new CollectAssignedUserIdListStep<W>(this));
    steps.add(new CreateWorkflowForAssignedUseresInCoreStep<W>(this));
    steps.add(new SaveWorkflowOfferForAssignedUseresInCoreStep<W>(this));
    steps.add(new SendWorkflowOffersForUsersToProfileStep<W>(this));

  }

}
