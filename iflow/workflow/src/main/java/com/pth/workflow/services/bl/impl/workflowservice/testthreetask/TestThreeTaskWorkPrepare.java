package com.pth.workflow.services.bl.impl.workflowservice.testthreetask;

import com.pth.workflow.entities.TestThreeTaskWorkflowEntity;
import com.pth.workflow.repositories.IWorkflowTypeRepository;
import com.pth.workflow.services.bl.WorkflowPrepareBase;

import javax.inject.Singleton;

@Singleton
public class TestThreeTaskWorkPrepare extends WorkflowPrepareBase<TestThreeTaskWorkflowEntity> {

  public TestThreeTaskWorkPrepare(IWorkflowTypeRepository workflowTypeRepository) {
    super(workflowTypeRepository);
  }

}
