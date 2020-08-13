package com.pth.profile.services.data;

import com.pth.profile.entities.DepartmentEntity;
import com.pth.profile.entities.UserDashboardMenuEntity;
import com.pth.profile.entities.UserEntity;
import com.pth.profile.entities.UserGroupEntity;
import com.pth.profile.models.ProfileResponse;

import java.util.List;
import java.util.Optional;
import java.util.Set;


public interface IUsersService {

  Optional<UserEntity> save(UserEntity model);

  void delete(UserEntity model);

  Optional<UserEntity> getUserByIdentity(String identity);

  Optional<UserEntity> getUserByEmail(String email);

  Optional<ProfileResponse> getProfileResponseByEmail(String appIdentity,
                                                      String email);

  Optional<ProfileResponse> getProfileResponseByIdentity(String appIdentity,
                                                         String identity);

  List<UserGroupEntity> getUserGroups(String identity);

  List<DepartmentEntity> getUserDepartments(String identity);

  List<UserEntity> getUserDeputies(String identity);

  List<UserEntity> getCompanyUsers(String companyIdentity);

  List<UserEntity> getAllUserIdentityListByDepartmentIdentity(String identity);

  List<UserEntity> getUserListByIdentityList(Set<String> identityList);

  List<UserDashboardMenuEntity> getUserDashboardMenuListByUserIdentity(String appIdentity,
                                                                       String userIdentity);

  List<UserDashboardMenuEntity> saveUserDashboardMenuListByUserIdentity(String appIdentity,
                                                                        String userIdentity,
                                                                        List<UserDashboardMenuEntity> list);

}
