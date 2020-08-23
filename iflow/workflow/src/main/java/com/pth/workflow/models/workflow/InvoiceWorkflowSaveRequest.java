package com.pth.workflow.models.workflow;

import com.pth.common.edo.enums.EWorkflowProcessCommand;
import com.pth.workflow.entities.InvoiceWorkflowEntity;
import com.pth.workflow.models.AssignItem;
import com.pth.workflow.models.base.IWorkflowSaveRequest;

import java.util.ArrayList;
import java.util.List;

public class InvoiceWorkflowSaveRequest implements IWorkflowSaveRequest<InvoiceWorkflowEntity> {

  private InvoiceWorkflowEntity workflow;
  private Integer                 expireDays;
  private List<AssignItem>        assigns = new ArrayList<>();
  private EWorkflowProcessCommand command;

  public InvoiceWorkflowSaveRequest() {

  }

  /**
   * @return the workflow
   */
  @Override
  public InvoiceWorkflowEntity getWorkflow() {
    return this.workflow;
  }

  /**
   * @param workflow the workflow to set
   */
  @Override
  public void setWorkflow(final InvoiceWorkflowEntity workflow) {
    this.workflow = workflow;
  }

  @Override
  public Integer getExpireDays() {
    return this.expireDays;
  }

  public void setExpireDays(final Integer expireDays) {
    this.expireDays = expireDays;
  }

  /**
   * @return the assignedUsers
   */
  @Override
  public List<AssignItem> getAssigns() {
    return this.assigns;
  }

  public void setAssigns(final List<AssignItem> assigns) {
    this.assigns = new ArrayList<>();
    if (assigns != null) {
      this.assigns.addAll(assigns);
    }
  }

  @Override
  public EWorkflowProcessCommand getCommand() {
    return this.command;
  }

  @Override
  public boolean isAssignCommand() {
    return this.command == EWorkflowProcessCommand.ASSIGN;
  }

  @Override
  public boolean isArchiveCommand() {
    return this.command == EWorkflowProcessCommand.ARCHIVE;
  }

  @Override
  public boolean isCreateCommand() {
    return this.command == EWorkflowProcessCommand.CREATE;
  }

  @Override
  public boolean isDoneCommand() {
    return this.command == EWorkflowProcessCommand.DONE;
  }

  @Override
  public boolean isSaveCommand() {
    return this.command == EWorkflowProcessCommand.SAVE;
  }

  public void setCommand(final EWorkflowProcessCommand command) {
    this.command = command;
  }

}
