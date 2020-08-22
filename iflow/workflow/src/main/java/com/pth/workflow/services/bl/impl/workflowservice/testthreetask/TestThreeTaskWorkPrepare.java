package com.pth.workflow.services.bl.impl.workflowservice.testthreetask;

import java.net.MalformedURLException;
import java.util.*;
import java.util.stream.Collectors;

import com.pth.workflow.entities.workflow.*;
import com.pth.workflow.repositories.IWorkflowTypeRepository;
import com.pth.workflow.services.bl.IWorkflowPrepare;
import com.pth.workflow.services.bl.WorkflowPrepareBase;

import javax.inject.Singleton;

@Singleton
public class TestThreeTaskWorkPrepare extends WorkflowPrepareBase<TestThreeTaskWorkflowEntity> {

  public TestThreeTaskWorkPrepare(IWorkflowTypeRepository workflowTypeRepository) {
    super(workflowTypeRepository);
  }

}
