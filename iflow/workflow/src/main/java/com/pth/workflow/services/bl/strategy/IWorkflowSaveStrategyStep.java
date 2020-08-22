package com.pth.workflow.services.bl.strategy;

import com.pth.common.exceptions.IFlowMessageConversionFailureException;

import java.net.MalformedURLException;


public interface IWorkflowSaveStrategyStep {

  public void process() throws IFlowMessageConversionFailureException;

  public boolean shouldProcess();
}
