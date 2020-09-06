package com.pth.clients.declaratives.company;

import com.pth.common.contants.ApiUrlConstants;
import com.pth.common.edo.CompanyEdo;
import com.pth.common.edo.CompanyWorkflowtypeItemOcrSettingPresetEdo;
import com.pth.common.edo.CompanyWorkflowtypeItemOcrSettingPresetListEdo;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Header;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.client.annotation.Client;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@Client(id = "profile")
public interface ICompanyV001DeclarativeClient {

    @Get(ApiUrlConstants.ProfileUrlConstants.API001_CORE001_COMPANY + "/readbyidentity/{companyIdentity}")
    HttpResponse<CompanyEdo> readCompanyByIdentity(@Header String authorization,
                                                   final String companyIdentity);

    @Get(ApiUrlConstants.ProfileUrlConstants.API001_CORE001_COMPANY + "/readwtoctsettings/{companyIdentity}")
    HttpResponse<CompanyWorkflowtypeItemOcrSettingPresetListEdo> readCompanyWorkflowtypeItemOcrSettings(
            @Header String authorization,
            final String companyIdentity);

    @Post(ApiUrlConstants.ProfileUrlConstants.API001_CORE001_COMPANY + "/savewtoctsettings")
    HttpResponse<CompanyWorkflowtypeItemOcrSettingPresetEdo>
        saveCompanyWorkflowtypeItemOcrSettings(@Header String authorization,
                                               @NotBlank @Body @Valid
                                               final CompanyWorkflowtypeItemOcrSettingPresetEdo presetEdo);

    @Post(ApiUrlConstants.ProfileUrlConstants.API001_CORE001_COMPANY + "/deletewtoctsettings")
    HttpResponse<?>
        deleteCompanyWorkflowtypeItemOcrSettings(@Header String authorization,
                                                 @NotBlank @Body @Valid
                                                 final CompanyWorkflowtypeItemOcrSettingPresetEdo presetEdo);
}
