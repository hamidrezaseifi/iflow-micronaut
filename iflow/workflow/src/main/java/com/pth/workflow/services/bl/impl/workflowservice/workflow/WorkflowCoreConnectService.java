package com.pth.workflow.services.bl.impl.workflowservice.workflow;

import java.net.MalformedURLException;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.pth.iflow.common.enums.EModule;
import com.pth.iflow.common.exceptions.EIFlowErrorType;
import com.pth.iflow.common.exceptions.IFlowMessageConversionFailureException;
import com.pth.iflow.common.models.edo.workflow.WorkflowEdo;
import com.pth.iflow.common.models.edo.workflow.WorkflowListEdo;
import com.pth.iflow.common.rest.IRestTemplateCall;
import com.pth.iflow.common.rest.IflowRestPaths;
import com.pth.iflow.workflow.bl.IWorkflowDataService;
import com.pth.iflow.workflow.config.WorkflowConfiguration;
import com.pth.iflow.workflow.exceptions.WorkflowCustomizedException;
import com.pth.iflow.workflow.models.mapper.WorkflowModelEdoMapper;
import com.pth.iflow.workflow.models.workflow.Workflow;

@Service
public class WorkflowCoreConnectService implements IWorkflowDataService<Workflow> {

  private final Logger logger = LoggerFactory.getLogger(getClass());

  private final IRestTemplateCall restTemplate;
  private final WorkflowConfiguration.ModuleAccessConfig moduleAccessConfig;

  public WorkflowCoreConnectService(@Autowired final IRestTemplateCall restTemplate,
      @Autowired final WorkflowConfiguration.ModuleAccessConfig moduleAccessConfig) {

    this.restTemplate = restTemplate;
    this.moduleAccessConfig = moduleAccessConfig;
  }

  @Override
  public Workflow getByIdentity(final String identity, final Authentication authentication)
      throws WorkflowCustomizedException, MalformedURLException, IFlowMessageConversionFailureException {

    logger.debug("Request workflow data for identity {}", identity);

    final WorkflowEdo edo = this.restTemplate
        .callRestGet(
            this.moduleAccessConfig.generateCoreUrl(IflowRestPaths.CoreModule.READ_WORKFLOW_BY_IDENTITY(identity)),
            EModule.CORE,
            WorkflowEdo.class,
            authentication.getDetails().toString(),
            true);

    return WorkflowModelEdoMapper.fromEdo(edo);
  }

  @Override
  public List<Workflow> getListByIdentityList(final Set<String> idList, final Authentication authentication)
      throws WorkflowCustomizedException, MalformedURLException, IFlowMessageConversionFailureException {

    logger.debug("Request workflow list for id list {}", idList);

    final WorkflowListEdo edoList = this.restTemplate
        .callRestPost(
            this.moduleAccessConfig.generateCoreUrl(IflowRestPaths.CoreModule.READ_WORKFLOWLIST()),
            EModule.CORE,
            idList,
            WorkflowListEdo.class,
            authentication.getDetails().toString(),
            true);

    return WorkflowModelEdoMapper.fromWorkflowEdoList(edoList.getWorkflows());
  }

  @Override
  public Workflow save(final Workflow model, final Authentication authentication)
      throws WorkflowCustomizedException, MalformedURLException, IFlowMessageConversionFailureException {

    throw new WorkflowCustomizedException("not implemented", EIFlowErrorType.SERVICE_NOT_IMPLEMENTED);
  }

  @Override
  public List<Workflow> getListForUser(final String identity, final int status, final Authentication authentication)
      throws WorkflowCustomizedException, MalformedURLException, IFlowMessageConversionFailureException {

    throw new WorkflowCustomizedException("not implemented", EIFlowErrorType.SERVICE_NOT_IMPLEMENTED);
  }

}
