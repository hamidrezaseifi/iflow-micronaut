package com.pth.clients.clients.profile;

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
import java.util.Optional;
import java.util.UUID;

public interface ICompanyClient {

    Optional<CompanyEdo> read(String authorization,
                              final UUID id);

    Optional<CompanyEdo> save(String authorization, final CompanyEdo company);

    Optional<CompanyWorkflowtypeItemOcrSettingPresetListEdo>
        readCompanyWorkflowtypeItemOcrSettings(String authorization, final UUID id);

    Optional<CompanyWorkflowtypeItemOcrSettingPresetEdo>
        saveCompanyWorkflowtypeItemOcrSettings(String authorization,
                                               final CompanyWorkflowtypeItemOcrSettingPresetEdo presetEdo);

    void deleteCompanyWorkflowtypeItemOcrSettings(String authorization,
                                                 final CompanyWorkflowtypeItemOcrSettingPresetEdo presetEdo);
}
