package com.pth.workflow.services.bl.impl.workflowservice.testthreetask;

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
import com.pth.iflow.workflow.models.workflow.testthree.TestThreeTaskWorkflow;

@Service
public class TestThreeTaskWorkProcessService implements IWorkflowProcessService<TestThreeTaskWorkflow> {

  private final Logger logger = LoggerFactory.getLogger(getClass());

  private final IWorkflowDataService<TestThreeTaskWorkflow> wrkflowDataService;

  private final IWorkflowSaveStrategyFactory<TestThreeTaskWorkflow> workStrategyFactory;

  private final IWorkflowPrepare<TestThreeTaskWorkflow> workflowPrepare;

  public TestThreeTaskWorkProcessService(@Autowired final IWorkflowDataService<TestThreeTaskWorkflow> workflowDataService,
      @Autowired final IWorkflowSaveStrategyFactory<TestThreeTaskWorkflow> workStrategyFactory,
      @Autowired final IWorkflowPrepare<TestThreeTaskWorkflow> workflowPrepare) {

    this.wrkflowDataService = workflowDataService;
    this.workStrategyFactory = workStrategyFactory;
    this.workflowPrepare = workflowPrepare;
  }

  @Override
  public List<TestThreeTaskWorkflow> create(final IWorkflowSaveRequest<TestThreeTaskWorkflow> request, final Authentication authentication)
      throws WorkflowCustomizedException, MalformedURLException, IFlowMessageConversionFailureException {

    validate(request, authentication);

    final IWorkflowSaveStrategy<TestThreeTaskWorkflow> workflowStrategy = this.workStrategyFactory
        .selectSaveWorkStrategy(request,
            authentication);

    workflowStrategy.process();

    final List<TestThreeTaskWorkflow> result = workflowStrategy.getProceedWorkflowList();

    return workflowPrepare.prepareWorkflowList(authentication, result);
  }

  @Override
  public TestThreeTaskWorkflow save(final IWorkflowSaveRequest<TestThreeTaskWorkflow> request, final Authentication authentication)
      throws WorkflowCustomizedException, MalformedURLException, IFlowMessageConversionFailureException {

    logger.debug("Saving workflow with authentication {}", authentication);

    validate(request, authentication);

    final IWorkflowSaveStrategy<TestThreeTaskWorkflow> workflowStrategy = this.workStrategyFactory
        .selectSaveWorkStrategy(request,
            authentication);

    workflowStrategy.process();

    final TestThreeTaskWorkflow result = workflowStrategy.getSingleProceedWorkflow();

    return result;
  }

  @Override
  public void validate(final IWorkflowSaveRequest<TestThreeTaskWorkflow> request, final Authentication authentication)
      throws WorkflowCustomizedException, MalformedURLException, IFlowMessageConversionFailureException {

    workflowPrepare.prepareWorkflow(authentication, request.getWorkflow());

    final IWorkflowSaveStrategy<TestThreeTaskWorkflow> workflowStrategy = this.workStrategyFactory
        .selectValidationWorkStrategy(request, authentication);

    workflowStrategy.process();
  }

  @Override
  public TestThreeTaskWorkflow getByIdentity(final String identity, final Authentication authentication)
      throws WorkflowCustomizedException, MalformedURLException, IFlowMessageConversionFailureException {

    logger.debug("get workflow by id {} with authentication {}", identity, authentication);

    final TestThreeTaskWorkflow workflow = this.wrkflowDataService.getByIdentity(identity, authentication);

    return workflowPrepare.prepareWorkflow(authentication, workflow);
  }

  @Override
  public List<TestThreeTaskWorkflow> getListForUser(final String identity, final int status, final Authentication authentication)
      throws WorkflowCustomizedException, MalformedURLException, IFlowMessageConversionFailureException {

    logger.debug("get workflow assigned to user id {} and has status {} with authentication {}", identity, status, authentication);

    final List<TestThreeTaskWorkflow> list = this.wrkflowDataService.getListForUser(identity, status, authentication);

    return workflowPrepare.prepareWorkflowList(authentication, list);
  }

  @Override
  public List<TestThreeTaskWorkflow> getListByIdentityList(final Set<String> identityList, final Authentication authentication)
      throws WorkflowCustomizedException, MalformedURLException, IFlowMessageConversionFailureException {

    logger.debug("get workflow list by id list with authentication {}", authentication);

    final List<TestThreeTaskWorkflow> list = this.wrkflowDataService.getListByIdentityList(identityList, authentication);

    return workflowPrepare.prepareWorkflowList(authentication, list);
  }

}
