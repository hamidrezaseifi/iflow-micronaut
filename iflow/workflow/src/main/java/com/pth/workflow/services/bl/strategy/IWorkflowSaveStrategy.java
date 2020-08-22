package com.pth.workflow.services.bl.strategy;

import com.pth.common.exceptions.IFlowMessageConversionFailureException;
import com.pth.workflow.models.base.IWorkflowBaseEntity;

import java.util.List;

public interface IWorkflowSaveStrategy<W extends IWorkflowBaseEntity> {

  public void setup();

  public void process() throws IFlowMessageConversionFailureException;

  public W getSingleProceedWorkflow();

  public List<W> getProceedWorkflowList();
}
