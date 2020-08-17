package com.pth.profile.services.data.impl;

import com.pth.profile.authentication.entities.RefreshTokenEntity;
import com.pth.profile.entities.*;
import com.pth.profile.models.ProfileResponse;
import com.pth.profile.repositories.*;
import com.pth.profile.services.data.IUsersService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class UsersService implements IUsersService {

  private final IUserRepository userRepository;
  private final ICompanyRepository companyRepository;
  private final IUserGroupRepository userGroupRepository;
  private final IDepartmentRepository departmentRepository;
  private final ICompanyWorkflowTypeOcrSettingPresetRepository workflowTypeOcrSettingPresetRepository;
  private final IRefreshTokenRepository refreshTokenRepository;
  private final IUserDashboardMenuRepository userDashboardMenuRepository;

  public UsersService(IUserRepository userRepository,
                      ICompanyRepository companyRepository,
                      IUserGroupRepository userGroupRepository,
                      IDepartmentRepository departmentRepository,
                      ICompanyWorkflowTypeOcrSettingPresetRepository workflowTypeOcrSettingPresetRepository,
                      IRefreshTokenRepository refreshTokenRepository,
                      IUserDashboardMenuRepository userDashboardMenuRepository) {
    this.userRepository = userRepository;
    this.companyRepository = companyRepository;
    this.userGroupRepository = userGroupRepository;
    this.departmentRepository = departmentRepository;
    this.workflowTypeOcrSettingPresetRepository = workflowTypeOcrSettingPresetRepository;
    this.refreshTokenRepository = refreshTokenRepository;
    this.userDashboardMenuRepository = userDashboardMenuRepository;
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
  public Optional<ProfileResponse> getProfileResponseByEmail(String appIdentity, String email) {

    Optional<UserEntity> userEntityOptional = getUserByEmail(email);
    if(userEntityOptional.isPresent()){
      UserEntity userEntity = userEntityOptional.get();

      Optional<ProfileResponse> profileResponseOptional = generateProfileResponse(userEntity, appIdentity);
      return profileResponseOptional;
    }

    return Optional.empty();
  }

  @Override
  public Optional<ProfileResponse> getProfileResponseByIdentity(String appIdentity, String identity) {
    Optional<UserEntity> userEntityOptional = getUserByIdentity(identity);
    if(userEntityOptional.isPresent()){
      UserEntity userEntity = userEntityOptional.get();

      Optional<ProfileResponse> profileResponseOptional = generateProfileResponse(userEntity, appIdentity);
      return profileResponseOptional;
    }

    return Optional.empty();
  }

  @Override
  public List<UserGroupEntity> getUserGroups(String identity) {
    Optional<UserEntity> userEntityOptional = getUserByIdentity(identity);
    if(userEntityOptional.isPresent()){
      UserEntity userEntity = userEntityOptional.get();

      return userEntity.getGroups().stream().collect(Collectors.toList());
    }

    return new ArrayList<>();
  }

  @Override
  public List<DepartmentEntity> getUserDepartments(String identity) {
    Optional<UserEntity> userEntityOptional = getUserByIdentity(identity);
    if(userEntityOptional.isPresent()){
      UserEntity userEntity = userEntityOptional.get();

      return userEntity.getUserDepartments().stream().map(ud -> ud.getDepartment()).collect(Collectors.toList());
    }

    return new ArrayList<>();
  }

  @Override
  public List<UserEntity> getUserDeputies(String identity) {
    Optional<UserEntity> userEntityOptional = getUserByIdentity(identity);
    if(userEntityOptional.isPresent()){
      UserEntity userEntity = userEntityOptional.get();

      return userEntity.getDeputies().stream().collect(Collectors.toList());
    }

    return new ArrayList<>();
  }

  @Override
  public List<UserEntity> getCompanyUsers(String companyIdentity) {
    Optional<CompanyEntity> companyEntityOptional = this.companyRepository.getByIdentity(companyIdentity);
    if(companyEntityOptional.isPresent()){
      CompanyEntity companyEntity = companyEntityOptional.get();
      return this.userRepository.getUserListByCompanyId(companyEntity.getId());
    }
    return new ArrayList<>();
  }

  @Override
  public List<UserEntity> getAllUserIdentityListByDepartmentIdentity(String identity) {
    return this.userRepository.getUserListDepartmentIdentity(identity);
  }

  @Override
  public List<UserEntity> getUserListByIdentityList(Set<String> identityList) {
    return this.userRepository.getUserListByIdentityList(identityList);
  }

  @Override
  public List<UserDashboardMenuEntity> getUserDashboardMenuListByUserIdentity(String appIdentity, String userIdentity) {
    Optional<UserEntity> userEntityOptional = getUserByIdentity(userIdentity);
    if(userEntityOptional.isPresent()){
      UserEntity userEntity = userEntityOptional.get();

       return this.userDashboardMenuRepository.getByUserId(userEntity.getId(), appIdentity);
    }
    return new ArrayList<>();
  }

  @Override
  public List<UserDashboardMenuEntity> saveUserDashboardMenuListByUserIdentity(String appIdentity, String userIdentity, List<UserDashboardMenuEntity> list) {
    Optional<UserEntity> userEntityOptional = getUserByIdentity(userIdentity);
    if(userEntityOptional.isPresent()){
      UserEntity userEntity = userEntityOptional.get();

      for(UserDashboardMenuEntity userDashboardMenuEntity: list){
        userDashboardMenuEntity.setUserId(userEntity.getId());
        userDashboardMenuEntity.setAppId(appIdentity);
        userDashboardMenuRepository.save(userDashboardMenuEntity);
      }
      return this.userDashboardMenuRepository.getByUserId(userEntity.getId(), appIdentity);
    }
    return new ArrayList<>();
  }


  private Optional<ProfileResponse> generateProfileResponse(UserEntity userEntity, String appId){

    Optional<RefreshTokenEntity> refreshTokenEntityOptional = this.refreshTokenRepository.findByUsername(userEntity.getUsername());
    if(refreshTokenEntityOptional.isPresent()){
      RefreshTokenEntity tokenEntity = refreshTokenEntityOptional.get();
      CompanyEntity companyEntity = userEntity.getCompany();
      List<DepartmentEntity> departments = userEntity.getUserDepartments().stream().map(ud -> ud.getDepartment()).collect(Collectors.toList());
      List<UserGroupEntity> userGroups = userEntity.getGroups().stream().collect(Collectors.toList());
      List<CompanyWorkflowTypeOcrSettingPresetEntity> ocrPresetSettings = this.workflowTypeOcrSettingPresetRepository.getByCompanyId(userEntity.getCompanyId());
      List<UserDashboardMenuEntity> userDashboardMenus = this.userDashboardMenuRepository.getByUserId(userEntity.getId(), appId);

      ProfileResponse profileResponse = new ProfileResponse(userEntity,
              companyEntity,
              departments,
              userGroups,
              ocrPresetSettings,
              userDashboardMenus,
              tokenEntity.getRefreshToken());

      return Optional.of(profileResponse);
    }
    return Optional.empty();
  }

}
