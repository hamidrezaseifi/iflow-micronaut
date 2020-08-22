package com.pth.workflow.services.bl.impl.workflowservice.singletask;

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
import com.pth.iflow.workflow.models.workflow.singletask.SingleTaskWorkflow;

@Service
public class SingleTaskWorkflowProcessService implements IWorkflowProcessService<SingleTaskWorkflow> {

  private final Logger logger = LoggerFactory.getLogger(getClass());

  private final IWorkflowDataService<SingleTaskWorkflow> wrkflowDataService;

  private final IWorkflowSaveStrategyFactory<SingleTaskWorkflow> workStrategyFactory;

  private final IWorkflowPrepare<SingleTaskWorkflow> workflowPrepare;

  public SingleTaskWorkflowProcessService(@Autowired final IWorkflowDataService<SingleTaskWorkflow> workflowDataService,
      @Autowired final IWorkflowSaveStrategyFactory<SingleTaskWorkflow> workStrategyFactory,
      @Autowired final IWorkflowPrepare<SingleTaskWorkflow> workflowPrepare) {

    this.wrkflowDataService = workflowDataService;
    this.workStrategyFactory = workStrategyFactory;
    this.workflowPrepare = workflowPrepare;
  }

  @Override
  public List<SingleTaskWorkflow> create(final IWorkflowSaveRequest<SingleTaskWorkflow> request, final Authentication authentication)
      throws WorkflowCustomizedException, MalformedURLException, IFlowMessageConversionFailureException {

    validate(request, authentication);

    final IWorkflowSaveStrategy<
        SingleTaskWorkflow> workflowStrategy = this.workStrategyFactory.selectSaveWorkStrategy(request, authentication);

    workflowStrategy.process();

    final List<SingleTaskWorkflow> result = workflowStrategy.getProceedWorkflowList();

    return workflowPrepare.prepareWorkflowList(authentication, result);
  }

  @Override
  public SingleTaskWorkflow save(final IWorkflowSaveRequest<SingleTaskWorkflow> request, final Authentication authentication)
      throws WorkflowCustomizedException, MalformedURLException, IFlowMessageConversionFailureException {

    logger.debug("Saving workflow with authentication {}", authentication);

    validate(request, authentication);

    final IWorkflowSaveStrategy<
        SingleTaskWorkflow> workflowStrategy = this.workStrategyFactory.selectSaveWorkStrategy(request, authentication);

    workflowStrategy.process();

    final SingleTaskWorkflow result = workflowStrategy.getSingleProceedWorkflow();

    return result;
  }

  @Override
  public void validate(final IWorkflowSaveRequest<SingleTaskWorkflow> request, final Authentication authentication)
      throws WorkflowCustomizedException, MalformedURLException, IFlowMessageConversionFailureException {

    workflowPrepare.prepareWorkflow(authentication, request.getWorkflow());

    final IWorkflowSaveStrategy<SingleTaskWorkflow> workflowStrategy = this.workStrategyFactory
        .selectValidationWorkStrategy(request,
            authentication);

    workflowStrategy.process();
  }

  @Override
  public SingleTaskWorkflow getByIdentity(final String identity, final Authentication authentication)
      throws WorkflowCustomizedException, MalformedURLException, IFlowMessageConversionFailureException {

    logger.debug("get workflow by id {} with authentication {}", identity, authentication);

    final SingleTaskWorkflow workflow = this.wrkflowDataService.getByIdentity(identity, authentication);

    return workflowPrepare.prepareWorkflow(authentication, workflow);
  }

  @Override
  public List<SingleTaskWorkflow> getListForUser(final String identity, final int status, final Authentication authentication)
      throws WorkflowCustomizedException, MalformedURLException, IFlowMessageConversionFailureException {

    logger.debug("get workflow assigned to user id {} and has status {} with authentication {}", identity, status, authentication);

    final List<SingleTaskWorkflow> list = this.wrkflowDataService.getListForUser(identity, status, authentication);

    return workflowPrepare.prepareWorkflowList(authentication, list);
  }

  @Override
  public List<SingleTaskWorkflow> getListByIdentityList(final Set<String> identityList, final Authentication authentication)
      throws WorkflowCustomizedException, MalformedURLException, IFlowMessageConversionFailureException {

    logger.debug("get workflow list by id list with authentication {}", authentication);

    final List<SingleTaskWorkflow> list = this.wrkflowDataService.getListByIdentityList(identityList, authentication);

    return workflowPrepare.prepareWorkflowList(authentication, list);
  }

}
