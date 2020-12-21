package com.pth.workflow.services.bl.impl.workflowservice.singletask;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import com.pth.common.exceptions.EIFlowErrorType;
import com.pth.workflow.entities.SingleTaskWorkflowEntity;
import com.pth.workflow.exceptions.WorkflowCustomizedException;
import com.pth.workflow.models.base.IWorkflowSaveRequest;
import com.pth.workflow.repositories.ISingleTaskWorkflowRepository;
import com.pth.workflow.services.bl.IWorkflowPrepare;
import com.pth.workflow.services.bl.IWorkflowProcessService;
import com.pth.workflow.services.bl.strategy.IWorkflowSaveStrategy;
import com.pth.workflow.services.bl.strategy.IWorkflowSaveStrategyFactory;
import com.pth.workflow.services.bl.strategy.factory.SingleTaskWorkflowSaveStrategyFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Named;
import javax.inject.Singleton;

@Singleton
public class SingleTaskWorkflowProcessService implements IWorkflowProcessService<SingleTaskWorkflowEntity> {

  private final Logger logger = LoggerFactory.getLogger(getClass());

  private final ISingleTaskWorkflowRepository singleTaskWorkflowRepository;

  private final IWorkflowSaveStrategyFactory<SingleTaskWorkflowEntity> workStrategyFactory;

  private final IWorkflowPrepare<SingleTaskWorkflowEntity> workflowPrepare;

  public SingleTaskWorkflowProcessService(ISingleTaskWorkflowRepository singleTaskWorkflowRepository,
                                          @Named("singleTaskWorkflowSaveStrategyFactory") IWorkflowSaveStrategyFactory<SingleTaskWorkflowEntity> workStrategyFactory,
                                          IWorkflowPrepare<SingleTaskWorkflowEntity> workflowPrepare) {

    this.singleTaskWorkflowRepository = singleTaskWorkflowRepository;
    this.workStrategyFactory = workStrategyFactory;
    this.workflowPrepare = workflowPrepare;
  }

  @Override
  public Optional<SingleTaskWorkflowEntity> getById(UUID id) {

    logger.debug("get workflow by id {}", id);

    final Optional<SingleTaskWorkflowEntity> workflowOptional = this.singleTaskWorkflowRepository.getById(id);

    if(workflowOptional.isPresent())
    {
      return workflowPrepare.prepareWorkflow(workflowOptional.get());
    }
    return Optional.empty();
  }

  @Override
  public List<SingleTaskWorkflowEntity>
    create(final IWorkflowSaveRequest<SingleTaskWorkflowEntity> request, String authorization)
          throws WorkflowCustomizedException {

    Optional<SingleTaskWorkflowEntity> preparedWorkflowOptional =
            workflowPrepare.prepareWorkflow(request.getWorkflow());
    if(!preparedWorkflowOptional.isPresent()){
      throw new WorkflowCustomizedException("No assign by workflow create", EIFlowErrorType.NO_WORKFLOW_ASSIGN_CREATE_STRATEGY);
    }
    request.setWorkflow(preparedWorkflowOptional.get());

    final IWorkflowSaveStrategy<
            SingleTaskWorkflowEntity>
            workflowStrategy = this.workStrategyFactory.selectSaveWorkStrategy(request, authorization);

    workflowStrategy.process();

    final List<SingleTaskWorkflowEntity> result = workflowStrategy.getProceedWorkflowList();

    return workflowPrepare.prepareWorkflowList(result);
  }

  @Override
  public Optional<SingleTaskWorkflowEntity> save(final IWorkflowSaveRequest<SingleTaskWorkflowEntity> request,
                                                 String authorization) throws WorkflowCustomizedException {

    logger.debug("Saving workflow");

    final IWorkflowSaveStrategy<
        SingleTaskWorkflowEntity> workflowStrategy = this.workStrategyFactory.selectSaveWorkStrategy(request,
                                                                                                     authorization);

    workflowStrategy.process();

    final Optional<SingleTaskWorkflowEntity> resultOptional = workflowStrategy.getSingleProceedWorkflow();

    return resultOptional;
  }

  @Override
  public void validate(final IWorkflowSaveRequest<SingleTaskWorkflowEntity> request,
                       String authorization) throws WorkflowCustomizedException {

    workflowPrepare.prepareWorkflow(request.getWorkflow());

    final IWorkflowSaveStrategy<SingleTaskWorkflowEntity> workflowStrategy =
            this.workStrategyFactory.selectValidationWorkStrategy(request, authorization);

    workflowStrategy.process();
  }

  @Override
  public List<SingleTaskWorkflowEntity> getListForUser(final UUID id, final int status){

    logger.debug("get workflow assigned to user id {} and has status {} with authentication {}", id, status);

    final List<SingleTaskWorkflowEntity> list = this.singleTaskWorkflowRepository.getListForUser(id, status);

    return workflowPrepare.prepareWorkflowList(list);
  }

}
