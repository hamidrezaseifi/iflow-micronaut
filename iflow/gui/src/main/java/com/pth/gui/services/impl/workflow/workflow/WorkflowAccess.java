package com.pth.gui.services.impl.workflow.workflow;

import java.net.MalformedURLException;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pth.iflow.common.enums.EModule;
import com.pth.iflow.common.exceptions.IFlowMessageConversionFailureException;
import com.pth.iflow.common.models.edo.IdentityListEdo;
import com.pth.iflow.common.models.edo.workflow.WorkflowEdo;
import com.pth.iflow.common.models.edo.workflow.WorkflowListEdo;
import com.pth.iflow.common.rest.IRestTemplateCall;
import com.pth.iflow.gui.configurations.GuiConfiguration;
import com.pth.iflow.gui.exceptions.GuiCustomizedException;
import com.pth.iflow.gui.models.mapper.GuiModelEdoMapper;
import com.pth.iflow.gui.models.workflow.workflow.Workflow;
import com.pth.iflow.gui.models.workflow.workflow.WorkflowSaveRequest;
import com.pth.iflow.gui.services.IWorkflowAccess;

@Service
public class WorkflowAccess implements IWorkflowAccess<Workflow, WorkflowSaveRequest> {

  private static final Logger logger = LoggerFactory.getLogger(WorkflowAccess.class);

  private final IRestTemplateCall restTemplate;
  private final GuiConfiguration.WorkflowModuleAccessConfig moduleAccessConfig;

  public WorkflowAccess(@Autowired final IRestTemplateCall restTemplate,
      @Autowired final GuiConfiguration.WorkflowModuleAccessConfig moduleAccessConfig) {

    this.restTemplate = restTemplate;
    this.moduleAccessConfig = moduleAccessConfig;
  }

  @Override
  public Workflow readWorkflow(final String workflowIdentity, final String token)
      throws GuiCustomizedException, MalformedURLException, IFlowMessageConversionFailureException {

    logger.debug("Read workflow: {}", workflowIdentity);

    final WorkflowEdo responseEdo = this.restTemplate
        .callRestGet(this.moduleAccessConfig.getReadWorkflowUri(workflowIdentity),
            EModule.WORKFLOW, WorkflowEdo.class, token, true);

    return GuiModelEdoMapper.fromEdo(responseEdo);
  }

  @Override
  public List<Workflow> createWorkflow(final WorkflowSaveRequest createRequest, final String token)
      throws GuiCustomizedException, MalformedURLException, IFlowMessageConversionFailureException {

    throw new GuiCustomizedException("not implemented");

  }

  @Override
  public Workflow saveWorkflow(final WorkflowSaveRequest request, final String token)
      throws GuiCustomizedException, MalformedURLException, IFlowMessageConversionFailureException {

    throw new GuiCustomizedException("not implemented");

  }

  @Override
  public void validateWorkflow(final WorkflowSaveRequest request, final String token)
      throws GuiCustomizedException, MalformedURLException, IFlowMessageConversionFailureException {

    throw new GuiCustomizedException("not implemented");

  }

  @Override
  public List<Workflow> readWorkflowList(final Set<String> workflowIdentityList, final String token)
      throws GuiCustomizedException, MalformedURLException, IFlowMessageConversionFailureException {

    logger.debug("Read workflow by identity list");

    final IdentityListEdo listEdo = new IdentityListEdo(workflowIdentityList);
    final WorkflowListEdo responseListEdo = this.restTemplate
        .callRestPost(this.moduleAccessConfig.getReadWorkflowListUri(), EModule.WORKFLOW,
            listEdo, WorkflowListEdo.class, token, true);

    final List<Workflow> list = GuiModelEdoMapper.fromWorkflowEdoList(responseListEdo.getWorkflows());

    return list;
  }

}
