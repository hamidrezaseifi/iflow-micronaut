package com.pth.workflow.services.bl.strategy;

import com.pth.common.exceptions.IFlowCustomizedException;
import com.pth.common.exceptions.IFlowMessageConversionFailureException;
import com.pth.workflow.exceptions.WorkflowCustomizedException;

import java.net.MalformedURLException;


public interface IWorkflowSaveStrategyStep {

  void process() throws WorkflowCustomizedException;

  boolean shouldProcess();
}
