package com.pth.workflow.services.bl.strategy.strategies;

import java.net.MalformedURLException;

import org.springframework.security.core.Authentication;

import com.pth.iflow.common.exceptions.IFlowMessageConversionFailureException;
import com.pth.iflow.workflow.bl.IDepartmentDataService;
import com.pth.iflow.workflow.bl.IGuiCachDataDataService;
import com.pth.iflow.workflow.bl.IWorkflowDataService;
import com.pth.iflow.workflow.bl.IWorkflowMessageDataService;
import com.pth.iflow.workflow.bl.IWorkflowPrepare;
import com.pth.iflow.workflow.bl.strategy.steps.CollectAssignedUserIdListStep;
import com.pth.iflow.workflow.bl.strategy.steps.InitializeWorkflowActiveActionStrategyStep;
import com.pth.iflow.workflow.bl.strategy.steps.InitializeWorkflowInitialActionStrategyStep;
import com.pth.iflow.workflow.bl.strategy.steps.SaveWorkflowForAssignedUseresInCoreStep;
import com.pth.iflow.workflow.bl.strategy.steps.SaveWorkflowOfferForAssignedUseresInCoreStep;
import com.pth.iflow.workflow.bl.strategy.steps.SendWorkflowOffersForUsersToProfileStep;
import com.pth.iflow.workflow.bl.strategy.steps.ValidateAssignInSaveRequestStrategyStep;
import com.pth.iflow.workflow.bl.strategy.steps.ValidateCurrentStepExistsInWorkflowTypeStrategyStep;
import com.pth.iflow.workflow.bl.strategy.steps.ValidateWorkflowActiveActionStrategyStep;
import com.pth.iflow.workflow.bl.strategy.steps.ValidateWorkflowDetailStrategyStep;
import com.pth.iflow.workflow.bl.strategy.steps.ValidateWorkflowTypeStepStrategyStep;
import com.pth.iflow.workflow.exceptions.WorkflowCustomizedException;
import com.pth.iflow.workflow.models.base.IWorkflow;
import com.pth.iflow.workflow.models.base.IWorkflowSaveRequest;

public class CreateManualAssignWorkflowStrategy<W extends IWorkflow> extends AbstractWorkflowSaveStrategy<W> {

  public CreateManualAssignWorkflowStrategy(final IWorkflowSaveRequest<W> workflowCreateRequest, final Authentication authentication,
      final IDepartmentDataService departmentDataService, final IWorkflowMessageDataService workflowMessageDataService,
      final IGuiCachDataDataService cachDataDataService, final IWorkflowDataService<W> workflowDataService,
      final IWorkflowPrepare<W> workflowPrepare)
      throws WorkflowCustomizedException, MalformedURLException, IFlowMessageConversionFailureException {

    super(workflowCreateRequest,authentication,departmentDataService,workflowMessageDataService,cachDataDataService,workflowDataService,
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
    steps.add(new SaveWorkflowForAssignedUseresInCoreStep<W>(this));
    steps.add(new SaveWorkflowOfferForAssignedUseresInCoreStep<W>(this));
    steps.add(new SendWorkflowOffersForUsersToProfileStep<W>(this));

  }

}
