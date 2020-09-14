package com.pth.clients.clients.profile.impl;

import com.pth.clients.clients.ClientBase;
import com.pth.clients.clients.profile.ICompanyClient;
import com.pth.clients.declaratives.company.ICompanyV001DeclarativeClient;
import com.pth.common.edo.CompanyEdo;
import com.pth.common.edo.CompanyWorkflowtypeItemOcrSettingPresetEdo;
import com.pth.common.edo.CompanyWorkflowtypeItemOcrSettingPresetListEdo;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;

import javax.inject.Singleton;
import java.util.Optional;
import java.util.UUID;

@Singleton
public class CompanyClient extends ClientBase implements ICompanyClient {

    private final ICompanyV001DeclarativeClient companyDeclarativeClient;

    public CompanyClient(ICompanyV001DeclarativeClient companyDeclarativeClient) {
        this.companyDeclarativeClient = companyDeclarativeClient;
    }

    @Override
    public Optional<CompanyEdo> read(String authorization,
                                     UUID id) {
        HttpResponse<CompanyEdo> response =
                this.companyDeclarativeClient.read(prepareBearerAuthorization(authorization), id);
        if(response.getStatus() == HttpStatus.OK){
            return  response.getBody();
        }

        return Optional.empty();
    }

    @Override
    public Optional<CompanyEdo> save(String authorization,
                                     CompanyEdo company) {

        HttpResponse<CompanyEdo> response =
                this.companyDeclarativeClient.save(prepareBearerAuthorization(authorization), company);
        if(response.getStatus() == HttpStatus.CREATED){
            return  response.getBody();
        }

        return Optional.empty();
    }

    @Override
    public Optional<CompanyWorkflowtypeItemOcrSettingPresetListEdo>
        readCompanyWorkflowtypeItemOcrSettings(String authorization, UUID id) {

        HttpResponse<CompanyWorkflowtypeItemOcrSettingPresetListEdo> response =
                this.companyDeclarativeClient.readCompanyWorkflowtypeItemOcrSettings(
                        prepareBearerAuthorization(authorization),
                        id);
        if(response.getStatus() == HttpStatus.OK){
            return  response.getBody();
        }

        return Optional.empty();
    }

    @Override
    public Optional<CompanyWorkflowtypeItemOcrSettingPresetEdo>
        saveCompanyWorkflowtypeItemOcrSettings(String authorization,
                                               CompanyWorkflowtypeItemOcrSettingPresetEdo presetEdo) {

        HttpResponse<CompanyWorkflowtypeItemOcrSettingPresetEdo> response =
                this.companyDeclarativeClient.saveCompanyWorkflowtypeItemOcrSettings(
                        prepareBearerAuthorization(authorization),
                        presetEdo);
        if(response.getStatus() == HttpStatus.CREATED){
            return  response.getBody();
        }

        return Optional.empty();
    }

    @Override
    public void deleteCompanyWorkflowtypeItemOcrSettings(String authorization,
                                                         CompanyWorkflowtypeItemOcrSettingPresetEdo presetEdo) {

        HttpResponse<?> response = this.companyDeclarativeClient.deleteCompanyWorkflowtypeItemOcrSettings(
                        prepareBearerAuthorization(authorization),
                        presetEdo);
        if(response.getStatus() == HttpStatus.CREATED){

        }

    }
}
