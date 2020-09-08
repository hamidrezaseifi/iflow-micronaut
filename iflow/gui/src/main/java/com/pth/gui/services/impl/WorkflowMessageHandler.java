package com.pth.gui.services.impl;

import java.net.MalformedURLException;
import java.util.*;

import com.pth.gui.exception.GuiCustomizedException;
import com.pth.gui.models.gui.uisession.SessionData;
import com.pth.gui.models.workflow.WorkflowMessage;
import com.pth.gui.models.workflow.WorkflowType;
import com.pth.gui.models.workflow.WorkflowTypeStep;
import com.pth.gui.models.workflow.workflow.Workflow;
import com.pth.gui.models.workflow.workflow.WorkflowSaveRequest;
import com.pth.gui.services.ICompanyCachDataManager;
import com.pth.gui.services.IWorkflowHandler;
import com.pth.gui.services.IWorkflowMessageHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class WorkflowMessageHandler implements IWorkflowMessageHandler {

  private final IWorkflowHandler<Workflow, WorkflowSaveRequest> workflowHandler;

  private final ICompanyCachDataManager companyCachDataManager;

  public WorkflowMessageHandler(final IWorkflowHandler<Workflow, WorkflowSaveRequest> workflowHandler,
                                final ICompanyCachDataManager companyCachDataManager) {

    this.workflowHandler = workflowHandler;
    this.companyCachDataManager = companyCachDataManager;
  }

  @Override
  public void callUserMessageReset(final UUID companyId,
                                   final UUID userId,
                                   boolean fromController,
                                   String authorization){

    this.companyCachDataManager.resetUserData(companyId, userId, authorization, fromController);

  }

  @Override
  public List<WorkflowMessage> readUserMessages(UUID companyId,
                                                UUID userId,
                                                SessionData sessionData){

    final List<WorkflowMessage> readList = this.companyCachDataManager.getUserWorkflowMessages(companyId, userId);

    readList.sort(new Comparator<WorkflowMessage>() {

      @Override
      public int compare(final WorkflowMessage message1, final WorkflowMessage message2) {

        return message1.getCreatedAt().isAfter(message2.getCreatedAt()) ? -1
            : message1.getCreatedAt().isBefore(message2.getCreatedAt()) ? 1 : 0;
      }
    });

    this.prepareWorkflowMessageList(readList, sessionData);

    return readList;
  }

  private List<WorkflowMessage> prepareWorkflowMessageList(final List<WorkflowMessage> messageList,
                                                           SessionData sessionData){

    final List<WorkflowMessage> resultList = new ArrayList<>();
    for (final WorkflowMessage message : messageList) {
      resultList.add(this.prepareWorkflowMessage(message, sessionData));
    }
    return resultList;
  }

  private WorkflowMessage prepareWorkflowMessage(final WorkflowMessage message, SessionData sessionData){

    Optional<Workflow> workflowOptional = this.workflowHandler.readWorkflow(message.getWorkflowId(), sessionData);

    if(workflowOptional.isPresent()){
      message.setWorkflow(workflowOptional.get());

      final WorkflowType type = message.getWorkflow().getWorkflowType();

      for (final WorkflowTypeStep step : type.getSteps()) {
        if (step.getId() ==  message.getStepId()) {
          message.setStep(step);
        }
      }

      return message;
    }

    throw new GuiCustomizedException("error by reading workflow");
  }

}
