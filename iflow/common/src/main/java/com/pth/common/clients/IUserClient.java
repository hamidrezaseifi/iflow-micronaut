package com.pth.common.clients;

import com.pth.common.edo.ProfileResponseEdo;
import com.pth.common.edo.UserEdo;
import com.pth.common.edo.UserListEdo;

import java.util.Optional;
import java.util.UUID;

public interface IUserClient {

  Optional<UserEdo> saveUser(String authorization,
                             final UserEdo userEdo);

  void deleteUser(String authorization,
                  final UserEdo userEdo);

  Optional<UserEdo> readUserById(final String authorization, final UUID id);

  Optional<UserListEdo> readCompanyUsers(final String authorization, final UUID companyid);

  Optional<ProfileResponseEdo> readUserProfileById(final String authorization, final String appIdentity, final UUID id);

}
