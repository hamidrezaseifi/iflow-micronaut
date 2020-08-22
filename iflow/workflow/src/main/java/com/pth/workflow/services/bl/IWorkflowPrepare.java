package com.pth.workflow.services.bl;

import com.pth.common.exceptions.IFlowMessageConversionFailureException;
import com.pth.workflow.models.base.IWorkflowBaseEntity;
import io.micronaut.security.authentication.Authentication;

import java.net.MalformedURLException;
import java.util.List;
import java.util.Optional;


public interface IWorkflowPrepare<W extends IWorkflowBaseEntity> {

  Optional<W> prepareWorkflow(final W workflow);

  List<W> prepareWorkflowList(List<W> workflowList);
}
