package com.pth.core.entities.workflow.base;


import com.pth.core.entities.workflow.WorkflowEntity;

public interface IWorkflowContainerEntity {

  public Long getWorkflowId();

  public void setWorkflowId(final Long workflowId);

  public WorkflowEntity getWorkflow();

  public void setWorkflow(final WorkflowEntity workflow);

}
