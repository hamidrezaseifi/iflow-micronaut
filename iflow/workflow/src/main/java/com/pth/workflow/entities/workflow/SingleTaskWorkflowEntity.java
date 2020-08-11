package com.pth.core.entities.workflow;

import com.pth.core.entities.workflow.base.IWorkflowContainerEntity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "singletask_workflow")
public class SingleTaskWorkflowEntity implements IWorkflowContainerEntity {

  @Id
  @Column(name = "workflow_id")
  private Long workflowId;

  @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "workflow_id")
  private WorkflowEntity workflow;

  public SingleTaskWorkflowEntity() {

  }

  @Override
  public Long getWorkflowId() {

    return workflowId;
  }

  @Override
  public void setWorkflowId(final Long workflowId) {

    this.workflowId = workflowId;
  }

  @Override
  public WorkflowEntity getWorkflow() {

    return workflow;
  }

  @Override
  public void setWorkflow(final WorkflowEntity workflow) {

    this.workflow = workflow;
  }


}
