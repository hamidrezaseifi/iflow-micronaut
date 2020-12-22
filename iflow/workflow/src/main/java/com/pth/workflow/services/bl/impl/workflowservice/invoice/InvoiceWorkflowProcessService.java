package com.pth.workflow.services.bl.impl.workflowservice.invoice;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import com.pth.common.exceptions.EIFlowErrorType;
import com.pth.workflow.entities.InvoiceWorkflowEntity;
import com.pth.workflow.entities.TestThreeTaskWorkflowEntity;
import com.pth.workflow.exceptions.WorkflowCustomizedException;
import com.pth.workflow.models.base.IWorkflowSaveRequest;
import com.pth.workflow.repositories.IInvoiceWorkflowRepository;
import com.pth.workflow.services.bl.IWorkflowPrepare;
import com.pth.workflow.services.bl.IWorkflowProcessService;
import com.pth.workflow.services.bl.strategy.IWorkflowSaveStrategy;
import com.pth.workflow.services.bl.strategy.IWorkflowSaveStrategyFactory;
import com.pth.workflow.services.bl.strategy.factory.InvoiceWorkflowSaveStrategyFactory;
import com.pth.workflow.services.bl.strategy.factory.TestThreeTaskWorkflowSaveStrategyFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Named;
import javax.inject.Singleton;


@Singleton
public class InvoiceWorkflowProcessService implements IWorkflowProcessService<InvoiceWorkflowEntity> {

  private final Logger logger = LoggerFactory.getLogger(getClass());

  private final IInvoiceWorkflowRepository invoiceWorkflowRepository;

  private final IWorkflowSaveStrategyFactory<InvoiceWorkflowEntity> workStrategyFactory;

  private final IWorkflowPrepare<InvoiceWorkflowEntity> workflowPrepare;

  public InvoiceWorkflowProcessService(IInvoiceWorkflowRepository invoiceWorkflowRepository,
                                       @Named("invoiceWorkflowSaveStrategyFactory") IWorkflowSaveStrategyFactory<InvoiceWorkflowEntity> workStrategyFactory,
                                        IWorkflowPrepare<InvoiceWorkflowEntity> workflowPrepare) {

    this.invoiceWorkflowRepository = invoiceWorkflowRepository;
    this.workStrategyFactory = workStrategyFactory;
    this.workflowPrepare = workflowPrepare;
  }

  @Override
  public Optional<InvoiceWorkflowEntity> getById(UUID id) {
    final Optional<InvoiceWorkflowEntity> workflowEntityOptional = invoiceWorkflowRepository.getById(id);

    if(workflowEntityOptional.isPresent()){
      return workflowPrepare.prepareWorkflow(workflowEntityOptional.get());
    }
    return Optional.empty();
  }

  @Override
  public List<InvoiceWorkflowEntity>
    create(final IWorkflowSaveRequest<InvoiceWorkflowEntity> request, String authorization)
          throws WorkflowCustomizedException {

    Optional<InvoiceWorkflowEntity> preparedWorkflowOptional =
            workflowPrepare.prepareWorkflow(request.getWorkflow());
    if(!preparedWorkflowOptional.isPresent()){
      throw new WorkflowCustomizedException("No assign by workflow create", EIFlowErrorType.NO_WORKFLOW_ASSIGN_CREATE_STRATEGY);
    }
    request.setWorkflow(preparedWorkflowOptional.get());

    final IWorkflowSaveStrategy<InvoiceWorkflowEntity> workflowStrategy =
            this.workStrategyFactory.selectSaveWorkStrategy(request, authorization);

    workflowStrategy.process();

    final List<InvoiceWorkflowEntity> result = workflowStrategy.getProceedWorkflowList();

    return workflowPrepare.prepareWorkflowList(result);
  }

  @Override
  public Optional<InvoiceWorkflowEntity>
    save(final IWorkflowSaveRequest<InvoiceWorkflowEntity> request, String authorization)
          throws WorkflowCustomizedException {

    Optional<InvoiceWorkflowEntity> preparedWorkflowOptional =
            workflowPrepare.prepareWorkflow(request.getWorkflow());
    if(!preparedWorkflowOptional.isPresent()){
      throw new WorkflowCustomizedException("No assign by workflow create", EIFlowErrorType.NO_WORKFLOW_ASSIGN_CREATE_STRATEGY);
    }
    request.setWorkflow(preparedWorkflowOptional.get());

    final IWorkflowSaveStrategy<
        InvoiceWorkflowEntity> workflowStrategy = this.workStrategyFactory.selectSaveWorkStrategy(request,
                                                                                                  authorization);

    workflowStrategy.process();

    final Optional<InvoiceWorkflowEntity> resultOptional = workflowStrategy.getSingleProceedWorkflow();

    return resultOptional;
  }

  @Override
  public void
    validate(final IWorkflowSaveRequest<InvoiceWorkflowEntity> request, String authorization)
          throws WorkflowCustomizedException {

    workflowPrepare.prepareWorkflow(request.getWorkflow());

    final IWorkflowSaveStrategy<InvoiceWorkflowEntity> workflowStrategy =
            this.workStrategyFactory.selectValidationWorkStrategy(request, authorization);

    workflowStrategy.process();
  }

  @Override
  public List<InvoiceWorkflowEntity> getListForUser(final UUID id, final int status)
       {

    logger.debug("get workflow assigned to user id {} and has status {} with authentication {}", id, status);

    final List<InvoiceWorkflowEntity> list = this.invoiceWorkflowRepository.getListForUser(id, status);

    return workflowPrepare.prepareWorkflowList(list);
  }

}
