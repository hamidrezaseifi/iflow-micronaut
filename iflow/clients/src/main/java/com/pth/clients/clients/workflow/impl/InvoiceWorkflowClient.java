package com.pth.clients.clients.workflow.impl;

import com.pth.clients.clients.ClientBase;
import com.pth.clients.clients.workflow.IInvoiceWorkflowClient;
import com.pth.clients.declaratives.workflow.IInvoiceWorkflowV001DeclarativeClient;
import com.pth.common.edo.workflow.invoice.InvoiceWorkflowEdo;
import com.pth.common.edo.workflow.invoice.InvoiceWorkflowListEdo;
import com.pth.common.edo.workflow.invoice.InvoiceWorkflowSaveRequestEdo;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;

import javax.inject.Singleton;
import java.util.Optional;
import java.util.UUID;

@Singleton
public class InvoiceWorkflowClient extends ClientBase implements IInvoiceWorkflowClient {

    private final IInvoiceWorkflowV001DeclarativeClient invoiceWorkflowDeclarativeClient;


    public InvoiceWorkflowClient(IInvoiceWorkflowV001DeclarativeClient invoiceWorkflowDeclarativeClient) {
        this.invoiceWorkflowDeclarativeClient = invoiceWorkflowDeclarativeClient;
    }


    @Override
    public Optional<InvoiceWorkflowEdo> read(String authorization,
                                             UUID id) {
        HttpResponse<InvoiceWorkflowEdo> response =
                this.invoiceWorkflowDeclarativeClient.readInvoice(prepareBearerAuthorization(authorization),
                                                                  id);
        if(response.getStatus() == HttpStatus.OK){
            return response.getBody();
        }

        return Optional.empty();
    }

    @Override
    public Optional<InvoiceWorkflowListEdo> create(String authorization,
                                                   InvoiceWorkflowSaveRequestEdo workflowCreateRequestEdo) {
        HttpResponse<InvoiceWorkflowListEdo> response =
                this.invoiceWorkflowDeclarativeClient.createInvoice(prepareBearerAuthorization(authorization),
                                                                    workflowCreateRequestEdo);
        if(response.getStatus() == HttpStatus.OK){
            return response.getBody();
        }

        return Optional.empty();
    }

    @Override
    public Optional<InvoiceWorkflowEdo> save(String authorization,
                                             InvoiceWorkflowSaveRequestEdo requestEdo) {
        HttpResponse<InvoiceWorkflowEdo> response =
                this.invoiceWorkflowDeclarativeClient.saveInvoice(prepareBearerAuthorization(authorization),
                                                                  requestEdo);
        if(response.getStatus() == HttpStatus.OK){
            return response.getBody();
        }

        return Optional.empty();
    }

    @Override
    public Optional<InvoiceWorkflowListEdo> readListForUser(String authorization,
                                                            UUID id,
                                                            int status) {
        HttpResponse<InvoiceWorkflowListEdo> response =
                this.invoiceWorkflowDeclarativeClient.readInvoiceListForUser(prepareBearerAuthorization(authorization),
                                                                             id,
                                                                             status);
        if(response.getStatus() == HttpStatus.OK){
            return response.getBody();
        }

        return Optional.empty();
    }

    @Override
    public void validate(String authorization,
                                InvoiceWorkflowSaveRequestEdo workflowCreateRequestEdo) {
        HttpResponse<?> response =
                this.invoiceWorkflowDeclarativeClient.validateInvoice(prepareBearerAuthorization(authorization),
                                                                      workflowCreateRequestEdo);
        if(response.getStatus() == HttpStatus.OK){

        }

    }
}
