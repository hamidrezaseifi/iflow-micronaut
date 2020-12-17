package com.pth.gui.controllers;


import com.pth.gui.controllers.helper.AuthenticatedController;
import com.pth.gui.models.CompanyWorkflowtypeItemOcrSettingPreset;
import com.pth.gui.models.CompanyWorkflowtypeItemOcrSettingPresetItem;
import com.pth.gui.models.gui.uisession.SessionData;
import com.pth.gui.models.workflow.WorkflowType;
import com.pth.gui.services.ICompanyHandler;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import io.micronaut.session.Session;

import java.util.*;

@Secured(SecurityRule.IS_AUTHENTICATED)
@Controller("/ocrpreset/data")

public class OcrPresetDataController extends AuthenticatedController {

  
  private final ICompanyHandler companyHandler;

  public OcrPresetDataController(ICompanyHandler companyHandler) {
    this.companyHandler = companyHandler;
  }

  @Get("/initpage")
  public HttpResponse<Map<String, Object>> readCompanyInfo(Session session) {

    SessionData sessionData = getSessionData(session);
    final Map<String, Object> map = new HashMap<>();

    final Collection<WorkflowType> workflowTypes = sessionData.getWorkflow().getWorkflowTypes();
    map.put("worlflowTypes", workflowTypes);
    final Map<UUID, List<String>> workflowTypeItems = new HashMap<>();
    for (final WorkflowType type : workflowTypes) {
      workflowTypeItems.put(type.getId(), this.companyHandler.readWorkflowtypeItems(type.getTypeEnum()));
    }
    map.put("worlflowTypeItems", workflowTypeItems);

    return HttpResponse.ok(map);
  }

  
  @Get("/list" )
  public HttpResponse<List<CompanyWorkflowtypeItemOcrSettingPreset>>
    readCompanyWorkflowtypeItemOcrSettings(Session session) {

    SessionData sessionData = getSessionData(session);
    List<CompanyWorkflowtypeItemOcrSettingPreset> list = readCompanyWorkflowtypeItemOcrSettingsIntern(sessionData);

    return HttpResponse.ok(list);
  }

  @Post("/save")
  public HttpResponse<List<CompanyWorkflowtypeItemOcrSettingPreset>>
      saveCompanyWorkflowtypeItemOcrSettings(@Body final CompanyWorkflowtypeItemOcrSettingPreset preset, Session session) {

    SessionData sessionData = getSessionData(session);
    preset.setCompanyId(sessionData.getCompanyId());

    this.companyHandler.saveCompanyWorkflowtypeItemOcrSetting(preset, sessionData.getRefreshToken());

    List<CompanyWorkflowtypeItemOcrSettingPreset> list = readCompanyWorkflowtypeItemOcrSettingsIntern(sessionData);
    return HttpResponse.created(list);
  }

  @Post( "/delete")
  public HttpResponse<List<CompanyWorkflowtypeItemOcrSettingPreset>>
      deleteCompanyWorkflowtypeItemOcrSettings(@Body final CompanyWorkflowtypeItemOcrSettingPreset preset, Session session) {

    SessionData sessionData = getSessionData(session);
    preset.setCompanyId(sessionData.getCompanyId());

    this.companyHandler.deleteCompanyWorkflowtypeItemOcrSetting(preset, sessionData.getRefreshToken());

    List<CompanyWorkflowtypeItemOcrSettingPreset> list = readCompanyWorkflowtypeItemOcrSettingsIntern(sessionData);
    return HttpResponse.ok(list);
  }

  
  @Get( "/read/{presetName}" )
  public HttpResponse<Map<String, CompanyWorkflowtypeItemOcrSettingPresetItem>> 
    readPresetAllItems(final String presetName, Session session) {
    SessionData sessionData = getSessionData(session);

    Optional<CompanyWorkflowtypeItemOcrSettingPreset> presetOptional = sessionData.findOcrPresetByName(presetName);
    if(presetOptional.isPresent()){

      WorkflowType workflowType = sessionData.findWorkflowType(presetOptional.get().getWorkflowTypeId());
      Map<String, CompanyWorkflowtypeItemOcrSettingPresetItem> result =
              this.companyHandler.readPresetAllItemsFromSession(presetName,
                                                                sessionData.getCompany().getOcrPresets(),
                                                                workflowType.getTypeEnum());
      return HttpResponse.ok(result);
    }

    return HttpResponse.notFound();
  }

  private List<CompanyWorkflowtypeItemOcrSettingPreset> readCompanyWorkflowtypeItemOcrSettingsIntern(SessionData sessionData) {

    List<CompanyWorkflowtypeItemOcrSettingPreset> list =
            this.companyHandler.readCompanyWorkflowtypeItemOcrSettings(sessionData.getCompanyId(),
                                                                       sessionData.getRefreshToken());

    return list;
  }
}
