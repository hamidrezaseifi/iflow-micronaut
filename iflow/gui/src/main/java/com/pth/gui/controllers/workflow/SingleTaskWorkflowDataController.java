package com.pth.gui.controllers.workflow;


import com.pth.common.edo.enums.EWorkflowType;
import com.pth.gui.helpers.ocr.IOcrHelper;
import com.pth.gui.models.gui.GuiSocketMessage;
import com.pth.gui.models.gui.ocr.OcrResultWord;
import com.pth.gui.models.gui.ocr.OcrResults;
import com.pth.gui.models.workflow.singletask.SingleTaskWorkflow;
import com.pth.gui.models.workflow.singletask.SingleTaskWorkflowSaveRequest;
import com.pth.gui.services.ICompanyHandler;
import com.pth.gui.services.IUploadFileManager;
import com.pth.gui.services.impl.workflow.ISingleTaskBasicWorkflowHandler;
import com.pth.gui.services.impl.workflow.singletask.SingleTaskBasicWorkflowHandler;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import io.micronaut.session.Session;
import org.apache.commons.lang3.StringUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;


@Secured(SecurityRule.IS_AUTHENTICATED)
@Controller("/workflow/singletask/data")
public class SingleTaskWorkflowDataController extends WorkflowDataControllerBase {

  protected final ISingleTaskBasicWorkflowHandler workflowHandler;

  protected final IUploadFileManager uploadFileManager;

  protected final ICompanyHandler companyHandler;

  protected final IOcrHelper ocrHelper;

  public SingleTaskWorkflowDataController(SingleTaskBasicWorkflowHandler workflowHandler,
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

    final SingleTaskWorkflow newWorkflow = this.generateInitialWorkflow(this.getCurrentUserId(session), session);

    this.prepareWorkflow(newWorkflow, session);

    final SingleTaskWorkflowSaveRequest workflowReq =
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

    final Optional<SingleTaskWorkflow> workflowOptional = this.workflowHandler.readWorkflow(workflowId, getSessionData(session));

    if(workflowOptional.isPresent()){
      SingleTaskWorkflow workflow = workflowOptional.get();
      final Integer expireDays = workflow.getWorkflow().getHasActiveAction() ?
                                 workflow.getWorkflow().getActiveAction().getCurrentStep().getExpireDays() :
                                 0;

      final SingleTaskWorkflowSaveRequest saveRequest = this.generateInitialWorkflowSaveRequest(workflow, expireDays);

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
  public HttpResponse<List<SingleTaskWorkflow>> createWorkflow(@Body final SingleTaskWorkflowSaveRequest createRequest, Session session) {

    this.prepareWorkflow(createRequest.getWorkflow(), session);

    List<SingleTaskWorkflow> createdWorkflowList = this.workflowHandler.createWorkflow(createRequest, getSessionData(session));
    return HttpResponse.created(createdWorkflowList);

  }

  @Post( "/save" )
  public HttpResponse<?> saveWorkflow(@Body final SingleTaskWorkflowSaveRequest saveRequest, Session session) {

    this.prepareWorkflow(saveRequest.getWorkflow(), session);

    this.workflowHandler.saveWorkflow(saveRequest, getSessionData(session));

    return HttpResponse.ok();
  }

  @Post( "/archive")
  public HttpResponse<?> archiveWorkflow(@Body final SingleTaskWorkflow workflow, Session session) {

    this.prepareWorkflow(workflow, session);

    this.workflowHandler.archiveWorkflow(workflow, getSessionData(session));
    return HttpResponse.ok();
  }

  @Post( "/done")
  public HttpResponse<?> makeDoneWorkflow(@Body final SingleTaskWorkflowSaveRequest saveRequest, final Session session) {

    this.prepareWorkflow(saveRequest.getWorkflow(), session);

    this.workflowHandler.doneWorkflow(saveRequest, getSessionData(session));
    return HttpResponse.ok();
  }

  @Post( "/assign/{workflowId}")
  public HttpResponse<SingleTaskWorkflow> assignWorkflow(final UUID workflowId, Session session) {

    final Optional<SingleTaskWorkflow> workflowOptional = this.workflowHandler.assignWorkflow(workflowId, getSessionData(session));

    if(workflowOptional.isPresent()){
      SingleTaskWorkflow workflow = workflowOptional.get();

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
    return new HashMap<>();
  }

  protected SingleTaskWorkflow generateInitialWorkflow(final UUID userId, Session session) {

    final SingleTaskWorkflow workflow =
            SingleTaskWorkflow.generateInitial(userId,
                                               this.getCurrentUserId(session),
                                               this.getWorkflowTypeByEnumType(EWorkflowType.SINGLE_TASK_WORKFLOW_TYPE,
                                                                           session));

    return workflow;

  }

  protected SingleTaskWorkflowSaveRequest generateInitialWorkflowSaveRequest(final SingleTaskWorkflow workflow, final int expireDays) {
    return SingleTaskWorkflowSaveRequest.generateNewWihExpireDays(workflow, expireDays);
  }

}
