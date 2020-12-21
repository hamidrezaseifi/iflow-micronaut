package com.pth.workflow.entities;

import com.pth.workflow.models.base.WorkflowBaseEntity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "testthreetask_workflow")
public class TestThreeTaskWorkflowEntity extends WorkflowBaseEntity {

  @Id
  @Column(name = "workflow_id")
  private UUID workflowId;

  @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "workflow_id")
  private WorkflowEntity workflow;

  public TestThreeTaskWorkflowEntity() {

  }

  @Override
  public UUID getWorkflowId() {

    return workflowId;
  }

  @Override
  public void setWorkflowId(final UUID workflowId) {

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

  @Override
  public UUID getWorkflowTypeId() {
    return this.workflow.getWorkflowTypeId();
  }

}
