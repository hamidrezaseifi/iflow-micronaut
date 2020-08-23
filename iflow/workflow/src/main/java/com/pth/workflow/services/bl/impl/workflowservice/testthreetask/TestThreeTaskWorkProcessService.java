package com.pth.workflow.services.bl.impl.workflowservice.testthreetask;

import java.net.MalformedURLException;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import com.pth.common.exceptions.IFlowMessageConversionFailureException;
import com.pth.workflow.entities.workflow.TestThreeTaskWorkflowEntity;
import com.pth.workflow.exceptions.WorkflowCustomizedException;
import com.pth.workflow.models.base.IWorkflowSaveRequest;
import com.pth.workflow.repositories.ITestThreeTaskWorkflowRepository;
import com.pth.workflow.services.bl.IWorkflowPrepare;
import com.pth.workflow.services.bl.IWorkflowProcessService;
import com.pth.workflow.services.bl.strategy.IWorkflowSaveStrategy;
import com.pth.workflow.services.bl.strategy.IWorkflowSaveStrategyFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import javax.inject.Singleton;

@Singleton
public class TestThreeTaskWorkProcessService implements IWorkflowProcessService<TestThreeTaskWorkflowEntity> {

  private final Logger logger = LoggerFactory.getLogger(getClass());

  private final ITestThreeTaskWorkflowRepository testThreeTaskWorkflowRepository;

  private final IWorkflowSaveStrategyFactory<TestThreeTaskWorkflowEntity> workStrategyFactory;

  private final IWorkflowPrepare<TestThreeTaskWorkflowEntity> workflowPrepare;

  public TestThreeTaskWorkProcessService(ITestThreeTaskWorkflowRepository testThreeTaskWorkflowRepository,
      IWorkflowSaveStrategyFactory<TestThreeTaskWorkflowEntity> workStrategyFactory,
      IWorkflowPrepare<TestThreeTaskWorkflowEntity> workflowPrepare) {

    this.testThreeTaskWorkflowRepository = testThreeTaskWorkflowRepository;
    this.workStrategyFactory = workStrategyFactory;
    this.workflowPrepare = workflowPrepare;
  }

  @Override
  public Optional<TestThreeTaskWorkflowEntity> getById(UUID id) {
    return testThreeTaskWorkflowRepository.getById(id);
  }

  @Override
  public List<TestThreeTaskWorkflowEntity>
    create(final IWorkflowSaveRequest<TestThreeTaskWorkflowEntity> request, String authorization)
          throws WorkflowCustomizedException {

    final IWorkflowSaveStrategy<TestThreeTaskWorkflowEntity> workflowStrategy =
            this.workStrategyFactory.selectSaveWorkStrategy(request, authorization);

    workflowStrategy.process();

    final List<TestThreeTaskWorkflowEntity> result = workflowStrategy.getProceedWorkflowList();

    return workflowPrepare.prepareWorkflowList(result);
  }

  @Override
  public Optional<TestThreeTaskWorkflowEntity>
    save(final IWorkflowSaveRequest<TestThreeTaskWorkflowEntity> request, String authorization)
          throws WorkflowCustomizedException {

    logger.debug("Saving workflow");

    final IWorkflowSaveStrategy<TestThreeTaskWorkflowEntity> workflowStrategy =
            this.workStrategyFactory.selectSaveWorkStrategy(request, authorization);

    workflowStrategy.process();

    final Optional<TestThreeTaskWorkflowEntity> resultOptional = workflowStrategy.getSingleProceedWorkflow();

    return resultOptional;
  }

  @Override
  public void
    validate(final IWorkflowSaveRequest<TestThreeTaskWorkflowEntity> request, String authorization)
          throws WorkflowCustomizedException {

    workflowPrepare.prepareWorkflow(request.getWorkflow());

    final IWorkflowSaveStrategy<TestThreeTaskWorkflowEntity> workflowStrategy = this.workStrategyFactory
        .selectValidationWorkStrategy(request, authorization);

    workflowStrategy.process();
  }

  @Override
  public Optional<TestThreeTaskWorkflowEntity> getByIdentity(final String identity){

    logger.debug("get workflow by id {} with authentication {}", identity);

    final Optional<TestThreeTaskWorkflowEntity> workflowOptional =
            this.testThreeTaskWorkflowRepository.getByIdentity(identity);

    if(workflowOptional.isPresent()){
      return workflowPrepare.prepareWorkflow(workflowOptional.get());
    }
    return Optional.empty();
  }

  @Override
  public List<TestThreeTaskWorkflowEntity> getListForUser(final UUID id, final int status){

    logger.debug("get workflow assigned to user id {} and has status {} with authentication {}", id, status);

    final List<TestThreeTaskWorkflowEntity> list =
            this.testThreeTaskWorkflowRepository.getListForUser(id, status);

    return workflowPrepare.prepareWorkflowList(list);
  }

  @Override
  public List<TestThreeTaskWorkflowEntity>
    getListByIdentityList(final Set<String> identityList) {

    logger.debug("get workflow list by id list with authentication {}");

    final List<TestThreeTaskWorkflowEntity> list = this.testThreeTaskWorkflowRepository.getListByIdentityList(identityList);

    return workflowPrepare.prepareWorkflowList(list);
  }

}
