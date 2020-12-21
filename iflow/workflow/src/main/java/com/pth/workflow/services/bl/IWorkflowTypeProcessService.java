package com.pth.workflow.services.bl;

import com.pth.workflow.entities.WorkflowTypeEntity;
import com.pth.workflow.entities.WorkflowTypeStepEntity;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;


public interface IWorkflowTypeProcessService {

  Optional<WorkflowTypeEntity> getById(UUID id);

  List<WorkflowTypeEntity> getListByCompanyId(UUID id);

  List<WorkflowTypeStepEntity> getStepsById(UUID id);

}
