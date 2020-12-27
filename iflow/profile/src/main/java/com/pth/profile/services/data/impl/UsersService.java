package com.pth.profile.services.data.impl;

import com.pth.common.credentials.IPasswordHashGenerator;
import com.pth.profile.authentication.entities.RefreshTokenEntity;
import com.pth.profile.entities.*;
import com.pth.profile.exception.UserNotFoundException;
import com.pth.profile.models.ProfileResponse;
import com.pth.profile.models.UserPasswordResetRequest;
import com.pth.profile.repositories.*;
import com.pth.profile.services.data.IUsersService;

import javax.inject.Singleton;
import java.util.*;
import java.util.stream.Collectors;

@Singleton
public class UsersService implements IUsersService {

  private final IUserRepository userRepository;
  private final ICompanyRepository companyRepository;
  private final IUserGroupRepository userGroupRepository;
  private final IDepartmentRepository departmentRepository;
  private final ICompanyWorkflowTypeOcrSettingPresetRepository workflowTypeOcrSettingPresetRepository;
  private final IRefreshTokenRepository refreshTokenRepository;
  private final IUserDashboardMenuRepository userDashboardMenuRepository;
  private final IPasswordHashGenerator passwordHashGenerator;
  private final ICompanyWorkflowTypeControllerRepository companyWorkflowTypeControllerRepository;

  public UsersService(IUserRepository userRepository,
                      ICompanyRepository companyRepository,
                      IUserGroupRepository userGroupRepository,
                      IDepartmentRepository departmentRepository,
                      ICompanyWorkflowTypeOcrSettingPresetRepository workflowTypeOcrSettingPresetRepository,
                      IRefreshTokenRepository refreshTokenRepository,
                      IUserDashboardMenuRepository userDashboardMenuRepository,
                      IPasswordHashGenerator passwordHashGenerator,
                      ICompanyWorkflowTypeControllerRepository companyWorkflowTypeControllerRepository) {
    this.userRepository = userRepository;
    this.companyRepository = companyRepository;
    this.userGroupRepository = userGroupRepository;
    this.departmentRepository = departmentRepository;
    this.workflowTypeOcrSettingPresetRepository = workflowTypeOcrSettingPresetRepository;
    this.refreshTokenRepository = refreshTokenRepository;
    this.userDashboardMenuRepository = userDashboardMenuRepository;
    this.passwordHashGenerator = passwordHashGenerator;
    this.companyWorkflowTypeControllerRepository = companyWorkflowTypeControllerRepository;
  }

  @Override
  public Optional<UserEntity> create(UserEntity model) {

    model.setPasswordHash("");
    model.setPasswordSalt("");
    this.userRepository.create(model);
    return this.userRepository.getById(model.getId());
  }

  @Override
  public Optional<UserEntity> save(UserEntity model) {
    this.userRepository.update(model);
    return this.userRepository.getById(model.getId());
  }

  @Override
  public void delete(UserEntity model) {
    this.userRepository.delete(model);
  }

  @Override
  public void resetPassword(UUID userId, UserPasswordResetRequest userPasswordResetRequest) {
    Optional<UserEntity> userEntityOptional = this.userRepository.getById(userId);
    if(userEntityOptional.isPresent()){
      UserEntity userEntity = userEntityOptional.get();

      String salt = passwordHashGenerator.produceSalt();
      String passwordHash = passwordHashGenerator.produceHash(userPasswordResetRequest.getPassword(), salt);
      userEntity.setPasswordHash(passwordHash);
      userEntity.setPasswordSalt(salt);


      this.userRepository.updatePassword(userEntity);

    }
    else
      throw new UserNotFoundException(userId.toString());
  }

  @Override
  public Optional<UserEntity> getUserById(UUID id) {
    Optional<UserEntity> userEntityOptional = this.userRepository.getById(id);
    if(userEntityOptional.isPresent()){
      return userEntityOptional;
    }
    throw new UserNotFoundException(id.toString());
  }

  @Override
  public Optional<UserEntity> getUserByUsername(String userName) {
    Optional<UserEntity> userEntityOptional = this.userRepository.getUserByUsername(userName);
    if(userEntityOptional.isPresent()){
      return userEntityOptional;
    }
    throw new UserNotFoundException(userName);
  }

  @Override
  public Optional<ProfileResponse> getProfileResponseByUserName(String appIdentity, String userName) {

    Optional<UserEntity> userEntityOptional = getUserByUsername(userName);
    if(userEntityOptional.isPresent()){
      UserEntity userEntity = userEntityOptional.get();

      Optional<ProfileResponse> profileResponseOptional = generateProfileResponse(userEntity, appIdentity);
      return profileResponseOptional;
    }

    throw new UserNotFoundException(userName);
  }

  @Override
  public Optional<ProfileResponse> getProfileResponseById(String appIdentity, UUID id) {
    Optional<UserEntity> userEntityOptional = getUserById(id);
    if(userEntityOptional.isPresent()){
      UserEntity userEntity = userEntityOptional.get();

      Optional<ProfileResponse> profileResponseOptional = generateProfileResponse(userEntity, appIdentity);
      return profileResponseOptional;
    }

    throw new UserNotFoundException(id.toString());
  }

  @Override
  public List<UserGroupEntity> getUserGroups(UUID id) {
    Optional<UserEntity> userEntityOptional = getUserById(id);
    if(userEntityOptional.isPresent()){
      UserEntity userEntity = userEntityOptional.get();

      return userEntity.getGroups().stream().collect(Collectors.toList());
    }

    throw new UserNotFoundException(id.toString());
  }

  @Override
  public List<DepartmentEntity> getUserDepartments(UUID id) {
    Optional<UserEntity> userEntityOptional = getUserById(id);
    if(userEntityOptional.isPresent()){
      UserEntity userEntity = userEntityOptional.get();

      return userEntity.getUserDepartments().stream().map(ud -> ud.getDepartment()).collect(Collectors.toList());
    }

    throw new UserNotFoundException(id.toString());
  }

  @Override
  public List<UserEntity> getUserDeputies(UUID id) {
    Optional<UserEntity> userEntityOptional = getUserById(id);
    if(userEntityOptional.isPresent()){
      UserEntity userEntity = userEntityOptional.get();

      return userEntity.getDeputies().stream().collect(Collectors.toList());
    }

    throw new UserNotFoundException(id.toString());
  }

  @Override
  public List<UserEntity> getCompanyUsers(UUID companyId) {
    Optional<CompanyEntity> companyEntityOptional = this.companyRepository.getById(companyId);
    if(companyEntityOptional.isPresent()){
      CompanyEntity companyEntity = companyEntityOptional.get();
      return this.userRepository.getUserListByCompanyId(companyEntity.getId());
    }
    throw new UserNotFoundException(companyId.toString());
  }

  @Override
  public List<UserEntity> getAllUserListByDepartmentId(UUID id) {
    return this.userRepository.getUserListByDepartmentId(id);
  }

  @Override
  public List<UserEntity> getUserListByIdList(Set<UUID> idList) {
    return this.userRepository.getUserListByIdList(idList);
  }

  @Override
  public List<UserDashboardMenuEntity> getUserDashboardMenuListByUserId(String appIdentity, UUID userId) {
    Optional<UserEntity> userEntityOptional = getUserById(userId);
    if(userEntityOptional.isPresent()){
      UserEntity userEntity = userEntityOptional.get();

       return this.userDashboardMenuRepository.getByUserId(userEntity.getId(), appIdentity);
    }
    return new ArrayList<>();
  }

  @Override
  public List<UserDashboardMenuEntity> saveUserDashboardMenuListByUserId(String appIdentity, UUID userId, List<UserDashboardMenuEntity> list) {
    Optional<UserEntity> userEntityOptional = getUserById(userId);
    if(userEntityOptional.isPresent()){
      UserEntity userEntity = userEntityOptional.get();

      userDashboardMenuRepository.deleteByUserId(userId, appIdentity);

      for(UserDashboardMenuEntity userDashboardMenuEntity: list){
        userDashboardMenuEntity.setUserId(userEntity.getId());
        userDashboardMenuEntity.setAppId(appIdentity);
        userDashboardMenuRepository.create(userDashboardMenuEntity);
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
      List<DepartmentEntity> departments = departmentRepository.getListByIdCompanyId(companyEntity.getId());
      List<UserGroupEntity> userGroups = userGroupRepository.getListByIdCompanyId(companyEntity.getId());
      List<CompanyWorkflowTypeOcrSettingPresetEntity> ocrPresetSettings = this.workflowTypeOcrSettingPresetRepository.getByCompanyId(userEntity.getCompanyId());
      List<UserDashboardMenuEntity> userDashboardMenus = this.userDashboardMenuRepository.getByUserId(userEntity.getId(), appId);
      List<CompanyWorkflowTypeControllerEntity> companyWorkflowTypeControllerList =
              this.companyWorkflowTypeControllerRepository.getByCompanyId(companyEntity.getId());

      ProfileResponse profileResponse = new ProfileResponse(userEntity,
              companyEntity,
              departments,
              userGroups,
              ocrPresetSettings,
              userDashboardMenus,
              companyWorkflowTypeControllerList,
              tokenEntity.getRefreshToken());

      return Optional.of(profileResponse);
    }
    throw new UserNotFoundException(userEntity.getId().toString());
  }

  private UserEntity setPasswordHash(UserEntity model, String password) {

    String salt = passwordHashGenerator.produceSalt();
    String passwordHash = passwordHashGenerator.produceHash(password, salt);
    model.setPasswordHash(passwordHash);
    model.setPasswordSalt(salt);

    return model;
  }
}
