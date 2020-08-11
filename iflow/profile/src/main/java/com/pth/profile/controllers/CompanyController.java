package com.pth.profile.controllers;


import com.pth.common.contants.ApiUrlConstants;
import com.pth.common.edo.CompanyEdo;
import com.pth.common.edo.CompanyWorkflowtypeItemOcrSettingPresetEdo;
import com.pth.common.edo.CompanyWorkflowtypeItemOcrSettingPresetListEdo;
import com.pth.profile.entities.CompanyEntity;
import com.pth.profile.entities.CompanyWorkflowTypeOcrSettingPresetEntity;
import com.pth.profile.services.data.ICompanyService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import io.micronaut.validation.Validated;

import javax.validation.Valid;
import java.util.List;

@Validated
@Secured(SecurityRule.IS_AUTHENTICATED)
@Controller(ApiUrlConstants.ProfileUrlConstants.API001_CORE001_COMPANY)
public class CompanyController {

  final ICompanyService companyService;

  public CompanyController(final ICompanyService companyService) {

    this.companyService = companyService;
  }

  @Produces(MediaType.APPLICATION_JSON)
  @Secured(SecurityRule.IS_AUTHENTICATED)
  @Get(value = "/readbyidentity/{companyidentity}")
  public HttpResponse<CompanyEdo> readCompany(final String companyidentity) throws Exception {

    final CompanyEntity company = this.companyService.getByIdentity(companyidentity);

    return HttpResponse.ok( this.companyService.toEdo(company));
  }

  @Produces(MediaType.APPLICATION_JSON)
  @Secured(SecurityRule.IS_AUTHENTICATED)
  @Post(value = "/save")
  public HttpResponse<CompanyEdo> saveCompany(@Body @Valid final CompanyEdo requestCompany)
      throws Exception {

    final CompanyEntity savedCompany = this.companyService.save(companyService.fromEdo(requestCompany));

    return HttpResponse.created(this.companyService.toEdo(savedCompany));
  }

  @Produces(MediaType.APPLICATION_JSON)
  @Secured(SecurityRule.IS_AUTHENTICATED)
  @Get(value = "/readwtoctsettings/{companyidentity}")
  public HttpResponse<CompanyWorkflowtypeItemOcrSettingPresetListEdo>
      readCompanyWorkflowtypeItemOcrSettings(final String companyidentity) throws Exception {

    final List<CompanyWorkflowTypeOcrSettingPresetEntity> modelList = this.companyService
        .readCompanyWorkflowtypeItemOcrSettingsByCompanyIdentity(companyidentity);

    final List<CompanyWorkflowtypeItemOcrSettingPresetEdo> edoList = this.companyService
        .toCompanyWorkflowtypeItemOcrSettingPresetEdoList(modelList);

    return HttpResponse.ok( new CompanyWorkflowtypeItemOcrSettingPresetListEdo(edoList));
  }

  @Produces(MediaType.APPLICATION_JSON)
  @Secured(SecurityRule.IS_AUTHENTICATED)
  @Post(value = "/savewtoctsettings")
  public HttpResponse<CompanyWorkflowtypeItemOcrSettingPresetEdo>
      saveCompanyWorkflowtypeItemOcrSettings(
          @Body @Valid final CompanyWorkflowtypeItemOcrSettingPresetEdo presetEdo) throws Exception {

    final CompanyWorkflowTypeOcrSettingPresetEntity modelInput = this.companyService
        .fromCompanyWorkflowtypeItemOcrSettingPresetEdo(presetEdo);

    final CompanyWorkflowTypeOcrSettingPresetEntity modelSaved = this.companyService
        .saveCompanyWorkflowtypeItemOcrSetting(modelInput);

    final CompanyWorkflowtypeItemOcrSettingPresetEdo savedEdo = this.companyService
        .toCompanyWorkflowtypeItemOcrSettingPresetEdo(modelSaved);

    return HttpResponse.created( savedEdo);
  }

  @Produces(MediaType.APPLICATION_JSON)
  @Secured(SecurityRule.IS_AUTHENTICATED)
  @Post(value = "/deletewtoctsettings")
  public void
  deleteCompanyWorkflowtypeItemOcrSettings(@Body @Valid final CompanyWorkflowtypeItemOcrSettingPresetEdo presetEdo)
          throws Exception {

    final CompanyWorkflowTypeOcrSettingPresetEntity modelInput = this.companyService
        .fromCompanyWorkflowtypeItemOcrSettingPresetEdo(presetEdo);

    this.companyService.deleteCompanyWorkflowtypeItemOcrSetting(modelInput);

  }

}
