package com.pth.gui.controllers.workflow;



import com.pth.common.edo.enums.EInvoiceWorkflowTypeItems;
import com.pth.common.edo.enums.EOcrType;
import com.pth.common.edo.enums.EWorkflowType;
import com.pth.gui.controllers.helper.AuthenticatedController;
import com.pth.gui.exception.GuiCustomizedException;
import com.pth.gui.models.CompanyWorkflowTypeController;
import com.pth.gui.models.CompanyWorkflowtypeItemOcrSettingPreset;
import com.pth.gui.models.CompanyWorkflowtypeItemOcrSettingPresetItem;
import com.pth.gui.models.gui.GuiSocketMessage;
import com.pth.gui.models.gui.ocr.OcrResultValueType;
import com.pth.gui.models.gui.ocr.OcrResultWord;
import com.pth.gui.models.gui.ocr.OcrResults;
import com.pth.gui.models.gui.uisession.SessionData;
import com.pth.gui.models.workflow.IWorkflow;
import com.pth.gui.models.workflow.IWorkflowSaveRequest;
import com.pth.gui.models.workflow.WorkflowType;
import com.pth.gui.services.IBasicWorkflowHandler;
import com.pth.gui.services.ICompanyHandler;
import com.pth.gui.services.IUploadFileManager;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.session.Session;
import org.apache.commons.lang3.StringUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

//@Controller
public abstract class WorkflowDataControllerBase<W extends IWorkflow, WS extends IWorkflowSaveRequest<W>> 
        extends AuthenticatedController {

  protected final IBasicWorkflowHandler<W, WS> workflowHandler;

  protected final IUploadFileManager uploadFileManager;

  protected final ICompanyHandler companyHandler;

  public WorkflowDataControllerBase(IBasicWorkflowHandler<W, WS> workflowHandler,
                                    IUploadFileManager uploadFileManager,
                                    ICompanyHandler companyHandler) {
    this.workflowHandler = workflowHandler;
    this.uploadFileManager = uploadFileManager;
    this.companyHandler = companyHandler;
  }

  @Post("/initcreate" )
  public HttpResponse<Map<String, Object>> loadWorkflowCreateData(Session session) {

    final Map<String, Object> map = new HashMap<>();

    final W newWorkflow = this.generateInitialWorkflow(this.getCurrentUserId(session), session);

    this.setWorkflowController(newWorkflow, session);

    final WS workflowReq =
            this.generateInitialWorkflowSaveRequest(newWorkflow,
                                                    newWorkflow.getHasActiveAction() ?
                                                    newWorkflow.getActiveAction().getCurrentStep().getExpireDays() :
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

    final Optional<W> workflowOptional = this.workflowHandler.readWorkflow(workflowId, getSessionData(session));

    if(workflowOptional.isPresent()){
      W workflow = workflowOptional.get();
      final Integer expireDays = workflow.getHasActiveAction() ? workflow.getActiveAction().getCurrentStep().getExpireDays() : 0;

      final WS saveRequest = this.generateInitialWorkflowSaveRequest(workflow, expireDays);

      this.setWorkflowController(workflow, session);

      map.put("workflowSaveRequest", saveRequest);
      map
              .put("ocrPresetList",
                   this.companyHandler.readCompanyWorkflowtypeItemOcrSettings(this.getCompanyId(session), this.getLoggedToken(session)));

      return HttpResponse.ok(map);
    }
   return HttpResponse.notFound();
  }

  @Post( "/create")
  public HttpResponse<List<W>> createWorkflow(@Body final WS createRequest, Session session) {

    createRequest.getWorkflow().setCompanyId(this.getCompanyId(session));

    this.setWorkflowController(createRequest.getWorkflow(), session);

    List<W> createdWorkflowList = this.workflowHandler.createWorkflow(createRequest, getSessionData(session));
    return HttpResponse.created(createdWorkflowList);

  }

  @Post( "/save" )
  public HttpResponse<?> saveWorkflow(@Body final WS saveRequest, Session session) {

    saveRequest.getWorkflow().setCompanyId(this.getCompanyId(session));

    this.setWorkflowController(saveRequest.getWorkflow(), session);

    this.workflowHandler.saveWorkflow(saveRequest, getSessionData(session));

    return HttpResponse.ok();
  }

  @Post( "/archive")
  public HttpResponse<?> archiveWorkflow(@Body final W workflow, Session session) {

    this.setWorkflowController(workflow, session);

    this.workflowHandler.archiveWorkflow(workflow, getSessionData(session));
    return HttpResponse.ok();
  }

  @Post( "/done")
  public HttpResponse<?> makeDoneWorkflow(@Body final WS saveRequest, final Session session) {

    saveRequest.getWorkflow().setCompanyId(this.getCompanyId(session));

    this.setWorkflowController(saveRequest.getWorkflow(), session);

    this.workflowHandler.doneWorkflow(saveRequest, getSessionData(session));
    return HttpResponse.ok();
  }

  @Post( "/assign/{workflowId}")
  public HttpResponse<W> assignWorkflow(final UUID workflowId, Session session) {

    final Optional<W> workflowOptional = this.workflowHandler.assignWorkflow(workflowId, getSessionData(session));

    if(workflowOptional.isPresent()){
      W workflow = workflowOptional.get();

      this.setWorkflowController(workflow, session);

      return HttpResponse.ok(workflow);
    }

    return HttpResponse.badRequest();
  }

  @Post( "/processdoc")
  public HttpResponse<GuiSocketMessage> processDocument(@Body final GuiSocketMessage message, Session session)
          throws IOException {

    final GuiSocketMessage result = message.clone();

    final String selectedPreset = message.getSelectedOcrPreset();
    if (StringUtils.isEmpty(selectedPreset)) {
      result.setStatus("error");
      result.setErrorMessage("Invalid Preset!");

      return HttpResponse.ok(result);
    }

    final String filePath = message.getFileNotHash();
    final String hocrPath = message.getHocrFileNotHash();

    final File file = new File(filePath);

    final OcrResults ocrResults = OcrResults.loadFromHocrFile(hocrPath);

    final Map<String, Set<OcrResultWord>> words = this.retrieveInvoiceDetailWords(ocrResults, selectedPreset, session);
    if(words == null){
      return  HttpResponse.notFound();
    }

    result.setWords(words);
    result.setPageCount(ocrResults.getPages().size());

    result.setImageWidth(300);
    result.setImageHeight(500);
    if (result.getIsFileImage()) {
      final BufferedImage bimg = ImageIO.read(file);
      result.setImageWidth(bimg.getWidth());
      result.setImageHeight(bimg.getHeight());
    }

    if (result.getIsFilePdf() && ocrResults.getPages().size() > 0) {
      result.setImageWidth(ocrResults.getPages().get(0).getBox().getWidth());
      result.setImageHeight(ocrResults.getPages().get(0).getBox().getHeight());
    }

    result.setStatus("done");

    return HttpResponse.ok(result);
  }

  private Map<String, Set<OcrResultWord>> retrieveInvoiceDetailWords(final OcrResults ocrResults,
                                                                     final String selectedPreset,
                                                                     Session session) {

    SessionData sessionData = this.getSessionData(session);
    Optional<CompanyWorkflowtypeItemOcrSettingPreset> presetOptional = sessionData.findOcrPresetByName(selectedPreset);

    if(presetOptional.isPresent() == false){

      return null;

    }

    WorkflowType workflowType = sessionData.getWorkflowTypeById(presetOptional.get().getWorkflowTypeId());

    final Map<String, CompanyWorkflowtypeItemOcrSettingPresetItem> presetItems =
            this.companyHandler.readPresetAllItems(selectedPreset,
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

  private Set<OcrResultWord> extractSearchItems(final OcrResults ocrResults,
      final Map<String, CompanyWorkflowtypeItemOcrSettingPresetItem> presetItems,
      final String propertyName, final OcrResultValueType valueType) {

    Set<OcrResultWord> results = null;
    if (presetItems.containsKey(propertyName)) {
      final CompanyWorkflowtypeItemOcrSettingPresetItem property = presetItems.get(propertyName);

      if (property.getOcrTypeEnum() == EOcrType.SEARCH_WORD) {

        final String[] searchWords = property.getValue().trim().split(";");
        results = ocrResults.findWords(searchWords, false, false, valueType);
        results = results.isEmpty() ? null : results;

      }

    }

    return results;
  }

  protected void setWorkflowController(final W newWorkflow, Session session) {

    SessionData sessionData = this.getSessionData(session);
    sessionData.getCompany().getWorkflowTypeControllers();

    List<CompanyWorkflowTypeController> controllerList = sessionData.getControllerForWorkflowType(newWorkflow.getWorkflowTypeId());


    if(controllerList == null ||controllerList.isEmpty()){

      //throw new GuiCustomizedException("Invalid-Company-Setting:Workflow-Controller-Not-Found!");
      return;
    }

    newWorkflow.setControllerId(controllerList.get(0).getUserId());
  }

  protected abstract W generateInitialWorkflow(UUID userId, Session session);

  protected abstract WS generateInitialWorkflowSaveRequest(W workflow, int expireDays);

  protected UUID getCurrentUserId(Session session){
    return this.getSessionData(session).getCurrentUserId();
  }

  protected UUID getCompanyId(Session session){
    return this.getSessionData(session).getCompanyId();
  }

  protected String getLoggedToken(Session session){
    return this.getSessionData(session).getRefreshToken();
  }

  protected WorkflowType getWorkflowTypeByEnumType(final EWorkflowType type, Session session) {

    return this.getSessionData(session).getWorkflowTypeByEnumType(type);

  }
}
