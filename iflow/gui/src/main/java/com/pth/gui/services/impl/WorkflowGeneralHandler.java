package com.pth.gui.services.impl;

import com.pth.clients.clients.workflow.IWorkflowClient;
import com.pth.common.edo.WorkflowSearchFilterEdo;
import com.pth.common.edo.enums.EWorkflowType;
import com.pth.common.edo.workflow.WorkflowEdo;
import com.pth.common.edo.workflow.WorkflowListEdo;
import com.pth.gui.mapper.IWorkflowMapper;
import com.pth.gui.mapper.IWorkflowSearchFilterMapper;
import com.pth.gui.models.gui.uisession.SessionData;
import com.pth.gui.models.workflow.WorkflowSearchFilter;
import com.pth.gui.models.workflow.base.WorkflowBased;
import com.pth.gui.models.workflow.workflow.Workflow;
import com.pth.gui.services.IBasicWorkflowHandler;
import com.pth.gui.services.IWorkflowGeneralHandler;
import com.pth.gui.services.impl.workflow.IInvoiceBasicWorkflowHandler;
import com.pth.gui.services.impl.workflow.ISingleTaskBasicWorkflowHandler;
import com.pth.gui.services.impl.workflow.ITestThreeTaskBasicWorkflowHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;
import java.util.*;

@Singleton
public class WorkflowGeneralHandler implements IWorkflowGeneralHandler {

    private static final Logger logger = LoggerFactory.getLogger(WorkflowGeneralHandler.class);
    private final IWorkflowClient workflowClient;
    private final IWorkflowSearchFilterMapper workflowSearchFilterMapper;
    private final IWorkflowMapper workflowMapper;
    private final IInvoiceBasicWorkflowHandler invoiceWorkflowHandler;
    private final ISingleTaskBasicWorkflowHandler singleTaskWorkflowHandler;
    private final ITestThreeTaskBasicWorkflowHandler testThreeTaskWorkflowHandler;

    public WorkflowGeneralHandler(IWorkflowClient workflowClient,
                                  IWorkflowSearchFilterMapper workflowSearchFilterMapper,
                                  IWorkflowMapper workflowMapper,
                                  IInvoiceBasicWorkflowHandler invoiceWorkflowHandler,
                                  ISingleTaskBasicWorkflowHandler singleTaskWorkflowHandler,
                                  ITestThreeTaskBasicWorkflowHandler testThreeTaskWorkflowHandler) {
        this.workflowClient = workflowClient;
        this.workflowSearchFilterMapper = workflowSearchFilterMapper;
        this.workflowMapper = workflowMapper;
        this.invoiceWorkflowHandler = invoiceWorkflowHandler;
        this.singleTaskWorkflowHandler = singleTaskWorkflowHandler;
        this.testThreeTaskWorkflowHandler = testThreeTaskWorkflowHandler;
    }

    @Override
    public List<Workflow> searchWorkflow(final WorkflowSearchFilter workflowSearchFilter, SessionData sessionData) {
        logger.debug("Search workflow");

        if (workflowSearchFilter.isMeAssigned()) {
            workflowSearchFilter.addAssignedUserId(sessionData.getCurrentUserId());
        }

        workflowSearchFilter.setCompanyId(sessionData.getCompanyId());

        WorkflowSearchFilterEdo workflowSearchFilterEdo = workflowSearchFilterMapper.toEdo(workflowSearchFilter);

        final Optional<WorkflowListEdo> responseListEdoOptional =
                this.workflowClient.searchWorkflow(sessionData.getRefreshToken(), workflowSearchFilterEdo);

        List<Workflow> list = new ArrayList<>();

        if(responseListEdoOptional.isPresent()){
            list = workflowMapper.fromEdoList(responseListEdoOptional.get().getWorkflows());
            for (final Workflow resultWorkflow : list) {
                this.prepareResult(resultWorkflow, sessionData);
            }
        }

        return list;

    }

    @Override
    public Optional<Workflow> readById(UUID id, SessionData sessionData) {

        Optional<WorkflowEdo> workflowEdoOptional = this.workflowClient.read(sessionData.getRefreshToken(), id);
        if(workflowEdoOptional.isPresent()) {
            Workflow workflow = workflowMapper.fromEdo(workflowEdoOptional.get());
            prepareResult(workflow, sessionData);
            return Optional.of(workflow);
        }
        return  Optional.empty();
    }

    public IBasicWorkflowHandler getHandlerByWorkflowType(final EWorkflowType workflowType) {
        if (workflowType == EWorkflowType.INVOICE_WORKFLOW_TYPE) {
            return this.invoiceWorkflowHandler;
        }
        if (workflowType == EWorkflowType.SINGLE_TASK_WORKFLOW_TYPE) {
            return this.singleTaskWorkflowHandler;
        }
        if (workflowType == EWorkflowType.TESTTHREE_TASK_WORKFLOW_TYPE) {
            return this.testThreeTaskWorkflowHandler;
        }
        return null;
    }

    private void prepareResult(final Workflow resultWorkflow, SessionData sessionData) {
        resultWorkflow.setWorkflowType(sessionData.getWorkflowTypeById(resultWorkflow.getWorkflowTypeId()));
        resultWorkflow.setCurrentStep(sessionData.getWorkflowStepTypeById(resultWorkflow.getWorkflowTypeId(),
                                                                          resultWorkflow.getCurrentStepId()));

        final IBasicWorkflowHandler handler = this.getHandlerByWorkflowType(resultWorkflow.getWorkflowType().getTypeEnum());
        final Optional<WorkflowBased> readWorkflowOptional = handler.readWorkflow(resultWorkflow.getId(), sessionData);
        resultWorkflow.setActions(readWorkflowOptional.get().getWorkflow().getActions());
        resultWorkflow.setFiles(readWorkflowOptional.get().getWorkflow().getFiles());

        resultWorkflow.setCurrentUserId(sessionData.getCurrentUserId());
    }

}
