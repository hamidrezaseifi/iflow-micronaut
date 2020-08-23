package com.pth.workflow.services.bl.strategy;

import com.pth.common.exceptions.IFlowMessageConversionFailureException;
import com.pth.workflow.models.base.IWorkflowBaseEntity;
import com.pth.workflow.models.base.IWorkflowSaveRequest;
import io.micronaut.security.authentication.Authentication;


public interface IWorkflowSaveStrategyFactory<W extends IWorkflowBaseEntity> {

  IWorkflowSaveStrategy<W> selectSaveWorkStrategy(IWorkflowSaveRequest<W> workflowSaveRequest,
                                                  final Authentication authentication);

  IWorkflowSaveStrategy<W> selectValidationWorkStrategy(IWorkflowSaveRequest<W> workflowSaveRequest,
                                                        final Authentication authentication);

}
