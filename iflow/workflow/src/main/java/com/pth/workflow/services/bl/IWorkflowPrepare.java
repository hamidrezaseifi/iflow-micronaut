package com.pth.workflow.services.bl;

import com.pth.common.exceptions.IFlowMessageConversionFailureException;
import com.pth.workflow.models.base.IWorkflowBaseEntity;
import io.micronaut.security.authentication.Authentication;

import java.net.MalformedURLException;
import java.util.List;


public interface IWorkflowPrepare<W extends IWorkflowBaseEntity> {

  W prepareWorkflow(final Authentication authentication,
                    final W workflow)
      throws MalformedURLException, IFlowMessageConversionFailureException;

  List<W> prepareWorkflowList(Authentication authentication,
                              List<W> workflowList)
      throws MalformedURLException, IFlowMessageConversionFailureException;
}
