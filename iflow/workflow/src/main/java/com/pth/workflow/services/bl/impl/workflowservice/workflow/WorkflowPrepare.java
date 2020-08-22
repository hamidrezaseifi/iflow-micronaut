package com.pth.workflow.services.bl.impl.workflowservice.workflow;

import com.pth.workflow.entities.workflow.WorkflowEntity;
import com.pth.workflow.repositories.IWorkflowTypeRepository;
import com.pth.workflow.services.bl.WorkflowPrepareBase;

import javax.inject.Singleton;

@Singleton
public class WorkflowPrepare extends WorkflowPrepareBase<WorkflowEntity> {

  public WorkflowPrepare(IWorkflowTypeRepository workflowTypeRepository) {
    super(workflowTypeRepository);
  }

}
