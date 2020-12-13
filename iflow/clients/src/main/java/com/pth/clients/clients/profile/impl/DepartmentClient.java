package com.pth.clients.clients.profile.impl;

import com.pth.clients.clients.ClientBase;
import com.pth.clients.clients.profile.IDepartmentClient;
import com.pth.clients.declaratives.company.IDepartmentV001DeclarativeClient;
import com.pth.common.edo.CompanyEdo;
import com.pth.common.edo.DepartmentEdo;
import com.pth.common.edo.DepartmentListEdo;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;

import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Singleton
public class DepartmentClient extends ClientBase implements IDepartmentClient {

    private final IDepartmentV001DeclarativeClient departmentDeclarativeClient;

    public DepartmentClient(IDepartmentV001DeclarativeClient departmentDeclarativeClient) {
        this.departmentDeclarativeClient = departmentDeclarativeClient;
    }

    @Override
    public List<DepartmentEdo> getCompanyDepartmentList(String authorization,
                                                        UUID companyId) {
        HttpResponse<DepartmentListEdo> response =
                this.departmentDeclarativeClient.listByCompanyId(prepareBearerAuthorization(authorization), companyId);
        if(response.getStatus() == HttpStatus.OK){
            return response.getBody().get().getDepartments();
        }

        return new ArrayList<>();
    }

    @Override
    public Optional<DepartmentEdo> create(String authorization,
                                DepartmentEdo departmentEdo) {
        HttpResponse<DepartmentEdo> response =
                this.departmentDeclarativeClient.create(prepareBearerAuthorization(authorization), departmentEdo);
        if(response.getStatus() == HttpStatus.CREATED){
            return response.getBody();
        }

        return Optional.empty();
    }

    @Override
    public Optional<DepartmentEdo> update(String authorization,
                                DepartmentEdo departmentEdo) {
        HttpResponse<DepartmentEdo> response =
                this.departmentDeclarativeClient.update(prepareBearerAuthorization(authorization), departmentEdo);
        if(response.getStatus() == HttpStatus.OK){
            return response.getBody();
        }

        return Optional.empty();
    }

    @Override
    public void delete(String authorization,
                       DepartmentEdo departmentEdo) {
        HttpResponse<?> response =
                this.departmentDeclarativeClient.delete(prepareBearerAuthorization(authorization), departmentEdo);
        if(response.getStatus() == HttpStatus.OK){

        }

    }
}
