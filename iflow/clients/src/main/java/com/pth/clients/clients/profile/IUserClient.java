package com.pth.clients.clients.profile;

import com.pth.common.edo.*;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Header;

import javax.validation.Valid;
import java.util.Optional;
import java.util.UUID;

public interface IUserClient {

  Optional<UserEdo> saveUser(String authorization, UserEdo userEdo);

  void resetPassword(String authorization,
                     UserPasswordResetRequestEdo userPasswordResetRequestEdo,
                     UUID id);

  void deleteUser(String authorization,
                  UserEdo userEdo);

  Optional<UserEdo> readUserById(String authorization,
                                 UUID id);

  Optional<UserListEdo> readCompanyUsers(String authorization,
                                         UUID companyid);

  Optional<ProfileResponseEdo> readUserProfileById(String authorization,
                                                   String appIdentity,
                                                   UUID id);

  Optional<ProfileResponseEdo> readUserProfileByUsername(String authorization,
                                                         String appIdentity,
                                                         String username);

  Optional<UserListEdo> readDepartmentUsers(String authorization, UUID id);

  Optional<UserDashboardMenuListEdo> readUserDashboardMenuByIdentity(String authorization,
                                                                     String appIdentity,
                                                                     UUID userId);

  Optional<UserDashboardMenuListEdo> saveUserDashboardMenuByIdentity(String authorization,
                                                                     UserDashboardMenuListEdo requestedEdoList,
                                                                     String appIdentity,
                                                                     UUID userId);

}
