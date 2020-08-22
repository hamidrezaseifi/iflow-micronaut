package com.pth.workflow.services.bl.impl.workflowservice.invoice;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.pth.iflow.common.exceptions.IFlowMessageConversionFailureException;
import com.pth.iflow.workflow.bl.IWorkflowPrepare;
import com.pth.iflow.workflow.bl.IWorkflowTypeDataService;
import com.pth.iflow.workflow.models.WorkflowAction;
import com.pth.iflow.workflow.models.WorkflowType;
import com.pth.iflow.workflow.models.WorkflowTypeStep;
import com.pth.iflow.workflow.models.workflow.invoice.InvoiceWorkflow;

@Service
public class InvoiceWorkflowPrepare implements IWorkflowPrepare<InvoiceWorkflow> {

  private final IWorkflowTypeDataService workflowTypeDataService;

  public InvoiceWorkflowPrepare(@Autowired final IWorkflowTypeDataService workflowTypeDataService) {

    this.workflowTypeDataService = workflowTypeDataService;
  }

  @Override
  public InvoiceWorkflow prepareWorkflow(final Authentication authentication, final InvoiceWorkflow workflow)
      throws MalformedURLException, IFlowMessageConversionFailureException {

    final WorkflowType workflowType = this.workflowTypeDataService.getByIdentity(workflow.getWorkflowTypeIdentity(), authentication);

    workflow.setWorkflowType(workflowType);

    final Map<String, WorkflowTypeStep> map = getIdMapedSteps(workflowType);

    workflow.setCurrentStep(map.containsKey(workflow.getCurrentStepIdentity()) ? map.get(workflow.getCurrentStepIdentity()) : null);
    for (final WorkflowAction action : workflow.getActions()) {
      action.setCurrentStep(map.containsKey(action.getCurrentStepIdentity()) ? map.get(action.getCurrentStepIdentity()) : null);
    }

    for (final WorkflowTypeStep typeStep : workflowType.getSteps()) {
      if (typeStep.hasSameIdentity(workflow.getCurrentStepIdentity())) {
        workflow.setCurrentStep(typeStep);
      }
    }

    return workflow;
  }

  @Override
  public List<InvoiceWorkflow> prepareWorkflowList(final Authentication authentication, final List<InvoiceWorkflow> workflowList)
      throws MalformedURLException, IFlowMessageConversionFailureException {

    final List<InvoiceWorkflow> list = new ArrayList<>();
    if (workflowList != null) {
      for (final InvoiceWorkflow workflow : workflowList) {
        list.add(this.prepareWorkflow(authentication, workflow));
      }

    }

    return list;
  }

  private Map<String, WorkflowTypeStep> getIdMapedSteps(final WorkflowType workflowType) {

    final Map<String, WorkflowTypeStep> list = workflowType
        .getSteps()
        .stream()
        .collect(Collectors.toMap(s -> s.getIdentity(), s -> s));

    return list;
  }

}
