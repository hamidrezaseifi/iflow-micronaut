package com.pth.gui.controllers.workflow;



import com.pth.common.edo.enums.EInvoiceWorkflowTypeItems;
import com.pth.common.edo.enums.EOcrType;
import com.pth.common.edo.enums.EWorkflowType;
import com.pth.gui.controllers.helper.AuthenticatedController;
import com.pth.gui.exception.GuiCustomizedException;
import com.pth.gui.helpers.ocr.IOcrHelper;
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
import com.pth.gui.models.workflow.base.WorkflowBased;
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
public abstract class WorkflowDataControllerBase
        extends AuthenticatedController {

  protected abstract ICompanyHandler getCompanyHandler();
  protected abstract IOcrHelper getOcrHelper();

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



    final OcrResults ocrResults = getOcrHelper().generateResultFromFile(hocrPath);

    final Map<String, Set<OcrResultWord>> words = this.retrieveInvoiceDetailWords(ocrResults, selectedPreset, session);
    if(words == null){
      return  HttpResponse.notFound();
    }

    result.setWords(words);
    result.setPageCount(ocrResults.getPages().size());

    result.setImageWidth(300);
    result.setImageHeight(500);
    if (result.getIsFileImage()) {
      final File file = new File(filePath);
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

  protected abstract Map<String, Set<OcrResultWord>> retrieveInvoiceDetailWords(final OcrResults ocrResults,
                                                                       final String selectedPreset,
                                                                       Session session);


  protected Set<OcrResultWord> extractSearchItems(final OcrResults ocrResults,
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

  private void setWorkflowController(final WorkflowBased workflow, Session session) {

    SessionData sessionData = this.getSessionData(session);
    sessionData.getCompany().getWorkflowTypeControllers();

    List<CompanyWorkflowTypeController> controllerList =
            sessionData.getControllerForWorkflowType(workflow.getWorkflow().getWorkflowTypeId());


    if(controllerList == null ||controllerList.isEmpty()){

      throw new GuiCustomizedException("Invalid-Company-Setting:Workflow-Controller-Not-Found!");
    }

    workflow.getWorkflow().setControllerId(controllerList.get(0).getUserId());
  }

  private void setWorkflowCompanyId(final WorkflowBased workflow, Session session) {

    workflow.getWorkflow().setCompanyId(this.getCompanyId(session));
  }


  protected void prepareWorkflow(final WorkflowBased workflow, Session session) {

    setWorkflowController(workflow, session);
    setWorkflowCompanyId(workflow, session);
  }


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
