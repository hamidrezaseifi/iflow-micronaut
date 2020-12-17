package com.pth.workflow.services.bl.impl;

import com.pth.workflow.entities.InvoiceWorkflowEntity;
import com.pth.workflow.entities.SingleTaskWorkflowEntity;
import com.pth.workflow.entities.TestThreeTaskWorkflowEntity;
import com.pth.workflow.entities.WorkflowEntity;
import com.pth.workflow.repositories.IInvoiceWorkflowRepository;
import com.pth.workflow.repositories.ISingleTaskWorkflowRepository;
import com.pth.workflow.repositories.ITestThreeTaskWorkflowRepository;
import com.pth.workflow.repositories.IWorkflowRepository;
import com.pth.workflow.services.bl.IWorkflowPrepare;
import com.pth.workflow.services.bl.IWorkflowProcessService;
import com.pth.workflow.services.bl.impl.workflowservice.invoice.InvoiceWorkflowProcessService;
import com.pth.workflow.services.bl.impl.workflowservice.singletask.SingleTaskWorkflowProcessService;
import com.pth.workflow.services.bl.impl.workflowservice.testthreetask.TestThreeTaskWorkProcessService;
import com.pth.workflow.services.bl.impl.workflowservice.workflow.WorkflowProcessService;
import com.pth.workflow.services.bl.strategy.IWorkflowSaveStrategyFactory;
import io.micronaut.context.annotation.Bean;
import io.micronaut.context.annotation.Factory;

import javax.inject.Named;
import javax.inject.Singleton;

@Factory
public class WorkflowProcessServiceFactory {


    @Bean
    @Singleton
    @Named("workflowProcessService")
    public IWorkflowProcessService generateWorkflowProcessService(@Named("workflowRepository") IWorkflowRepository workflowRepository,
                                                                  @Named("workflowPrepare") IWorkflowPrepare<WorkflowEntity> workflowPrepare) {
        return new WorkflowProcessService(workflowRepository, workflowPrepare);
    }

    @Bean
    @Singleton
    @Named("invoiceWorkflowProcessService")
    public IWorkflowProcessService generateInvoiceWorkflowProcessService(IInvoiceWorkflowRepository invoiceWorkflowRepository,
                                                                         IWorkflowSaveStrategyFactory<InvoiceWorkflowEntity> workStrategyFactory,
                                                                         @Named("invoiceWorkflowPrepare") IWorkflowPrepare<InvoiceWorkflowEntity> workflowPrepare) {
        return new InvoiceWorkflowProcessService(invoiceWorkflowRepository, workStrategyFactory, workflowPrepare);
    }

    @Bean
    @Singleton
    @Named("singleTaskWorkflowProcessService")
    public IWorkflowProcessService generateSingleTaskWorkflowProcessService(ISingleTaskWorkflowRepository singleTaskWorkflowRepository,
                                                                            IWorkflowSaveStrategyFactory<SingleTaskWorkflowEntity> workStrategyFactory,
                                                                            @Named("singleTaskWorkflowPrepare") IWorkflowPrepare<SingleTaskWorkflowEntity> workflowPrepare) {
        return new SingleTaskWorkflowProcessService(singleTaskWorkflowRepository, workStrategyFactory, workflowPrepare);
    }

    @Bean
    @Singleton
    @Named("testThreeTaskWorkProcessService")
    public IWorkflowProcessService generateTestThreeTaskWorkProcessService(ITestThreeTaskWorkflowRepository testThreeTaskWorkflowRepository,
                                                                           IWorkflowSaveStrategyFactory<TestThreeTaskWorkflowEntity> workStrategyFactory,
                                                                           @Named("testThreeTaskWorkPrepare") IWorkflowPrepare<TestThreeTaskWorkflowEntity> workflowPrepare) {
        return new TestThreeTaskWorkProcessService(testThreeTaskWorkflowRepository, workStrategyFactory, workflowPrepare);
    }

}
