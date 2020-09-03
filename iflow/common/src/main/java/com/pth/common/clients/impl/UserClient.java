package com.pth.common.clients.impl;

import com.pth.common.clients.IUserClient;
import com.pth.common.declaratives.user.IUserV001DeclarativeClient;
import com.pth.common.edo.ProfileResponseEdo;
import com.pth.common.edo.UserEdo;
import com.pth.common.edo.UserListEdo;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;

import javax.inject.Singleton;
import java.util.Optional;
import java.util.UUID;

@Singleton
public class UserClient implements IUserClient {

  private final IUserV001DeclarativeClient userDeclarativeClient;

  public UserClient(IUserV001DeclarativeClient userDeclarativeClient) {
    this.userDeclarativeClient = userDeclarativeClient;
  }

  @Override
  public Optional<UserEdo> saveUser(String authorization, UserEdo userEdo) {

    HttpResponse<UserEdo> response = this.userDeclarativeClient.saveUser(authorization, userEdo);
    if(response.getStatus() == HttpStatus.CREATED){
      return  response.getBody();
    }

    return Optional.empty();
  }

  @Override
  public void deleteUser(String authorization, UserEdo userEdo) {

    HttpResponse<?> response = this.userDeclarativeClient.deleteUser(authorization, userEdo);
    if(response.getStatus() == HttpStatus.ACCEPTED){

    }
  }

  @Override
  public Optional<UserEdo> readUserById(String authorization, UUID id) {
    HttpResponse<UserEdo> response = this.userDeclarativeClient.readUserById(authorization, id);
    if(response.getStatus() == HttpStatus.OK){
      return  response.getBody();
    }

    return Optional.empty();
  }

  @Override
  public Optional<UserListEdo> readCompanyUsers(String authorization, UUID companyid) {
    HttpResponse<UserListEdo> response = this.userDeclarativeClient.readCompanyUsers(authorization, companyid);
    if(response.getStatus() == HttpStatus.OK){
      return  response.getBody();
    }

    return Optional.empty();
  }

  @Override
  public Optional<ProfileResponseEdo> readUserProfileById(String authorization, String appIdentity, UUID id) {
    HttpResponse<ProfileResponseEdo> response =
            this.userDeclarativeClient.readUserProfileById(authorization, appIdentity, id);
    if(response.getStatus() == HttpStatus.OK){
      return  response.getBody();
    }

    return Optional.empty();
  }
}
