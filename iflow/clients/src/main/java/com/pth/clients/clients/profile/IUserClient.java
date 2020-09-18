package com.pth.clients.clients.profile;

import com.pth.common.edo.ProfileResponseEdo;
import com.pth.common.edo.UserEdo;
import com.pth.common.edo.UserListEdo;
import com.pth.common.edo.UserPasswordResetRequestEdo;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Header;

import javax.validation.Valid;
import java.util.Optional;
import java.util.UUID;

public interface IUserClient {

  Optional<UserEdo> saveUser(String authorization, final UserEdo userEdo);

  void resetPassword(String authorization,
                     final UserPasswordResetRequestEdo userPasswordResetRequestEdo,
                     final UUID id);

  void deleteUser(String authorization,
                  final UserEdo userEdo);

  Optional<UserEdo> readUserById(final String authorization,
                                 final UUID id);

  Optional<UserListEdo> readCompanyUsers(final String authorization,
                                         final UUID companyid);

  Optional<ProfileResponseEdo> readUserProfileById(final String authorization,
                                                   final String appIdentity,
                                                   final UUID id);

  Optional<ProfileResponseEdo> readUserProfileByUsername(final String authorization,
                                                         final String appIdentity,
                                                         final String username);

  Optional<UserListEdo> readDepartmentUsers(final String authorization, final UUID id);
}
