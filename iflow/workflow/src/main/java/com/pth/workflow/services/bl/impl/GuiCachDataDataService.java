package com.pth.workflow.services.bl.impl;

import java.util.Set;
import java.util.UUID;

import com.pth.clients.clients.gui.IGuiCachDataClient;
import com.pth.common.edo.IdListEdo;
import com.pth.workflow.exceptions.WorkflowCustomizedException;
import com.pth.workflow.services.bl.IGuiCachDataDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;


@Singleton
public class GuiCachDataDataService implements IGuiCachDataDataService {

  private static final Logger logger = LoggerFactory.getLogger(GuiCachDataDataService.class);

  private final IGuiCachDataClient guiCachDataClient;

  public GuiCachDataDataService(IGuiCachDataClient guiCachDataClient) {

    this.guiCachDataClient = guiCachDataClient;
  }

  @Override
  public void resetCachDataForUser(final UUID companyId, final UUID userId, final String authorization)
          throws WorkflowCustomizedException {

    logger.debug("Reset cach data request for user");

    guiCachDataClient.resetUserData(companyId, userId, authorization);
  }

  @Override
  public void resetCachDataForUserList(final UUID companyId,
                                       final Set<UUID> userIdList,
                                       final String authorization){

    logger.debug("Reset cach data request for user list");

    IdListEdo idListEdo = new IdListEdo(userIdList);
    guiCachDataClient.resetUserListData(companyId, idListEdo, authorization);

  }

  @Override
  public void resetCachDataForWorkflow(final UUID companyId, final UUID workflowId, final String authorization){

    logger.debug("Reset cach data request for workflow");

    guiCachDataClient.resetWorkflowData(companyId, workflowId, authorization);


  }

}
