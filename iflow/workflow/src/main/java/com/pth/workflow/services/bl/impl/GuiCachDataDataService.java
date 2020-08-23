package com.pth.workflow.services.bl.impl;

import java.net.MalformedURLException;
import java.util.Set;
import java.util.UUID;

import com.pth.workflow.exceptions.WorkflowCustomizedException;
import com.pth.workflow.services.bl.IGuiCachDataDataService;
import io.micronaut.security.authentication.Authentication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;


@Singleton
public class GuiCachDataDataService implements IGuiCachDataDataService {

  private static final Logger logger = LoggerFactory.getLogger(GuiCachDataDataService.class);

  private final IRestTemplateCall restTemplate;
  private final WorkflowConfiguration.ModuleAccessConfig moduleAccessConfig;

  public GuiCachDataDataService(IRestTemplateCall restTemplate,
      WorkflowConfiguration.ModuleAccessConfig moduleAccessConfig) {

    this.restTemplate = restTemplate;
    this.moduleAccessConfig = moduleAccessConfig;
  }

  @Override
  public void resetCachDataForUser(final UUID companyId, final UUID userId, final Authentication authentication)
          throws WorkflowCustomizedException {

    logger.debug("Reset cach data request for user");

    this.restTemplate
        .callRestGet(
            this.moduleAccessConfig
                .generateGuieUrl(IflowRestPaths.GuiModule.CAL_CACHDATA_USER_DATARESET(companyIdentity, userIdentity)),
            EModule.CORE,
            Void.class,
            authentication.getDetails().toString(),
            true);

  }

  @Override
  public void resetCachDataForUserList(final UUID companyId,
                                       final Set<UUID> userIdList,
                                       final Authentication authentication)
      throws WorkflowCustomizedException, MalformedURLException, IFlowMessageConversionFailureException {

    logger.debug("Reset cach data request for user list");

    final IdentityListEdo idListEdo = new IdentityListEdo(userIdentityList);
    this.restTemplate
        .callRestPost(
            this.moduleAccessConfig.generateGuieUrl(IflowRestPaths.GuiModule.CAL_CACHDATA_USERLIST_DATARESET(companyIdentity)),
            EModule.CORE,
            idListEdo,
            Void.class,
            authentication.getDetails().toString(),
            true);

  }

  @Override
  public void resetCachDataForWorkflow(final UUID companyId, final UUID workflowId, final Authentication authentication)
      throws WorkflowCustomizedException, MalformedURLException, IFlowMessageConversionFailureException {

    logger.debug("Reset cach data request for workflow");

    this.restTemplate
        .callRestGet(
            this.moduleAccessConfig
                .generateGuieUrl(IflowRestPaths.GuiModule.CAL_CACHDATA_WORKFLOW_DATARESET(companyIdentity, workflowIdentity)),
            EModule.CORE,
            Void.class,
            authentication.getDetails().toString(),
            true);

  }

}
