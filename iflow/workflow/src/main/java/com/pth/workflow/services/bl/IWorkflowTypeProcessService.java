package com.pth.workflow.services.bl;

import com.pth.common.exceptions.IFlowMessageConversionFailureException;
import com.pth.workflow.entities.workflow.WorkflowTypeEntity;
import com.pth.workflow.entities.workflow.WorkflowTypeStepEntity;
import io.micronaut.security.authentication.Authentication;

import java.net.MalformedURLException;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;


public interface IWorkflowTypeProcessService {

  public Optional<WorkflowTypeEntity> getById(UUID id);

  public Optional<WorkflowTypeEntity> getByIdentity(String identity);

  public List<WorkflowTypeEntity> getListByCompanyId(UUID id);

  public List<WorkflowTypeStepEntity> getStepsById(UUID id);

  public List<WorkflowTypeEntity> getListByIdentityList(final Set<String> identityList);
}
