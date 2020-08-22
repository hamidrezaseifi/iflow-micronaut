package com.pth.workflow.services.bl.strategy;

import com.pth.common.exceptions.IFlowMessageConversionFailureException;
import com.pth.workflow.entities.workflow.InvoiceWorkflowEntity;
import com.pth.workflow.models.base.IWorkflowBaseEntity;

import java.util.List;
import java.util.Optional;

public interface IWorkflowSaveStrategy<W extends IWorkflowBaseEntity> {

  void setup();

  void process() throws IFlowMessageConversionFailureException;

  Optional<W> getSingleProceedWorkflow();

  List<W> getProceedWorkflowList();
}
