package com.pth.profile.services.data.impl;

import com.pth.common.edo.ProfileResponseEdo;
import com.pth.common.edo.UserDashboardMenuEdo;
import com.pth.common.exceptions.IFlowMessageConversionFailureException;
import com.pth.profile.entities.DepartmentEntity;
import com.pth.profile.entities.UserDashboardMenuEntity;
import com.pth.profile.entities.UserEntity;
import com.pth.profile.entities.UserGroupEntity;
import com.pth.profile.models.ProfileResponse;
import com.pth.profile.repositories.ICompanyRepository;
import com.pth.profile.repositories.IDepartmentRepository;
import com.pth.profile.repositories.IUserGroupRepository;
import com.pth.profile.repositories.IUserRepository;
import com.pth.profile.services.data.IUsersService;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public class UsersService implements IUsersService {

  private final IUserRepository userRepository;
  private final ICompanyRepository companyRepository;
  private final IUserGroupRepository userGroupRepository;
  private final IDepartmentRepository departmentDao;
  //private final IWorkflowTypeDao workflowTypeDao;


  public UsersService(IUserRepository userRepository, ICompanyRepository companyRepository, IUserGroupRepository userGroupRepository, IDepartmentRepository departmentDao) {
    this.userRepository = userRepository;
    this.companyRepository = companyRepository;
    this.userGroupRepository = userGroupRepository;
    this.departmentDao = departmentDao;
  }

  @Override
  public Optional<UserEntity> save(UserEntity model) {
    this.userRepository.save(model);
    return this.userRepository.getById(model.getId());
  }

  @Override
  public void delete(UserEntity model) {
    this.userRepository.delete(model);
  }

  @Override
  public Optional<UserEntity> getUserByIdentity(String identity) {
    return this.userRepository.getByIdentity(identity);
  }

  @Override
  public Optional<UserEntity> getUserByEmail(String email) {
    return this.userRepository.getUserByEmail(email);
  }

  @Override
  public ProfileResponse getProfileResponseByEmail(String appIdentity, String email) {
    return null;
  }

  @Override
  public ProfileResponse getProfileResponseByIdentity(String appIdentity, String identity) {
    return null;
  }

  @Override
  public List<UserGroupEntity> getUserGroups(String identity) {
    return null;
  }

  @Override
  public List<DepartmentEntity> getUserDepartments(String identity) {
    return null;
  }

  @Override
  public List<UserEntity> getUserDeputies(String identity) {
    return null;
  }

  @Override
  public List<UserEntity> getCompanyUsers(String companyIdentity) {
    return null;
  }

  @Override
  public List<UserEntity> getAllUserIdentityListByDepartmentIdentity(String identity) {
    return null;
  }

  @Override
  public List<UserEntity> getUserListByIdentityList(Set<String> identityList) {
    return null;
  }

  @Override
  public ProfileResponseEdo toProfileResponseEdo(ProfileResponse model) {
    return null;
  }

  @Override
  public List<UserDashboardMenuEntity> getUserDashboardMenuListByUserIdentity(String appIdentity, String userIdentity) {
    return null;
  }

  @Override
  public List<UserDashboardMenuEntity> saveUserDashboardMenuListByUserIdentity(String appIdentity, String userIdentity, List<UserDashboardMenuEntity> list) {
    return null;
  }

  @Override
  public List<UserDashboardMenuEdo> toUserDashboardMenuEdoList(List<UserDashboardMenuEntity> modelList) {
    return null;
  }

  @Override
  public List<UserDashboardMenuEntity> fromUserDashboardMenuEdoList(List<UserDashboardMenuEdo> edoList) throws IFlowMessageConversionFailureException {
    return null;
  }
}
