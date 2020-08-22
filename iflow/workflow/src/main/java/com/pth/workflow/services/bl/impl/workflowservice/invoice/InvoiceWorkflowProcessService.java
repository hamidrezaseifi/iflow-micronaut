package com.pth.workflow.services.bl.impl.workflowservice.invoice;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.pth.common.exceptions.IFlowMessageConversionFailureException;
import com.pth.workflow.entities.workflow.InvoiceWorkflowEntity;
import com.pth.workflow.models.base.IWorkflowSaveRequest;
import com.pth.workflow.repositories.IInvoiceWorkflowRepository;
import com.pth.workflow.services.bl.IWorkflowPrepare;
import com.pth.workflow.services.bl.IWorkflowProcessService;
import com.pth.workflow.services.bl.strategy.IWorkflowSaveStrategy;
import com.pth.workflow.services.bl.strategy.IWorkflowSaveStrategyFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;


@Singleton
public class InvoiceWorkflowProcessService implements IWorkflowProcessService<InvoiceWorkflowEntity> {

  private final Logger logger = LoggerFactory.getLogger(getClass());

  private final IInvoiceWorkflowRepository invoiceWorkflowRepository;

  private final IWorkflowSaveStrategyFactory<InvoiceWorkflowEntity> workStrategyFactory;

  private final IWorkflowPrepare<InvoiceWorkflowEntity> workflowPrepare;

  public InvoiceWorkflowProcessService(IInvoiceWorkflowRepository invoiceWorkflowRepository,
      IWorkflowSaveStrategyFactory<InvoiceWorkflowEntity> workStrategyFactory,
      IWorkflowPrepare<InvoiceWorkflowEntity> workflowPrepare) {

    this.invoiceWorkflowRepository = invoiceWorkflowRepository;
    this.workStrategyFactory = workStrategyFactory;
    this.workflowPrepare = workflowPrepare;
  }

  @Override
  public List<InvoiceWorkflowEntity>
    create(final IWorkflowSaveRequest<InvoiceWorkflowEntity> request) throws IFlowMessageConversionFailureException {

    final IWorkflowSaveStrategy<InvoiceWorkflowEntity> workflowStrategy =
            this.workStrategyFactory.selectSaveWorkStrategy(request);

    workflowStrategy.process();

    final List<InvoiceWorkflowEntity> result = workflowStrategy.getProceedWorkflowList();

    return workflowPrepare.prepareWorkflowList(result);
  }

  @Override
  public Optional<InvoiceWorkflowEntity>
    save(final IWorkflowSaveRequest<InvoiceWorkflowEntity> request) throws IFlowMessageConversionFailureException {

    final IWorkflowSaveStrategy<
        InvoiceWorkflowEntity> workflowStrategy = this.workStrategyFactory.selectSaveWorkStrategy(request);

    workflowStrategy.process();

    final Optional<InvoiceWorkflowEntity> resultOptional = workflowStrategy.getSingleProceedWorkflow();

    return resultOptional;
  }

  @Override
  public void
    validate(final IWorkflowSaveRequest<InvoiceWorkflowEntity> request)
          throws IFlowMessageConversionFailureException {

    workflowPrepare.prepareWorkflow(request.getWorkflow());

    final IWorkflowSaveStrategy<InvoiceWorkflowEntity> workflowStrategy =
            this.workStrategyFactory.selectValidationWorkStrategy(request);

    workflowStrategy.process();
  }

  @Override
  public Optional<InvoiceWorkflowEntity> getByIdentity(final String identity)
       {

    final Optional<InvoiceWorkflowEntity> workflowEntityOptional = this.invoiceWorkflowRepository.getByIdentity(identity);

    if(workflowEntityOptional.isPresent()){
      return workflowPrepare.prepareWorkflow(workflowEntityOptional.get());
    }
    return Optional.empty();
  }

  @Override
  public List<InvoiceWorkflowEntity> getListForUser(final String identity, final int status)
       {

    logger.debug("get workflow assigned to user id {} and has status {} with authentication {}", identity, status);

    final List<InvoiceWorkflowEntity> list = this.invoiceWorkflowRepository.getListForUser(identity, status);

    return workflowPrepare.prepareWorkflowList(list);
  }

  @Override
  public List<InvoiceWorkflowEntity> getListByIdentityList(final Set<String> identityList)
       {

    logger.debug("get workflow list by id list with authentication {}");

    final List<InvoiceWorkflowEntity> list = this.invoiceWorkflowRepository.getListByIdentityList(identityList);

    return workflowPrepare.prepareWorkflowList(list);
  }

}
