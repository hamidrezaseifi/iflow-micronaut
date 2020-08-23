package com.pth.workflow.services.bl;

import com.pth.workflow.entities.WorkflowTypeEntity;
import com.pth.workflow.entities.WorkflowTypeStepEntity;

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
