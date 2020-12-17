package com.pth.gui.services.impl;

import com.pth.clients.clients.workflow.IWorkflowClient;
import com.pth.common.edo.WorkflowSearchFilterEdo;
import com.pth.common.edo.enums.EWorkflowType;
import com.pth.common.edo.workflow.WorkflowListEdo;
import com.pth.gui.mapper.IWorkflowMapper;
import com.pth.gui.mapper.IWorkflowSearchFilterMapper;
import com.pth.gui.models.gui.uisession.SessionData;
import com.pth.gui.models.workflow.IWorkflow;
import com.pth.gui.models.workflow.WorkflowSearchFilter;
import com.pth.gui.models.workflow.workflow.Workflow;
import com.pth.gui.services.IBasicWorkflowHandler;
import com.pth.gui.services.IWorkflowSearchHandler;
import com.pth.gui.services.impl.workflow.IInvoiceBasicWorkflowHandler;
import com.pth.gui.services.impl.workflow.ISingleTaskBasicWorkflowHandler;
import com.pth.gui.services.impl.workflow.ITestThreeTaskBasicWorkflowHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class WorkflowSearchHandler implements IWorkflowSearchHandler {

    private static final Logger logger = LoggerFactory.getLogger(WorkflowSearchHandler.class);
    private final IWorkflowClient workflowClient;
    private final IWorkflowSearchFilterMapper workflowSearchFilterMapper;
    private final IWorkflowMapper workflowMapper;
    private final IInvoiceBasicWorkflowHandler invoiceWorkflowHandler;
    private final ISingleTaskBasicWorkflowHandler singleTaskWorkflowHandler;
    private final ITestThreeTaskBasicWorkflowHandler testThreeTaskWorkflowHandler;

    public WorkflowSearchHandler(IWorkflowClient workflowClient,
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

    private IBasicWorkflowHandler getHandlerByWorkflowType(final EWorkflowType eEorkflowType) {
        if (eEorkflowType == EWorkflowType.INVOICE_WORKFLOW_TYPE) {
            return this.invoiceWorkflowHandler;
        }
        if (eEorkflowType == EWorkflowType.SINGLE_TASK_WORKFLOW_TYPE) {
            return this.singleTaskWorkflowHandler;
        }
        if (eEorkflowType == EWorkflowType.TESTTHREE_TASK_WORKFLOW_TYPE) {
            return this.testThreeTaskWorkflowHandler;
        }
        return null;
    }

    private void prepareResult(final Workflow resultWorkflow, SessionData sessionData) {
        resultWorkflow.setWorkflowType(sessionData.getWorkflowTypeById(resultWorkflow.getWorkflowTypeId()));
        resultWorkflow.setCurrentStep(sessionData.getWorkflowStepTypeById(resultWorkflow.getWorkflowTypeId(),
                                                                          resultWorkflow.getCurrentStepId()));

        final IBasicWorkflowHandler handler = this.getHandlerByWorkflowType(resultWorkflow.getWorkflowType().getTypeEnum());
        final Optional<IWorkflow> readWorkflowOptional = handler.readWorkflow(resultWorkflow.getId(), sessionData);
        resultWorkflow.setActions(readWorkflowOptional.get().getActions());
        resultWorkflow.setFiles(readWorkflowOptional.get().getFiles());

        resultWorkflow.setCurrentUserId(sessionData.getCurrentUserId());
    }

}
