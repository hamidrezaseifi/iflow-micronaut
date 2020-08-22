package com.pth.workflow.services.bl;

import com.pth.common.exceptions.IFlowMessageConversionFailureException;
import com.pth.workflow.models.base.IWorkflowBaseEntity;
import com.pth.workflow.models.base.IWorkflowSaveRequest;
import io.micronaut.security.authentication.Authentication;

import java.util.List;
import java.util.Set;


public interface IWorkflowProcessService<W extends IWorkflowBaseEntity> {

  public List<W> create(IWorkflowSaveRequest<W> model,
                        Authentication authentication)
      throws IFlowMessageConversionFailureException;

  public W save(IWorkflowSaveRequest<W> request,
                Authentication authentication)
      throws IFlowMessageConversionFailureException;

  public W getByIdentity(String identity,
                         Authentication authentication)
      throws IFlowMessageConversionFailureException;

  public List<W> getListForUser(final String identity,
                                int status,
                                Authentication authentication)
      throws IFlowMessageConversionFailureException;

  public List<W> getListByIdentityList(final Set<String> identityList,
                                       Authentication authentication)
      throws IFlowMessageConversionFailureException;

  public void validate(IWorkflowSaveRequest<W> model,
                       Authentication authentication)
      throws IFlowMessageConversionFailureException;
}
