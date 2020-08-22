package com.pth.workflow.services.bl.impl.workflowservice.workflow;

import java.net.MalformedURLException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.pth.common.exceptions.EIFlowErrorType;
import com.pth.common.exceptions.IFlowMessageConversionFailureException;
import com.pth.workflow.entities.workflow.WorkflowEntity;
import com.pth.workflow.models.base.IWorkflowSaveRequest;
import com.pth.workflow.repositories.IInvoiceWorkflowRepository;
import com.pth.workflow.repositories.IWorkflowRepository;
import com.pth.workflow.services.bl.IWorkflowPrepare;
import com.pth.workflow.services.bl.IWorkflowProcessService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;

@Singleton
public class WorkflowProcessService implements IWorkflowProcessService<WorkflowEntity> {

  private final Logger logger = LoggerFactory.getLogger(getClass());

  private final IWorkflowRepository workflowRepository;

  private final IWorkflowPrepare<WorkflowEntity> workflowPrepare;

  public WorkflowProcessService(IWorkflowRepository workflowRepository,
      IWorkflowPrepare<WorkflowEntity> workflowPrepare) {

    this.workflowRepository = workflowRepository;
    this.workflowPrepare = workflowPrepare;
  }

  @Override
  public List<WorkflowEntity> create(final IWorkflowSaveRequest<WorkflowEntity> request)
          throws IFlowMessageConversionFailureException {

    throw new IFlowMessageConversionFailureException("not implemented", EIFlowErrorType.SERVICE_NOT_IMPLEMENTED);
  }

  @Override
  public Optional<WorkflowEntity> save(final IWorkflowSaveRequest<WorkflowEntity> request)
          throws IFlowMessageConversionFailureException {

    throw new IFlowMessageConversionFailureException("not implemented", EIFlowErrorType.SERVICE_NOT_IMPLEMENTED);
  }

  @Override
  public void validate(final IWorkflowSaveRequest<WorkflowEntity> request)
      throws IFlowMessageConversionFailureException {

    throw new IFlowMessageConversionFailureException("not implemented", EIFlowErrorType.SERVICE_NOT_IMPLEMENTED);
  }

  @Override
  public Optional<WorkflowEntity> getByIdentity(final String identity) {

    logger.debug("get workflow by id {}", identity);

    final Optional<WorkflowEntity> workflowEntityOptional = this.workflowRepository.getByIdentity(identity);
    if(workflowEntityOptional.isPresent()){
      return workflowPrepare.prepareWorkflow(workflowEntityOptional.get());
    }

    return Optional.empty();
  }

  @Override
  public List<WorkflowEntity> getListForUser(final String identity, final int status) {

    logger.debug("get workflow assigned to user id {} and has status {} with authentication {}", identity, status);

    final List<WorkflowEntity> list = this.workflowRepository.getListForUser(identity, status);

    return workflowPrepare.prepareWorkflowList(list);
  }

  @Override
  public List<WorkflowEntity> getListByIdentityList(final Set<String> identityList) {

    logger.debug("get workflow list by id list with authentication {}");

    final List<WorkflowEntity> list = this.workflowRepository.getListByIdentityList(identityList);

    return workflowPrepare.prepareWorkflowList(list);
  }

}
