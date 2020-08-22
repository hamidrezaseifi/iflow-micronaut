package com.pth.workflow.services.bl;

import com.pth.common.exceptions.IFlowMessageConversionFailureException;
import com.pth.workflow.entities.workflow.WorkflowEntity;
import com.pth.workflow.models.WorkflowSearchFilter;
import io.micronaut.security.authentication.Authentication;

import java.net.MalformedURLException;
import java.util.List;
import java.util.Set;


public interface IWorkflowSearchService {

  public List<WorkflowEntity> search(final WorkflowSearchFilter workflowSearchFilter,
                                     Authentication authentication)
      throws IFlowMessageConversionFailureException;

  public List<WorkflowEntity> readWorkflowListByIdentityList(final Set<String> identityList,
                                                       Authentication authentication)
      throws IFlowMessageConversionFailureException;

}
