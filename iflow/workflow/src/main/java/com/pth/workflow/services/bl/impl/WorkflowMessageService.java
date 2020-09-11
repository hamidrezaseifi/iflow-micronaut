package com.pth.workflow.services.bl.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.pth.common.edo.enums.EWorkflowMessageStatus;
import com.pth.workflow.entities.WorkflowMessageEntity;
import com.pth.workflow.repositories.IWorkflowMessageRepository;
import com.pth.workflow.services.IWorkflowMessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.inject.Singleton;


@Singleton
public class WorkflowMessageService implements IWorkflowMessageService {

  private static final Logger logger = LoggerFactory.getLogger(WorkflowMessageService.class);

  private final IWorkflowMessageRepository workflowMessageRepository;

  public WorkflowMessageService(IWorkflowMessageRepository workflowMessageRepository) {

    this.workflowMessageRepository = workflowMessageRepository;
  }

  @Override
  public List<WorkflowMessageEntity> getListForUser(final UUID userId,
                                                    final int status){

    logger.debug("Request workflow message list for user id {}", userId);


    return workflowMessageRepository.getListForUser(userId, status);
  }

  @Override
  public List<WorkflowMessageEntity> getListForWorkflow(final UUID workflowID){

    logger.debug("Request workflow message list for workflow id {}", workflowID);

    return workflowMessageRepository.getListForWorkflow(workflowID);
  }

  @Override
  public Optional<WorkflowMessageEntity> save(final WorkflowMessageEntity message){

    logger.debug("Save workflow message ");

    workflowMessageRepository.save(message);

    return workflowMessageRepository.getById(message.getId());
  }

  @Override
  public void updateWorkflowMessageStatus(final UUID workflowID,
                                          final UUID stepId,
                                          final EWorkflowMessageStatus status){

    logger.debug("Save workflow message ");

    workflowMessageRepository.updateWorkflowMessageStatus(workflowID, stepId, status);

  }

  @Override
  public void updateUserAndWorkflowMessageStatus(final UUID workflowID,
                                                 final UUID stepId,
                                                 final UUID userId,
                                                 final EWorkflowMessageStatus status){

    logger.debug("Save workflow message ");

    workflowMessageRepository.updateWorkflowMessageStatus(workflowID, stepId, userId, status);

  }

}
