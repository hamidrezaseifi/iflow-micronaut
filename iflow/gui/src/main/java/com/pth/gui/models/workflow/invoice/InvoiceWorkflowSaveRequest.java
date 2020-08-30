package com.pth.gui.models.workflow.invoice;

import com.pth.common.edo.enums.EAssignType;
import com.pth.common.edo.enums.EWorkflowProcessCommand;
import com.pth.gui.models.UploadededFile;
import com.pth.gui.models.workflow.AssignItem;
import com.pth.gui.models.workflow.IWorkflowSaveRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class InvoiceWorkflowSaveRequest implements IWorkflowSaveRequest<InvoiceWorkflow> {

  private InvoiceWorkflow workflow;
  private Integer expireDays;
  private List<AssignItem> assigns = new ArrayList<>();
  private EWorkflowProcessCommand command;
  private List<UploadededFile> uploadedFiles = new ArrayList<>();
  private String comments;

  public InvoiceWorkflowSaveRequest() {

  }

  /**
   * @return the workflow
   */
  @Override
  public InvoiceWorkflow getWorkflow() {

    return this.workflow;
  }

  /**
   * @param workflow the workflow to set
   */
  @Override
  public void setWorkflow(final InvoiceWorkflow workflow) {

    this.workflow = workflow;
  }

  @Override
  public Integer getExpireDays() {

    return this.expireDays;
  }

  public void setExpireDays(final Integer expireDays) {

    this.expireDays = expireDays;
  }

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

  public static InvoiceWorkflowSaveRequest generateNewNoExpireDays(final InvoiceWorkflow workflow) {

    final InvoiceWorkflowSaveRequest request = new InvoiceWorkflowSaveRequest();

    request.setWorkflow(workflow);
    request.setExpireDays(0);
    request.setCommand(EWorkflowProcessCommand.CREATE);

    return request;
  }

  public static InvoiceWorkflowSaveRequest generateNewWihExpireDays(final InvoiceWorkflow workflow, final int expireDays) {

    final InvoiceWorkflowSaveRequest request = new InvoiceWorkflowSaveRequest();

    request.setWorkflow(workflow);
    request.setExpireDays(expireDays);
    request.setCommand(EWorkflowProcessCommand.CREATE);

    return request;
  }

  @Override
  public List<UploadededFile> getUploadedFiles() {

    return this.uploadedFiles;
  }

  @Override
  public void setUploadedFiles(final List<UploadededFile> uploadedFiles) {

    this.uploadedFiles = uploadedFiles;
  }

  @Override
  public void setAssignUser(final UUID userId) {

    this.assigns.clear();
    this.assigns.add(new AssignItem(userId, EAssignType.USER));
  }

  @Override
  public String getComments() {

    return this.comments;
  }

  @Override
  public void setComments(final String comments) {

    this.comments = comments;
  }

}
