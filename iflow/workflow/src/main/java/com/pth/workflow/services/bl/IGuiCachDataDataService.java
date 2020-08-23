package com.pth.workflow.services.bl;

import com.pth.workflow.exceptions.WorkflowCustomizedException;
import io.micronaut.security.authentication.Authentication;

import java.util.Set;
import java.util.UUID;

public interface IGuiCachDataDataService {

  void resetCachDataForUser(UUID companyId,
                                   UUID userId,
                                   String authorization)
          throws WorkflowCustomizedException;

  void resetCachDataForUserList(UUID companyId,
                                       Set<UUID> userIdList,
                                       String authorization)
      throws WorkflowCustomizedException;

  void resetCachDataForWorkflow(UUID companyId,
                                       UUID workflowId,
                                       String authorization)
      throws WorkflowCustomizedException;

}
