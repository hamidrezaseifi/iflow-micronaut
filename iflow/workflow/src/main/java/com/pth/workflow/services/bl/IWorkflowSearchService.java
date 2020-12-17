package com.pth.workflow.services.bl;

import com.pth.workflow.entities.WorkflowEntity;
import com.pth.workflow.models.WorkflowSearchFilter;

import java.util.List;
import java.util.Set;
import java.util.UUID;


public interface IWorkflowSearchService {

  public List<WorkflowEntity> search(final WorkflowSearchFilter workflowSearchFilter);


}
