package com.pth.workflow.services.bl;

import com.pth.workflow.entities.WorkflowEntity;
import com.pth.workflow.models.WorkflowSearchFilter;

import java.util.List;
import java.util.Set;


public interface IWorkflowSearchService {

  public List<WorkflowEntity> search(final WorkflowSearchFilter workflowSearchFilter);

  public List<WorkflowEntity> readWorkflowListByIdentityList(final Set<String> identityList);

}
