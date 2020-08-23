package com.pth.workflow.services.bl.impl;

import java.util.*;

import com.pth.workflow.entities.WorkflowTypeEntity;
import com.pth.workflow.entities.WorkflowTypeStepEntity;
import com.pth.workflow.repositories.IWorkflowTypeRepository;
import com.pth.workflow.services.bl.IWorkflowTypeProcessService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import javax.inject.Singleton;

@Singleton
public class WorkflowTypeProcessService implements IWorkflowTypeProcessService {

  private static final Logger logger = LoggerFactory.getLogger(WorkflowTypeProcessService.class);

  private final IWorkflowTypeRepository workflowTypeRepository;

  public WorkflowTypeProcessService( IWorkflowTypeRepository workflowTypeRepository) {

    this.workflowTypeRepository = workflowTypeRepository;
  }

  @Override
  public Optional<WorkflowTypeEntity> getById(final UUID id){

    logger.debug("Request workflow data for id {}", id);

    return this.workflowTypeRepository.getById(id);

  }

  @Override
  public Optional<WorkflowTypeEntity> getByIdentity(final String identity){

    logger.debug("Request workflow data for id {}", identity);

    return this.workflowTypeRepository.getByIdentity(identity);

  }

  @Override
  public List<WorkflowTypeEntity> getListByCompanyId(final UUID id){

    logger.debug("Request workflow list for company id {}", id);

    return this.workflowTypeRepository.getListByCompanyId(id);
  }

  @Override
  public List<WorkflowTypeEntity> getListByIdentityList(final Set<String> identityList){

    logger.debug("Request workflow list for id list {}");

    return this.workflowTypeRepository.getListByIdentityList(identityList);
  }

  @Override
  public List<WorkflowTypeStepEntity> getStepsById(final UUID id){

    logger.debug("Request workflow-step list for workflow-type id {}", id);

    Optional<WorkflowTypeEntity> workflowTypeEntityOptional = this.workflowTypeRepository.getById(id);
    if(workflowTypeEntityOptional.isPresent()){
      return workflowTypeEntityOptional.get().getStepsAsList();
    }
    return new ArrayList<>();
  }

}
