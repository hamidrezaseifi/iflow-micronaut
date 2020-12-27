package com.pth.clients.clients.profile.impl;

import com.pth.clients.clients.ClientBase;
import com.pth.clients.declaratives.user.IUserV001DeclarativeClient;
import com.pth.clients.clients.profile.IUserClient;
import com.pth.common.edo.*;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;

import javax.inject.Singleton;
import java.util.Optional;
import java.util.UUID;

@Singleton
public class UserClient extends ClientBase implements IUserClient {

  private final IUserV001DeclarativeClient userDeclarativeClient;

  public UserClient(IUserV001DeclarativeClient userDeclarativeClient) {
    this.userDeclarativeClient = userDeclarativeClient;
  }

  @Override
  public Optional<UserEdo> createUser(String authorization, UserEdo userEdo) {

    HttpResponse<UserEdo> response =
            this.userDeclarativeClient.createUser(prepareBearerAuthorization(authorization), userEdo);
    if(response.getStatus() == HttpStatus.CREATED){
      return  response.getBody();
    }

    return Optional.empty();
  }

  @Override
  public Optional<UserEdo> saveUser(String authorization, UserEdo userEdo) {

    HttpResponse<UserEdo> response =
            this.userDeclarativeClient.saveUser(prepareBearerAuthorization(authorization), userEdo);
    if(response.getStatus() == HttpStatus.CREATED){
      return  response.getBody();
    }

    return Optional.empty();
  }

  @Override
  public void resetPassword(String authorization,
                            final UserPasswordResetRequestEdo userPasswordResetRequestEdo,
                            final UUID id) {

    HttpResponse<?> response =
            this.userDeclarativeClient.resetPassword(prepareBearerAuthorization(authorization),
                                                     userPasswordResetRequestEdo,
                                                     id);
    if(response.getStatus() == HttpStatus.CREATED){

    }

  }

  @Override
  public void deleteUser(String authorization, UserEdo userEdo) {

    HttpResponse<?> response = this.userDeclarativeClient.deleteUser(prepareBearerAuthorization(authorization), userEdo);
    if(response.getStatus() == HttpStatus.ACCEPTED){

    }
  }

  @Override
  public Optional<UserEdo> readUserById(String authorization, UUID id) {
    HttpResponse<UserEdo> response = this.userDeclarativeClient.readUserById(prepareBearerAuthorization(authorization), id);
    if(response.getStatus() == HttpStatus.OK){
      return  response.getBody();
    }

    return Optional.empty();
  }

  @Override
  public Optional<UserListEdo> readCompanyUsers(String authorization, UUID companyid) {
    HttpResponse<UserListEdo> response = this.userDeclarativeClient.readCompanyUsers(prepareBearerAuthorization(authorization), companyid);
    if(response.getStatus() == HttpStatus.OK){
      return  response.getBody();
    }

    return Optional.empty();
  }

  @Override
  public Optional<ProfileResponseEdo> readUserProfileById(String authorization, String appIdentity, UUID id) {

    String authorizationBearer = prepareBearerAuthorization(authorization);
    HttpResponse<ProfileResponseEdo> response =
            this.userDeclarativeClient.readUserProfileById(authorizationBearer, appIdentity, id);
    if(response.getStatus() == HttpStatus.OK){
      return  response.getBody();
    }

    return Optional.empty();
  }

  @Override
  public Optional<ProfileResponseEdo> readUserProfileByUsername(String authorization,
                                                                String appIdentity,
                                                                String username) {
    String authorizationBearer = prepareBearerAuthorization(authorization);
    HttpResponse<ProfileResponseEdo> response =
            this.userDeclarativeClient.readUserProfileByUsername(authorizationBearer, appIdentity, username);
    if(response.getStatus() == HttpStatus.OK){
      return  response.getBody();
    }

    return Optional.empty();
  }

  @Override
  public Optional<UserListEdo> readDepartmentUsers(String authorization, UUID id) {
    String authorizationBearer = prepareBearerAuthorization(authorization);
    HttpResponse<UserListEdo> response = this.userDeclarativeClient.readDepartmentUsers(authorizationBearer, id);
    if(response.getStatus() == HttpStatus.OK){
      return  response.getBody();
    }

    return Optional.empty();
  }

  @Override
  public Optional<UserDashboardMenuListEdo> readUserDashboardMenuByIdentity(String authorization,
                                                                            String appIdentity,
                                                                            UUID userId){
    String authorizationBearer = prepareBearerAuthorization(authorization);
    HttpResponse<UserDashboardMenuListEdo> response =
            this.userDeclarativeClient.readUserDashboardMenuByIdentity(authorizationBearer, appIdentity, userId);
    if(response.getStatus() == HttpStatus.OK){
      return  response.getBody();
    }

    return Optional.empty();
  }

  @Override
  public Optional<UserDashboardMenuListEdo> saveUserDashboardMenuByIdentity(String authorization,
                                                                            UserDashboardMenuListEdo requestedEdoList,
                                                                            String appIdentity,
                                                                            UUID userId){
    String authorizationBearer = prepareBearerAuthorization(authorization);
    HttpResponse<UserDashboardMenuListEdo> response =
            this.userDeclarativeClient.saveUserDashboardMenuByIdentity(authorizationBearer,
                                                                       requestedEdoList,
                                                                       appIdentity,
                                                                       userId);
    if(response.getStatus() == HttpStatus.CREATED){
      return  response.getBody();
    }

    return Optional.empty();
  }
}
