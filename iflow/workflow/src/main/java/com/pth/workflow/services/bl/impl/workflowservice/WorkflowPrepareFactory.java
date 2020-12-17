package com.pth.workflow.services.bl.impl.workflowservice;

import com.pth.workflow.repositories.*;
import com.pth.workflow.services.bl.IWorkflowPrepare;
import com.pth.workflow.services.bl.impl.workflowservice.invoice.InvoiceWorkflowPrepare;
import com.pth.workflow.services.bl.impl.workflowservice.singletask.SingleTaskWorkflowPrepare;
import com.pth.workflow.services.bl.impl.workflowservice.testthreetask.TestThreeTaskWorkPrepare;
import com.pth.workflow.services.bl.impl.workflowservice.workflow.WorkflowPrepare;
import io.micronaut.context.annotation.Bean;
import io.micronaut.context.annotation.Factory;

import javax.inject.Named;
import javax.inject.Singleton;

@Factory
public class WorkflowPrepareFactory {

    @Bean
    @Singleton
    @Named("workflowPrepare")
    public IWorkflowPrepare generateWorkflowPrepare(IWorkflowTypeRepository workflowTypeRepository) {
        return new WorkflowPrepare(workflowTypeRepository);
    }

    @Bean
    @Singleton
    @Named("invoiceWorkflowPrepare")
    public IWorkflowPrepare generateInvoiceWorkflowPrepare(IWorkflowTypeRepository workflowTypeRepository) {
        return new InvoiceWorkflowPrepare(workflowTypeRepository);
    }

    @Bean
    @Singleton
    @Named("singleTaskWorkflowPrepare")
    public IWorkflowPrepare generateSingleTaskWorkflowPrepare(IWorkflowTypeRepository workflowTypeRepository) {
        return new SingleTaskWorkflowPrepare(workflowTypeRepository);
    }

    @Bean
    @Singleton
    @Named("testThreeTaskWorkPrepare")
    public IWorkflowPrepare generateTestThreeTaskWorkflowPrepare(IWorkflowTypeRepository workflowTypeRepository) {
        return new TestThreeTaskWorkPrepare(workflowTypeRepository);
    }
}
