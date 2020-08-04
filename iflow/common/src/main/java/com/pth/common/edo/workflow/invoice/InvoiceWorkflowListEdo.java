package com.pth.common.edo.workflow.invoice;

import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonSetter;

public class InvoiceWorkflowListEdo {

  @NotNull
  private final List<InvoiceWorkflowEdo> workflows = new ArrayList<>();

  public InvoiceWorkflowListEdo() {

  }

  public InvoiceWorkflowListEdo(final List<InvoiceWorkflowEdo> workflows) {
    this.setWorkflows(workflows);
  }

  public List<InvoiceWorkflowEdo> getWorkflows() {
    return this.workflows;
  }

  @JsonSetter
  public void setWorkflows(final List<InvoiceWorkflowEdo> workflows) {
    this.workflows.clear();
    if (workflows != null) {
      this.workflows.addAll(workflows);
    }
  }

}
