package com.pth.workflow.services.bl;

import com.pth.common.exceptions.IFlowMessageConversionFailureException;
import com.pth.workflow.exceptions.WorkflowCustomizedException;
import com.pth.workflow.models.base.IWorkflowBaseEntity;
import com.pth.workflow.models.base.IWorkflowSaveRequest;
import io.micronaut.http.annotation.Header;
import io.micronaut.security.authentication.Authentication;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;


public interface IWorkflowProcessService<W extends IWorkflowBaseEntity> {

  Optional<W> getById(UUID id);

  List<W> create(IWorkflowSaveRequest<W> model, String authorization) throws WorkflowCustomizedException;

  Optional<W> save(IWorkflowSaveRequest<W> request, String authorization) throws WorkflowCustomizedException;

  Optional<W> getByIdentity(String identity);

  List<W> getListForUser(final UUID id, int status);

  List<W> getListByIdentityList(final Set<String> identityList);

  void validate(IWorkflowSaveRequest<W> model, String authorization) throws WorkflowCustomizedException;
}
