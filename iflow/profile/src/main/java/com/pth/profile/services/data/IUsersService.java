package com.pth.profile.services.data;

import com.pth.common.edo.ProfileResponseEdo;
import com.pth.common.edo.UserDashboardMenuEdo;
import com.pth.common.edo.UserEdo;
import com.pth.common.exceptions.IFlowMessageConversionFailureException;
import com.pth.profile.entities.DepartmentEntity;
import com.pth.profile.entities.UserDashboardMenuEntity;
import com.pth.profile.entities.UserEntity;
import com.pth.profile.entities.UserGroupEntity;
import com.pth.profile.models.ProfileResponse;
import com.pth.common.mapping.IModelEdoMapper;

import java.util.List;
import java.util.Optional;
import java.util.Set;


public interface IUsersService {

  Optional<UserEntity> save(UserEntity model);

  void delete(UserEntity model);

  Optional<UserEntity> getUserByIdentity(String identity);

  Optional<UserEntity> getUserByEmail(String email);

  ProfileResponse getProfileResponseByEmail(String appIdentity,
                                            String email);

  ProfileResponse getProfileResponseByIdentity(String appIdentity,
                                               String identity);

  List<UserGroupEntity> getUserGroups(String identity);

  List<DepartmentEntity> getUserDepartments(String identity);

  List<UserEntity> getUserDeputies(String identity);

  List<UserEntity> getCompanyUsers(String companyIdentity);

  List<UserEntity> getAllUserIdentityListByDepartmentIdentity(String identity);

  List<UserEntity> getUserListByIdentityList(Set<String> identityList);

  ProfileResponseEdo toProfileResponseEdo(ProfileResponse model);

  List<UserDashboardMenuEntity> getUserDashboardMenuListByUserIdentity(String appIdentity,
                                                                       String userIdentity);

  List<UserDashboardMenuEntity> saveUserDashboardMenuListByUserIdentity(String appIdentity,
                                                                        String userIdentity,
                                                                        List<UserDashboardMenuEntity> list);

  List<UserDashboardMenuEdo> toUserDashboardMenuEdoList(List<UserDashboardMenuEntity> modelList);

  List<UserDashboardMenuEntity> fromUserDashboardMenuEdoList(List<UserDashboardMenuEdo> edoList)
          throws IFlowMessageConversionFailureException;

}
