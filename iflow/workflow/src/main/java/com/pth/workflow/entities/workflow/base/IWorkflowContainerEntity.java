package com.pth.workflow.entities.workflow.base;


import com.pth.workflow.entities.workflow.WorkflowEntity;

public interface IWorkflowContainerEntity {

  public Long getWorkflowId();

  public void setWorkflowId(final Long workflowId);

  public WorkflowEntity getWorkflow();

  public void setWorkflow(final WorkflowEntity workflow);

}
