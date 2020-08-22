package com.pth.workflow.services.bl.impl.workflowservice.singletask;

import java.net.MalformedURLException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.pth.common.exceptions.IFlowMessageConversionFailureException;
import com.pth.workflow.entities.workflow.SingleTaskWorkflowEntity;
import com.pth.workflow.models.base.IWorkflowSaveRequest;
import com.pth.workflow.repositories.ISingleTaskWorkflowRepository;
import com.pth.workflow.services.bl.IWorkflowPrepare;
import com.pth.workflow.services.bl.IWorkflowProcessService;
import com.pth.workflow.services.bl.strategy.IWorkflowSaveStrategy;
import com.pth.workflow.services.bl.strategy.IWorkflowSaveStrategyFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;

@Singleton
public class SingleTaskWorkflowProcessService implements IWorkflowProcessService<SingleTaskWorkflowEntity> {

  private final Logger logger = LoggerFactory.getLogger(getClass());

  private final ISingleTaskWorkflowRepository singleTaskWorkflowRepository;

  private final IWorkflowSaveStrategyFactory<SingleTaskWorkflowEntity> workStrategyFactory;

  private final IWorkflowPrepare<SingleTaskWorkflowEntity> workflowPrepare;

  public SingleTaskWorkflowProcessService(ISingleTaskWorkflowRepository singleTaskWorkflowRepository,
      IWorkflowSaveStrategyFactory<SingleTaskWorkflowEntity> workStrategyFactory,
      IWorkflowPrepare<SingleTaskWorkflowEntity> workflowPrepare) {

    this.singleTaskWorkflowRepository = singleTaskWorkflowRepository;
    this.workStrategyFactory = workStrategyFactory;
    this.workflowPrepare = workflowPrepare;
  }

  @Override
  public List<SingleTaskWorkflowEntity>
    create(final IWorkflowSaveRequest<SingleTaskWorkflowEntity> request)
          throws IFlowMessageConversionFailureException {

    final IWorkflowSaveStrategy<
            SingleTaskWorkflowEntity>
            workflowStrategy = this.workStrategyFactory.selectSaveWorkStrategy(request);

    workflowStrategy.process();

    final List<SingleTaskWorkflowEntity> result = workflowStrategy.getProceedWorkflowList();

    return workflowPrepare.prepareWorkflowList(result);
  }

  @Override
  public Optional<SingleTaskWorkflowEntity>
    save(final IWorkflowSaveRequest<SingleTaskWorkflowEntity> request) throws IFlowMessageConversionFailureException {

    logger.debug("Saving workflow");

    final IWorkflowSaveStrategy<
        SingleTaskWorkflowEntity> workflowStrategy = this.workStrategyFactory.selectSaveWorkStrategy(request);

    workflowStrategy.process();

    final Optional<SingleTaskWorkflowEntity> resultOptional = workflowStrategy.getSingleProceedWorkflow();

    return resultOptional;
  }

  @Override
  public void
    validate(final IWorkflowSaveRequest<SingleTaskWorkflowEntity> request)
          throws IFlowMessageConversionFailureException {

    workflowPrepare.prepareWorkflow(request.getWorkflow());

    final IWorkflowSaveStrategy<SingleTaskWorkflowEntity> workflowStrategy =
            this.workStrategyFactory.selectValidationWorkStrategy(request);

    workflowStrategy.process();
  }

  @Override
  public Optional<SingleTaskWorkflowEntity> getByIdentity(final String identity){

    logger.debug("get workflow by id {} with authentication {}", identity);

    final Optional<SingleTaskWorkflowEntity> workflowOptional = this.singleTaskWorkflowRepository.getByIdentity(identity);

    if(workflowOptional.isPresent())
    {
      return workflowPrepare.prepareWorkflow(workflowOptional.get());
    }
    return Optional.empty();
  }

  @Override
  public List<SingleTaskWorkflowEntity> getListForUser(final String identity, final int status){

    logger.debug("get workflow assigned to user id {} and has status {} with authentication {}", identity, status);

    final List<SingleTaskWorkflowEntity> list = this.singleTaskWorkflowRepository.getListForUser(identity, status);

    return workflowPrepare.prepareWorkflowList(list);
  }

  @Override
  public List<SingleTaskWorkflowEntity> getListByIdentityList(final Set<String> identityList){

    logger.debug("get workflow list by id list with authentication {}");

    final List<SingleTaskWorkflowEntity> list = this.singleTaskWorkflowRepository.getListByIdentityList(identityList);

    return workflowPrepare.prepareWorkflowList(list);
  }

}
