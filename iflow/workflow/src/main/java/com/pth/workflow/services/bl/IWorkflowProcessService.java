package com.pth.workflow.services.bl;

import com.pth.common.exceptions.IFlowMessageConversionFailureException;
import com.pth.workflow.models.base.IWorkflowBaseEntity;
import com.pth.workflow.models.base.IWorkflowSaveRequest;
import io.micronaut.security.authentication.Authentication;

import java.util.List;
import java.util.Optional;
import java.util.Set;


public interface IWorkflowProcessService<W extends IWorkflowBaseEntity> {

  List<W> create(IWorkflowSaveRequest<W> model) throws IFlowMessageConversionFailureException;

  Optional<W> save(IWorkflowSaveRequest<W> request) throws IFlowMessageConversionFailureException;

  Optional<W> getByIdentity(String identity);

  List<W> getListForUser(final String identity, int status);

  List<W> getListByIdentityList(final Set<String> identityList);

  void validate(IWorkflowSaveRequest<W> model) throws IFlowMessageConversionFailureException;
}
