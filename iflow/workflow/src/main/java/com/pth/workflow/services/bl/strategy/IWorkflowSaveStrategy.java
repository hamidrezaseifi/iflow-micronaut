package com.pth.workflow.services.bl.strategy;

import com.pth.workflow.exceptions.WorkflowCustomizedException;
import com.pth.workflow.models.base.IWorkflowBaseEntity;

import java.util.List;
import java.util.Optional;

public interface IWorkflowSaveStrategy<W extends IWorkflowBaseEntity> {

  void setup();

  void process() throws WorkflowCustomizedException;

  Optional<W> getSingleProceedWorkflow();

  List<W> getProceedWorkflowList();
}
