package com.pth.workflow.services.bl.impl.workflowservice.workflow;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import com.pth.common.exceptions.EIFlowErrorType;
import com.pth.workflow.entities.WorkflowEntity;
import com.pth.workflow.exceptions.WorkflowCustomizedException;
import com.pth.workflow.models.base.IWorkflowSaveRequest;
import com.pth.workflow.repositories.IWorkflowRepository;
import com.pth.workflow.services.bl.IWorkflowPrepare;
import com.pth.workflow.services.bl.IWorkflowProcessService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Named;
import javax.inject.Singleton;

@Singleton
public class WorkflowProcessService implements IWorkflowProcessService<WorkflowEntity> {

  private final Logger logger = LoggerFactory.getLogger(getClass());

  private final IWorkflowRepository workflowRepository;

  private final IWorkflowPrepare<WorkflowEntity> workflowPrepare;

  public WorkflowProcessService(IWorkflowRepository workflowRepository,
                                @Named("workflowPrepare") IWorkflowPrepare<WorkflowEntity> workflowPrepare) {

    this.workflowRepository = workflowRepository;
    this.workflowPrepare = workflowPrepare;
  }

  @Override
  public Optional<WorkflowEntity> getById(UUID id) {
    logger.debug("get workflow by id {}", id);

    final Optional<WorkflowEntity> workflowEntityOptional = this.workflowRepository.getById(id);
    if(workflowEntityOptional.isPresent()){
      return workflowPrepare.prepareWorkflow(workflowEntityOptional.get());
    }

    return Optional.empty();
  }

  @Override
  public List<WorkflowEntity> create(final IWorkflowSaveRequest<WorkflowEntity> request, String authorization)
          throws WorkflowCustomizedException {

    throw new WorkflowCustomizedException("not implemented", EIFlowErrorType.SERVICE_NOT_IMPLEMENTED);
  }

  @Override
  public Optional<WorkflowEntity> save(final IWorkflowSaveRequest<WorkflowEntity> request, String authorization)
          throws WorkflowCustomizedException {

    throw new WorkflowCustomizedException("not implemented", EIFlowErrorType.SERVICE_NOT_IMPLEMENTED);
  }

  @Override
  public void validate(final IWorkflowSaveRequest<WorkflowEntity> request, String authorization)
          throws WorkflowCustomizedException {

    throw new WorkflowCustomizedException("not implemented", EIFlowErrorType.SERVICE_NOT_IMPLEMENTED);
  }

  @Override
  public List<WorkflowEntity> getListForUser(final UUID id, final int status) {

    logger.debug("get workflow assigned to user id {} and has status {} with authentication {}", id, status);

    final List<WorkflowEntity> list = this.workflowRepository.getListForUser(id, status);

    return workflowPrepare.prepareWorkflowList(list);
  }

}
