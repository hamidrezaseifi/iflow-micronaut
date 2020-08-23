package com.pth.workflow.services.bl.impl.workflowservice.singletask;

import com.pth.workflow.entities.SingleTaskWorkflowEntity;
import com.pth.workflow.repositories.IWorkflowTypeRepository;
import com.pth.workflow.services.bl.WorkflowPrepareBase;

import javax.inject.Singleton;

@Singleton
public class SingleTaskWorkflowPrepare extends WorkflowPrepareBase<SingleTaskWorkflowEntity> {

  public SingleTaskWorkflowPrepare(IWorkflowTypeRepository workflowTypeRepository) {
    super(workflowTypeRepository);
  }

}
