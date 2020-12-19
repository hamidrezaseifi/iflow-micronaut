package com.pth.workflow.services.bl.strategy;

import com.pth.workflow.entities.InvoiceWorkflowEntity;
import com.pth.workflow.entities.SingleTaskWorkflowEntity;
import com.pth.workflow.entities.TestThreeTaskWorkflowEntity;
import com.pth.workflow.repositories.IInvoiceWorkflowRepository;
import com.pth.workflow.repositories.ISingleTaskWorkflowRepository;
import com.pth.workflow.repositories.ITestThreeTaskWorkflowRepository;
import com.pth.workflow.repositories.IWorkflowMessageRepository;
import com.pth.workflow.services.bl.IDepartmentDataService;
import com.pth.workflow.services.bl.IGuiCachDataDataService;
import com.pth.workflow.services.bl.IWorkflowPrepare;
import com.pth.workflow.services.bl.strategy.factory.InvoiceWorkflowSaveStrategyFactory;
import com.pth.workflow.services.bl.strategy.factory.SingleTaskWorkflowSaveStrategyFactory;
import com.pth.workflow.services.bl.strategy.factory.TestThreeTaskWorkflowSaveStrategyFactory;
import io.micronaut.context.annotation.Bean;
import io.micronaut.context.annotation.Factory;

import javax.inject.Named;
import javax.inject.Singleton;

@Factory
public class WorkflowSaveStrategyFactoryFactory {

    @Bean
    @Singleton
    @Named("invoiceWorkflowSaveStrategyFactory")
    public IWorkflowSaveStrategyFactory generateInvoiceWorkflowSaveStrategyFactory(IInvoiceWorkflowRepository invoiceWorkflowRepository,
                                                                                   IDepartmentDataService departmentDataService,
                                                                                   IWorkflowMessageRepository workflowMessageRepository,
                                                                                   IGuiCachDataDataService cachDataDataService,
                                                                                   @Named("invoiceWorkflowPrepare") IWorkflowPrepare<InvoiceWorkflowEntity> invoiceWorkflowPrepare) {
        return new InvoiceWorkflowSaveStrategyFactory(invoiceWorkflowRepository,
                                                      departmentDataService,
                                                      workflowMessageRepository,
                                                      cachDataDataService,
                                                      invoiceWorkflowPrepare);
    }

    @Bean
    @Singleton
    @Named("singleTaskWorkflowSaveStrategyFactory")
    public IWorkflowSaveStrategyFactory generateSingleTaskWorkflowSaveStrategyFactory(ISingleTaskWorkflowRepository singleTaskWorkflowRepository,
                                                                                      IDepartmentDataService departmentDataService,
                                                                                      IWorkflowMessageRepository workflowMessageRepository,
                                                                                      IGuiCachDataDataService cachDataDataService,
                                                                                      @Named("singleTaskWorkflowPrepare") IWorkflowPrepare<SingleTaskWorkflowEntity> singleTasWorkflowPrepare) {
        return new SingleTaskWorkflowSaveStrategyFactory(singleTaskWorkflowRepository,
                                                         departmentDataService,
                                                         workflowMessageRepository,
                                                         cachDataDataService,
                                                         singleTasWorkflowPrepare);
    }

    @Bean
    @Singleton
    @Named("testThreeTaskWorkflowSaveStrategyFactory")
    public IWorkflowSaveStrategyFactory generateTestThreeTaskWorkProcessService(ITestThreeTaskWorkflowRepository testThreeTaskWorkflowRepository,
                                                                                IDepartmentDataService departmentDataService,
                                                                                IWorkflowMessageRepository workflowMessageRepository,
                                                                                IGuiCachDataDataService cachDataDataService,
                                                                                @Named("testThreeTaskWorkPrepare") IWorkflowPrepare<TestThreeTaskWorkflowEntity> testThreeWorkflowPrepare) {
        return new TestThreeTaskWorkflowSaveStrategyFactory(testThreeTaskWorkflowRepository,
                                                            departmentDataService,
                                                            workflowMessageRepository,
                                                            cachDataDataService,
                                                            testThreeWorkflowPrepare);
    }

}
