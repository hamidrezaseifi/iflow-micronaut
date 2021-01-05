package com.pth.gui.controllers.workflow;


import com.pth.common.edo.enums.EInvoiceWorkflowTypeItems;
import com.pth.common.edo.enums.EWorkflowType;
import com.pth.gui.helpers.ocr.IOcrHelper;
import com.pth.gui.models.CompanyWorkflowtypeItemOcrSettingPreset;
import com.pth.gui.models.CompanyWorkflowtypeItemOcrSettingPresetItem;
import com.pth.gui.models.gui.GuiSocketMessage;
import com.pth.gui.models.gui.ocr.OcrResultValueType;
import com.pth.gui.models.gui.ocr.OcrResultWord;
import com.pth.gui.models.gui.ocr.OcrResults;
import com.pth.gui.models.gui.uisession.SessionData;
import com.pth.gui.models.workflow.WorkflowType;
import com.pth.gui.models.workflow.invoice.InvoiceWorkflow;
import com.pth.gui.models.workflow.invoice.InvoiceWorkflowSaveRequest;
import com.pth.gui.services.ICompanyHandler;
import com.pth.gui.services.IUploadFileManager;
import com.pth.gui.services.impl.workflow.IInvoiceBasicWorkflowHandler;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import io.micronaut.session.Session;
import net.sourceforge.tess4j.TesseractException;
import org.apache.commons.lang3.StringUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

@Secured(SecurityRule.IS_AUTHENTICATED)
@Controller("/workflow/invoice/data")
public class InvoiceWorkflowDataController extends WorkflowDataControllerBase {


  protected final IInvoiceBasicWorkflowHandler workflowHandler;

  protected final IUploadFileManager uploadFileManager;

  protected final ICompanyHandler companyHandler;

  protected final IOcrHelper ocrHelper;

  public InvoiceWorkflowDataController(IInvoiceBasicWorkflowHandler workflowHandler,
                                       IUploadFileManager uploadFileManager,
                                       ICompanyHandler companyHandler,
                                       IOcrHelper ocrHelper) {

    this.workflowHandler = workflowHandler;
    this.uploadFileManager = uploadFileManager;
    this.companyHandler = companyHandler;
    this.ocrHelper = ocrHelper;
  }

  @Post("/initcreate" )
  public HttpResponse<Map<String, Object>> loadWorkflowCreateData(Session session) {

    final Map<String, Object> map = new HashMap<>();

    final InvoiceWorkflow newWorkflow = this.generateInitialWorkflow(this.getCurrentUserId(session), session);

    this.prepareWorkflow(newWorkflow, session);

    final InvoiceWorkflowSaveRequest workflowReq =
            this.generateInitialWorkflowSaveRequest(newWorkflow,
                                                    newWorkflow.getWorkflow().getHasActiveAction() ?
                                                    newWorkflow.getWorkflow().getActiveAction().getCurrentStep().getExpireDays() :
                                                    15);

    map.put("workflowSaveRequest", workflowReq);
    map.put("ocrPresetList",
            this.companyHandler.readCompanyWorkflowtypeItemOcrSettings(this.getCompanyId(session),
                                                                       this.getLoggedToken(session)));

    return HttpResponse.ok(map);
  }

  @Post( "/initedit/{workflowId}")
  public HttpResponse<Map<String, Object>> loadWorkflowEditData(final UUID workflowId, Session session)
  {

    final Map<String, Object> map = new HashMap<>();

    final Optional<InvoiceWorkflow> workflowOptional = this.workflowHandler.readWorkflow(workflowId, getSessionData(session));

    if(workflowOptional.isPresent()){
      InvoiceWorkflow workflow = workflowOptional.get();
      final Integer expireDays = workflow.getWorkflow().getHasActiveAction() ?
                                 workflow.getWorkflow().getActiveAction().getCurrentStep().getExpireDays() : 0;

      final InvoiceWorkflowSaveRequest saveRequest = this.generateInitialWorkflowSaveRequest(workflow, expireDays);

      this.prepareWorkflow(workflow, session);

      map.put("workflowSaveRequest", saveRequest);
      map
              .put("ocrPresetList",
                   this.companyHandler.readCompanyWorkflowtypeItemOcrSettings(this.getCompanyId(session), this.getLoggedToken(session)));

      return HttpResponse.ok(map);
    }
    return HttpResponse.notFound();
  }

  @Post( "/create")
  public HttpResponse<List<InvoiceWorkflow>> createWorkflow(@Body final InvoiceWorkflowSaveRequest createRequest, Session session) {

    this.prepareWorkflow(createRequest.getWorkflow(), session);

    List<InvoiceWorkflow> createdWorkflowList = this.workflowHandler.createWorkflow(createRequest, getSessionData(session));
    return HttpResponse.created(createdWorkflowList);

  }

  @Post( "/save" )
  public HttpResponse<?> saveWorkflow(@Body final InvoiceWorkflowSaveRequest saveRequest, Session session) {

    this.prepareWorkflow(saveRequest.getWorkflow(), session);

    this.workflowHandler.saveWorkflow(saveRequest, getSessionData(session));

    return HttpResponse.ok();
  }

  @Post( "/archive")
  public HttpResponse<?> archiveWorkflow(@Body final InvoiceWorkflow workflow, Session session) {

    this.prepareWorkflow(workflow, session);

    this.workflowHandler.archiveWorkflow(workflow, getSessionData(session));
    return HttpResponse.ok();
  }

  @Post( "/done")
  public HttpResponse<?> makeDoneWorkflow(@Body final InvoiceWorkflowSaveRequest saveRequest, final Session session) {

    this.prepareWorkflow(saveRequest.getWorkflow(), session);

    this.workflowHandler.doneWorkflow(saveRequest, getSessionData(session));
    return HttpResponse.ok();
  }

  @Post( "/assign/{workflowId}")
  public HttpResponse<InvoiceWorkflow> assignWorkflow(final UUID workflowId, Session session) {

    final Optional<InvoiceWorkflow> workflowOptional = this.workflowHandler.assignWorkflow(workflowId, getSessionData(session));

    if(workflowOptional.isPresent()){
      InvoiceWorkflow workflow = workflowOptional.get();

      this.prepareWorkflow(workflow, session);

      return HttpResponse.ok(workflow);
    }

    return HttpResponse.badRequest();
  }

  @Override
  protected ICompanyHandler getCompanyHandler() {
    return this.companyHandler;
  }

  @Override
  protected IOcrHelper getOcrHelper() {
    return this.ocrHelper;
  }

  @Override
  protected Map<String, Set<OcrResultWord>> retrieveInvoiceDetailWords(final OcrResults ocrResults,
                                                                       final String selectedPreset,
                                                                       Session session) {

    SessionData sessionData = this.getSessionData(session);
    Optional<CompanyWorkflowtypeItemOcrSettingPreset> presetOptional = sessionData.findOcrPresetByName(selectedPreset);

    if(presetOptional.isPresent() == false){

      return null;

    }

    WorkflowType workflowType = sessionData.getWorkflowTypeById(presetOptional.get().getWorkflowTypeId());

    final Map<String, CompanyWorkflowtypeItemOcrSettingPresetItem> presetItems =
            this.getCompanyHandler().readPresetAllItems(selectedPreset,
                                                        this.getCompanyId(session),
                                                        workflowType.getTypeEnum(),
                                                        this.getLoggedToken(session));

    final Map<String, Set<OcrResultWord>> words = new HashMap<>();

    Set<OcrResultWord> results = this
            .extractSearchItems(ocrResults, presetItems, EInvoiceWorkflowTypeItems.PAYMENT_AMOUNT.getIdentity(), OcrResultValueType.FLOAT);
    if (results != null) {
      words.put(EInvoiceWorkflowTypeItems.PAYMENT_AMOUNT.getIdentity(), results);
    }

    results = this
            .extractSearchItems(ocrResults, presetItems, EInvoiceWorkflowTypeItems.INVOCIE_SENDER.getIdentity(), OcrResultValueType.TEXT);
    if (results != null) {
      words.put(EInvoiceWorkflowTypeItems.INVOCIE_SENDER.getIdentity(), results);
    }

    results = this
            .extractSearchItems(ocrResults, presetItems, EInvoiceWorkflowTypeItems.INVOCIE_NUMBER.getIdentity(), OcrResultValueType.TEXT);
    if (results != null) {
      words.put(EInvoiceWorkflowTypeItems.INVOCIE_NUMBER.getIdentity(), results);
    }

    results = this
            .extractSearchItems(ocrResults, presetItems, EInvoiceWorkflowTypeItems.INVOCIE_DATE.getIdentity(), OcrResultValueType.DATE);
    if (results != null) {
      words.put(EInvoiceWorkflowTypeItems.INVOCIE_DATE.getIdentity(), results);
    }

    results = this
            .extractSearchItems(ocrResults, presetItems, EInvoiceWorkflowTypeItems.PARTNER_CODE.getIdentity(), OcrResultValueType.TEXT);
    if (results != null) {
      words.put(EInvoiceWorkflowTypeItems.PARTNER_CODE.getIdentity(), results);
    }

    results = this
            .extractSearchItems(ocrResults, presetItems, EInvoiceWorkflowTypeItems.VENDOR_NUMBER.getIdentity(), OcrResultValueType.TEXT);
    if (results != null) {
      words.put(EInvoiceWorkflowTypeItems.VENDOR_NUMBER.getIdentity(), results);
    }

    results = this
            .extractSearchItems(ocrResults, presetItems, EInvoiceWorkflowTypeItems.VENDOR_NAME.getIdentity(), OcrResultValueType.TEXT);
    if (results != null) {
      words.put(EInvoiceWorkflowTypeItems.VENDOR_NAME.getIdentity(), results);
    }

    results = this
            .extractSearchItems(ocrResults, presetItems, EInvoiceWorkflowTypeItems.IS_DIRECT_DEBIT_PERMISSION.getIdentity(),
                                OcrResultValueType.TEXT);
    if (results != null) {
      words.put(EInvoiceWorkflowTypeItems.IS_DIRECT_DEBIT_PERMISSION.getIdentity(), results);
    }

    results = this
            .extractSearchItems(ocrResults, presetItems, EInvoiceWorkflowTypeItems.INVOICE_TYPE.getIdentity(), OcrResultValueType.TEXT);
    if (results != null) {
      words.put(EInvoiceWorkflowTypeItems.INVOICE_TYPE.getIdentity(), results);
    }

    results = this
            .extractSearchItems(ocrResults, presetItems, EInvoiceWorkflowTypeItems.DISCOUNT_ENTERDATE.getIdentity(), OcrResultValueType.DATE);
    if (results != null) {
      words.put(EInvoiceWorkflowTypeItems.DISCOUNT_ENTERDATE.getIdentity(), results);
    }

    results = this
            .extractSearchItems(ocrResults, presetItems, EInvoiceWorkflowTypeItems.DISCOUNT_DEADLINE.getIdentity(), OcrResultValueType.TEXT);
    if (results != null) {
      words.put(EInvoiceWorkflowTypeItems.DISCOUNT_DEADLINE.getIdentity(), results);
    }

    results = this
            .extractSearchItems(ocrResults, presetItems, EInvoiceWorkflowTypeItems.DISCOUNT_RATE.getIdentity(), OcrResultValueType.FLOAT);
    if (results != null) {
      words.put(EInvoiceWorkflowTypeItems.DISCOUNT_RATE.getIdentity(), results);
    }

    results = this
            .extractSearchItems(ocrResults, presetItems, EInvoiceWorkflowTypeItems.DISCOUNT_DATE.getIdentity(), OcrResultValueType.DATE);
    if (results != null) {
      words.put(EInvoiceWorkflowTypeItems.DISCOUNT_DATE.getIdentity(), results);
    }

    final Set<OcrResultWord> amountWorldList = ocrResults.findWord("betrag", false, false, OcrResultValueType.FLOAT);
    final Set<OcrResultWord> senderWorldList = ocrResults.findWord("sender", false, false, OcrResultValueType.TEXT);
    final Set<OcrResultWord> numberWorldList = ocrResults
            .findWords(new String[] { "R.-Nr.", "Rg-Nr", "R. Nummer", "Rg-Nummer", "Rechnungsnummer", "nr." }, false,
                       false,
                       OcrResultValueType.TEXT);
    final Set<OcrResultWord> dateWorldList = ocrResults.findWord("Datum", false, false, OcrResultValueType.DATE);
    final Set<OcrResultWord> testList = ocrResults.findWords(new String[] { "ident", "heinstadt" }, false, false, OcrResultValueType.TEXT);

    /*
     * words.put("invoice-paymentamount", amountWorldList); words.put("invoice-sender", senderWorldList); words.put("invoice-invoicenumber",
     * numberWorldList); words.put("invoice-invoicedate", dateWorldList); words.put("test", testList);
     */
    return words;
  }

  protected InvoiceWorkflow generateInitialWorkflow(final UUID userId, Session session) {

    final InvoiceWorkflow workflow =
            InvoiceWorkflow.generateInitial(userId,
                                            this.getCurrentUserId(session),
                                            this.getWorkflowTypeByEnumType(EWorkflowType.INVOICE_WORKFLOW_TYPE,
                                                                           session));
    workflow.setIsDirectDebitPermission(false);
    return workflow;
  }


  protected InvoiceWorkflowSaveRequest generateInitialWorkflowSaveRequest(final InvoiceWorkflow workflow, final int expireDays) {

    return InvoiceWorkflowSaveRequest.generateNewWihExpireDays(workflow, expireDays);
  }

}
