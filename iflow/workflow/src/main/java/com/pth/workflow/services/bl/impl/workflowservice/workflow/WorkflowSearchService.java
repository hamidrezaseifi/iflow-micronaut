package com.pth.workflow.services.bl.impl.workflowservice.workflow;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import com.pth.workflow.entities.WorkflowEntity;
import com.pth.workflow.models.WorkflowSearchFilter;
import com.pth.workflow.repositories.IWorkflowRepository;
import com.pth.workflow.services.bl.IWorkflowSearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;


@Singleton
public class WorkflowSearchService implements IWorkflowSearchService {

  private final Logger logger = LoggerFactory.getLogger(getClass());

  private final IWorkflowRepository workflowRepository;

  public WorkflowSearchService(IWorkflowRepository workflowRepository) {

    this.workflowRepository = workflowRepository;
  }

  @Override
  public List<WorkflowEntity> search(final WorkflowSearchFilter workflowSearchFilter) {

    logger.debug("Search workflow");

    return this.workflowRepository.search(workflowSearchFilter);
  }



}
