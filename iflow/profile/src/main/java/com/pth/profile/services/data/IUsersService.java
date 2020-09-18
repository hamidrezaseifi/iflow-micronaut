package com.pth.profile.services.data;

import com.pth.profile.entities.DepartmentEntity;
import com.pth.profile.entities.UserDashboardMenuEntity;
import com.pth.profile.entities.UserEntity;
import com.pth.profile.entities.UserGroupEntity;
import com.pth.profile.models.ProfileResponse;
import com.pth.profile.models.UserPasswordResetRequest;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;


public interface IUsersService {

  Optional<UserEntity> create(UserEntity model);

  Optional<UserEntity> save(UserEntity model);

  void delete(UserEntity model);

  void resetPassword(UUID userId, UserPasswordResetRequest userPasswordResetRequest);

  Optional<UserEntity> getUserById(UUID id);

  Optional<UserEntity> getUserByUsername(String email);

  Optional<ProfileResponse> getProfileResponseByUserName(String appIdentity,
                                                         String userName);

  Optional<ProfileResponse> getProfileResponseById(String appIdentity,
                                                   UUID id);

  List<UserGroupEntity> getUserGroups(UUID id);

  List<DepartmentEntity> getUserDepartments(UUID id);

  List<UserEntity> getUserDeputies(UUID id);

  List<UserEntity> getCompanyUsers(UUID companyId);

  List<UserEntity> getAllUserListByDepartmentId(UUID id);

  List<UserEntity> getUserListByIdentityList(Set<String> identityList);

  List<UserDashboardMenuEntity> getUserDashboardMenuListByUserId(String appIdentity,
                                                                 UUID userId);

  List<UserDashboardMenuEntity> saveUserDashboardMenuListByUserId(String appIdentity,
                                                                  UUID userId,
                                                                  List<UserDashboardMenuEntity> list);

}
