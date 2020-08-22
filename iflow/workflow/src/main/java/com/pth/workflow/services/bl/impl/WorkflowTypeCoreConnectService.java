package com.pth.workflow.services.bl.impl;

import java.net.MalformedURLException;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.pth.iflow.common.enums.EModule;
import com.pth.iflow.common.exceptions.IFlowMessageConversionFailureException;
import com.pth.iflow.common.models.edo.WorkflowTypeEdo;
import com.pth.iflow.common.models.edo.WorkflowTypeListEdo;
import com.pth.iflow.common.models.edo.WorkflowTypeStepListEdo;
import com.pth.iflow.common.rest.IRestTemplateCall;
import com.pth.iflow.common.rest.IflowRestPaths;
import com.pth.iflow.workflow.bl.IWorkflowTypeDataService;
import com.pth.iflow.workflow.config.WorkflowConfiguration;
import com.pth.iflow.workflow.exceptions.WorkflowCustomizedException;
import com.pth.iflow.workflow.models.WorkflowType;
import com.pth.iflow.workflow.models.WorkflowTypeStep;
import com.pth.iflow.workflow.models.mapper.WorkflowModelEdoMapper;

@Service
public class WorkflowTypeCoreConnectService implements IWorkflowTypeDataService {

  private static final Logger logger = LoggerFactory.getLogger(WorkflowTypeCoreConnectService.class);

  private final IRestTemplateCall restTemplate;
  private final WorkflowConfiguration.ModuleAccessConfig moduleAccessConfig;

  public WorkflowTypeCoreConnectService(@Autowired final IRestTemplateCall restTemplate,
      @Autowired final WorkflowConfiguration.ModuleAccessConfig moduleAccessConfig) {

    this.restTemplate = restTemplate;
    this.moduleAccessConfig = moduleAccessConfig;
  }

  @Override
  public WorkflowType getByIdentity(final String identity, final Authentication authentication)
      throws WorkflowCustomizedException, MalformedURLException, IFlowMessageConversionFailureException {

    logger.debug("Request workflow data for id {}", identity);

    final WorkflowTypeEdo edo = this.restTemplate
        .callRestGet(
            this.moduleAccessConfig.generateCoreUrl(IflowRestPaths.CoreModule.READ_WORKFLOWTYPE_BY_IDENTITY(identity)),
            EModule.CORE,
            WorkflowTypeEdo.class,
            authentication.getDetails().toString(),
            true);

    return WorkflowModelEdoMapper.fromEdo(edo);
  }

  @Override
  public List<WorkflowType> getListByCompanyIdentity(final String identity, final Authentication authentication)
      throws WorkflowCustomizedException, MalformedURLException, IFlowMessageConversionFailureException {

    logger.debug("Request workflow list for company id {}", identity);

    final WorkflowTypeListEdo edoList = this.restTemplate
        .callRestGet(
            this.moduleAccessConfig.generateCoreUrl(IflowRestPaths.CoreModule.READ_WORKFLOWTYPE_LIST_BY_COMPANY(identity)),
            EModule.CORE,
            WorkflowTypeListEdo.class,
            authentication.getDetails().toString(),
            true);

    return WorkflowModelEdoMapper.fromWorkflowTypeEdoList(edoList.getWorkflowTypes());
  }

  @Override
  public List<WorkflowType> getListByIdentityList(final Set<String> identityList, final Authentication authentication)
      throws WorkflowCustomizedException, MalformedURLException, IFlowMessageConversionFailureException {

    logger.debug("Request workflow list for id list");

    final WorkflowTypeListEdo edoList = this.restTemplate
        .callRestPost(
            this.moduleAccessConfig.generateCoreUrl(IflowRestPaths.CoreModule.READ_WORKFLOWTYPE_LIST()),
            EModule.CORE,
            identityList,
            WorkflowTypeListEdo.class,
            authentication.getDetails().toString(),
            true);

    return WorkflowModelEdoMapper.fromWorkflowTypeEdoList(edoList.getWorkflowTypes());
  }

  @Override
  public List<WorkflowTypeStep> getStepsByIdentity(final String identity, final Authentication authentication)
      throws WorkflowCustomizedException, MalformedURLException, IFlowMessageConversionFailureException {

    logger.debug("Request workflow-step list for workflow id {}", identity);

    final WorkflowTypeStepListEdo edoList = this.restTemplate
        .callRestGet(
            this.moduleAccessConfig.generateCoreUrl(IflowRestPaths.CoreModule.READ_WORKFLOWTYPESTEP_LIST_BY_WORKFLOW(identity)),
            EModule.CORE,
            WorkflowTypeStepListEdo.class,
            authentication.getDetails().toString(),
            true);

    return WorkflowModelEdoMapper.fromWorkflowTypeStepEdoList(edoList.getWorkflowTypeSteps());
  }

}
