package com.pth.workflow.services.bl.impl;

import java.net.MalformedURLException;
import java.util.Set;
import java.util.UUID;

import com.pth.clients.gui.IGui001Client;
import com.pth.common.edo.IdListEdo;
import com.pth.common.edo.IdentityListEdo;
import com.pth.workflow.exceptions.WorkflowCustomizedException;
import com.pth.workflow.services.bl.IGuiCachDataDataService;
import io.micronaut.security.authentication.Authentication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;


@Singleton
public class GuiCachDataDataService implements IGuiCachDataDataService {

  private static final Logger logger = LoggerFactory.getLogger(GuiCachDataDataService.class);

  private final IGui001Client gui001Client;

  public GuiCachDataDataService(IGui001Client gui001Client) {

    this.gui001Client = gui001Client;
  }

  @Override
  public void resetCachDataForUser(final UUID companyId, final UUID userId, final String authorization)
          throws WorkflowCustomizedException {

    logger.debug("Reset cach data request for user");

    gui001Client.calUserDataResetByCompanyAndUserId(authorization, companyId, userId);
  }

  @Override
  public void resetCachDataForUserList(final UUID companyId,
                                       final Set<UUID> userIdList,
                                       final String authorization){

    logger.debug("Reset cach data request for user list");

    IdListEdo idListEdo = new IdListEdo(userIdList);
    gui001Client.calUserListDataResetByCompanyAndUserId(authorization, companyId, idListEdo);

  }

  @Override
  public void resetCachDataForWorkflow(final UUID companyId, final UUID workflowId, final String authorization){

    logger.debug("Reset cach data request for workflow");

    gui001Client.calWorkflowDataResetByCompanyAndUserId(authorization, companyId, workflowId);


  }

}
