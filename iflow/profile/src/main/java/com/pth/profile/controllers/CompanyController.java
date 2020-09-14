package com.pth.profile.controllers;


import com.pth.common.contants.ApiUrlConstants;
import com.pth.common.edo.CompanyEdo;
import com.pth.common.edo.CompanyWorkflowtypeItemOcrSettingPresetEdo;
import com.pth.common.edo.CompanyWorkflowtypeItemOcrSettingPresetListEdo;
import com.pth.profile.entities.CompanyEntity;
import com.pth.profile.entities.CompanyWorkflowTypeOcrSettingPresetEntity;
import com.pth.profile.mapper.ICompanyMapper;
import com.pth.profile.mapper.ICompanyWorkflowTypeOcrSettingPresetMapper;
import com.pth.profile.services.data.ICompanyService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import io.micronaut.validation.Validated;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Validated
@Secured(SecurityRule.IS_AUTHENTICATED)
@Controller(ApiUrlConstants.ProfileUrlConstants.API001_CORE001_COMPANY)
public class CompanyController {

  private final ICompanyService companyService;
  private final ICompanyMapper companyModelEdoMapper;
  private final ICompanyWorkflowTypeOcrSettingPresetMapper presetModelEdoMapper;

  public CompanyController(ICompanyService companyService,
                           ICompanyMapper companyModelEdoMapper,
                           ICompanyWorkflowTypeOcrSettingPresetMapper presetModelEdoMapper) {

    this.companyService = companyService;
    this.companyModelEdoMapper = companyModelEdoMapper;
    this.presetModelEdoMapper = presetModelEdoMapper;
  }

  @Produces(MediaType.APPLICATION_JSON)
  @Secured(SecurityRule.IS_AUTHENTICATED)
  @Get(value = "/read/{id}")
  public HttpResponse<CompanyEdo> readCompany(final UUID id) throws Exception {

    final Optional<CompanyEntity> companyEntityOptional = this.companyService.getById(id);

    if(companyEntityOptional.isPresent()){

      CompanyEdo companyEdo = this.companyModelEdoMapper.toEdo(companyEntityOptional.get());

      return HttpResponse.ok(companyEdo);
    }
    return HttpResponse.notFound();
  }

  @Produces(MediaType.APPLICATION_JSON)
  @Secured(SecurityRule.IS_AUTHENTICATED)
  @Post(value = "/save")
  public HttpResponse<CompanyEdo> saveCompany(@Body @Valid final CompanyEdo requestCompany)
      throws Exception {

    final Optional<CompanyEntity> savedCompanyEntityOptional = this.companyService.save(companyModelEdoMapper.fromEdo(requestCompany));

    if(savedCompanyEntityOptional.isPresent()){

      CompanyEdo companyEdo = this.companyModelEdoMapper.toEdo(savedCompanyEntityOptional.get());

      return HttpResponse.created(companyEdo);
    }
    return HttpResponse.badRequest();

  }

  @Produces(MediaType.APPLICATION_JSON)
  @Secured(SecurityRule.IS_AUTHENTICATED)
  @Get(value = "/readwtoctsettings/{id}")
  public HttpResponse<CompanyWorkflowtypeItemOcrSettingPresetListEdo>
      readCompanyWorkflowtypeItemOcrSettings(final UUID id) throws Exception {

    final List<CompanyWorkflowTypeOcrSettingPresetEntity> modelList = this.companyService
        .getCompanyWorkflowtypeItemOcrSettingListByCompanyIdentity(id);

    final List<CompanyWorkflowtypeItemOcrSettingPresetEdo> edoList = this.presetModelEdoMapper.toEdoList(modelList);

    return HttpResponse.ok( new CompanyWorkflowtypeItemOcrSettingPresetListEdo(edoList));
  }

  @Produces(MediaType.APPLICATION_JSON)
  @Secured(SecurityRule.IS_AUTHENTICATED)
  @Post(value = "/savewtoctsettings")
  public HttpResponse<CompanyWorkflowtypeItemOcrSettingPresetEdo>
      saveCompanyWorkflowtypeItemOcrSettings(
          @Body @Valid final CompanyWorkflowtypeItemOcrSettingPresetEdo presetEdo) throws Exception {

    final CompanyWorkflowTypeOcrSettingPresetEntity modelInput = this.presetModelEdoMapper.fromEdo(presetEdo);

    final Optional<CompanyWorkflowTypeOcrSettingPresetEntity> modelSavedOptional = this.companyService
        .saveCompanyWorkflowtypeItemOcrSetting(modelInput);

    if(modelSavedOptional.isPresent()){

      CompanyWorkflowTypeOcrSettingPresetEntity modelSaved = modelSavedOptional.get();
      final CompanyWorkflowtypeItemOcrSettingPresetEdo savedEdo = this.presetModelEdoMapper.toEdo(modelSaved);

      return HttpResponse.created( savedEdo);
    }

    return HttpResponse.badRequest();
  }

  @Produces(MediaType.APPLICATION_JSON)
  @Secured(SecurityRule.IS_AUTHENTICATED)
  @Post(value = "/deletewtoctsettings")
  public void
    deleteCompanyWorkflowtypeItemOcrSettings(@Body @Valid final CompanyWorkflowtypeItemOcrSettingPresetEdo presetEdo)
          throws Exception {

    final CompanyWorkflowTypeOcrSettingPresetEntity modelInput = this.presetModelEdoMapper.fromEdo(presetEdo);

    this.companyService.deleteCompanyWorkflowtypeItemOcrSetting(modelInput);

  }

}
