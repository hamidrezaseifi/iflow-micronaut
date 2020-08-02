package com.pth.iflow.profile.service.access;

import java.net.MalformedURLException;
import java.util.List;

import com.pth.iflow.common.exceptions.IFlowMessageConversionFailureException;
import com.pth.iflow.profile.exceptions.ProfileCustomizedException;
import com.pth.iflow.profile.model.WorkflowMessage;

public interface IWorkflowMessageAccessService {

  public List<WorkflowMessage> getWorkflowMessageListByUser(final String userId)
      throws ProfileCustomizedException, MalformedURLException, IFlowMessageConversionFailureException;

  public List<WorkflowMessage> getWorkflowMessageListByWorkflow(final String workflowId)
      throws ProfileCustomizedException, MalformedURLException, IFlowMessageConversionFailureException;
}
