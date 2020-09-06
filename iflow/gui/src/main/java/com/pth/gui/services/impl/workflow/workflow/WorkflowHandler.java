package com.pth.gui.services.impl.workflow.workflow;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pth.iflow.common.exceptions.IFlowMessageConversionFailureException;
import com.pth.iflow.gui.exceptions.GuiCustomizedException;
import com.pth.iflow.gui.models.WorkflowFile;
import com.pth.iflow.gui.models.ui.SessionUserInfo;
import com.pth.iflow.gui.models.workflow.workflow.Workflow;
import com.pth.iflow.gui.models.workflow.workflow.WorkflowSaveRequest;
import com.pth.iflow.gui.services.IUploadFileManager;
import com.pth.iflow.gui.services.IWorkflowAccess;
import com.pth.iflow.gui.services.IWorkflowHandler;
import com.pth.iflow.gui.services.impl.workflow.base.WorkflowHandlerHelper;

@Service
public class WorkflowHandler extends WorkflowHandlerHelper<Workflow> implements IWorkflowHandler<Workflow, WorkflowSaveRequest> {

  private static final Logger logger = LoggerFactory.getLogger(WorkflowHandler.class);

  private final IWorkflowAccess<Workflow, WorkflowSaveRequest> workflowAccess;

  private final SessionUserInfo sessionUserInfo;

  private final IUploadFileManager uploadFileManager;

  public WorkflowHandler(@Autowired final IWorkflowAccess<Workflow, WorkflowSaveRequest> workflowAccess,
      @Autowired final SessionUserInfo sessionUserInfo, @Autowired final IUploadFileManager uploadFileManager) {

    this.workflowAccess = workflowAccess;
    this.sessionUserInfo = sessionUserInfo;
    this.uploadFileManager = uploadFileManager;
  }

  @Override
  public Workflow readWorkflow(final String workflowIdentity)
      throws GuiCustomizedException, MalformedURLException, IFlowMessageConversionFailureException {

    logger.debug("Read workflow {}", workflowIdentity);

    final Workflow wirkflow = this.workflowAccess.readWorkflow(workflowIdentity, this.sessionUserInfo.getToken());
    return this.prepareWorkflow(wirkflow);
  }

  @Override
  public List<Workflow> createWorkflow(final WorkflowSaveRequest createRequest)
      throws GuiCustomizedException, IOException, IFlowMessageConversionFailureException {

    throw new GuiCustomizedException("not implemented");
  }

  @Override
  public Workflow saveWorkflow(final WorkflowSaveRequest saveRequest)
      throws GuiCustomizedException, MalformedURLException, IOException, IFlowMessageConversionFailureException {

    throw new GuiCustomizedException("not implemented");
  }

  @Override
  public Workflow assignWorkflow(final String workflowIdentity)
      throws GuiCustomizedException, MalformedURLException, IOException, IFlowMessageConversionFailureException {

    throw new GuiCustomizedException("not implemented");

  }

  @Override
  public Workflow doneWorkflow(final WorkflowSaveRequest saveRequest)
      throws GuiCustomizedException, MalformedURLException, IOException, IFlowMessageConversionFailureException {

    throw new GuiCustomizedException("not implemented");
  }

  @Override
  public Workflow archiveWorkflow(final Workflow workflow)
      throws GuiCustomizedException, MalformedURLException, IOException, IFlowMessageConversionFailureException {

    throw new GuiCustomizedException("not implemented");
  }

  @Override
  public WorkflowFile readWorkflowFile(final String workflowIdentity, final String fileIdentity)
      throws GuiCustomizedException, MalformedURLException, IFlowMessageConversionFailureException {

    throw new GuiCustomizedException("not implemented");
  }

  @Override
  protected SessionUserInfo getSessionUserInfo() {

    return this.sessionUserInfo;
  }

  @Override
  protected IUploadFileManager getUploadFileManager() {

    return this.uploadFileManager;
  }

}
