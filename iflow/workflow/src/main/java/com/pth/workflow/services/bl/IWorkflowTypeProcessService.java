package com.pth.workflow.services.bl;

import com.pth.common.exceptions.IFlowMessageConversionFailureException;
import com.pth.workflow.entities.workflow.WorkflowTypeEntity;
import com.pth.workflow.entities.workflow.WorkflowTypeStepEntity;
import io.micronaut.security.authentication.Authentication;

import java.net.MalformedURLException;
import java.util.List;
import java.util.Optional;
import java.util.Set;


public interface IWorkflowTypeProcessService {

  public Optional<WorkflowTypeEntity> getByIdentity(String identity);

  public List<WorkflowTypeEntity> getListByCompanyIdentity(String identity);

  public List<WorkflowTypeStepEntity> getStepsByIdentity(String identity);

  public List<WorkflowTypeEntity> getListByIdentityList(final Set<String> identityList);
}
