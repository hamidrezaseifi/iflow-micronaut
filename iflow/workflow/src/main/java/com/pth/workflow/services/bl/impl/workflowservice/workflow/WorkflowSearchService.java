package com.pth.workflow.services.bl.impl.workflowservice.workflow;

import java.net.MalformedURLException;
import java.util.List;
import java.util.Set;

import com.pth.common.edo.workflow.WorkflowListEdo;
import com.pth.workflow.entities.workflow.WorkflowEntity;
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

  @Override
  public List<WorkflowEntity> readWorkflowListByIdentityList(final Set<String> identityList){

    logger.debug("Read workflow List");

    return this.workflowRepository.getListByIdentityList(identityList);
  }

}
