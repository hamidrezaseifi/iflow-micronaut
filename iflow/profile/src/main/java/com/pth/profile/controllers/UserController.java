package com.pth.profile.controllers;

import com.pth.common.contants.ApiUrlConstants;
import com.pth.common.edo.*;
import com.pth.profile.entities.DepartmentEntity;
import com.pth.profile.entities.UserDashboardMenuEntity;
import com.pth.profile.entities.UserEntity;
import com.pth.profile.entities.UserGroupEntity;
import com.pth.profile.mapper.IDepartmentMapper;
import com.pth.profile.mapper.IProfileResponseMapper;
import com.pth.profile.mapper.IUserDashboardMenuMapper;
import com.pth.profile.mapper.IUserMapper;
import com.pth.profile.models.ProfileResponse;
import com.pth.profile.services.data.IDepartmentService;
import com.pth.profile.services.data.IUserGroupService;
import com.pth.profile.services.data.IUsersService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import io.micronaut.validation.Validated;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Validated
@Secured(SecurityRule.IS_AUTHENTICATED)
@Controller(ApiUrlConstants.ProfileUrlConstants.API001_CORE001_USERS)
public class UserController {

  private final IUsersService usersService;
  private final IUserGroupService userGroupService;
  private final IDepartmentService departmentService;
  private final IUserMapper userMapper;
  private final IDepartmentMapper departmentMapper;
  private final IProfileResponseMapper profileResponseMapper;
  private final IUserDashboardMenuMapper userDashboardMenuMapper;

  public UserController(IUsersService usersService,
                        IUserGroupService userGroupService,
                        IDepartmentService departmentService,
                        IUserMapper userMapper,
                        IDepartmentMapper departmentMapper,
                        IProfileResponseMapper profileResponseMapper,
                        IUserDashboardMenuMapper userDashboardMenuMapper) {

    this.usersService = usersService;
    this.userGroupService = userGroupService;
    this.departmentService = departmentService;
    this.userMapper = userMapper;
    this.departmentMapper = departmentMapper;
    this.profileResponseMapper = profileResponseMapper;
    this.userDashboardMenuMapper = userDashboardMenuMapper;
  }

  @Post(value = ApiUrlConstants.ProfileUrlConstants.USER_SAVE)
  public HttpResponse<UserEdo> saveUser(@Body @Valid final UserEdo userEdo) throws Exception {

    final Optional<UserEntity> userOptional = this.usersService.save(this.userMapper.fromEdo(userEdo));

    if(userOptional.isPresent()){
      return HttpResponse.created(this.userMapper.toEdo(userOptional.get()));
    }
    return HttpResponse.badRequest();
  }
  
  @Post(value = ApiUrlConstants.ProfileUrlConstants.USER_DELETE)
  public HttpResponse<?> deleteUser(@Body @Valid final UserEdo userEdo) throws Exception {

    this.usersService.delete(this.userMapper.fromEdo(userEdo));

    return HttpResponse.accepted();
  }

  
  @Get(value = ApiUrlConstants.ProfileUrlConstants.USER_READ_BY_IDENTITY)
  public HttpResponse<UserEdo>
      readUserByIdentity(final String identity)
          throws Exception {

    final Optional<UserEntity> userOptional = this.usersService.getUserByIdentity(identity);

    if(userOptional.isPresent()){
      return HttpResponse.ok(this.userMapper.toEdo(userOptional.get()));
    }
    return HttpResponse.badRequest();
  }

  
  @Get(value = ApiUrlConstants.ProfileUrlConstants.USER_USERGROUPS_LIST_BY_IDENTITY)
  public HttpResponse<UserGroupListEdo> readUserGroups(final String identity) throws Exception {

    final List<UserGroupEntity> groups = this.usersService.getUserGroups(identity);

    return HttpResponse.ok(new UserGroupListEdo(this.userGroupService.toEdoList(groups)));
  }

  
  @Get(value = ApiUrlConstants.ProfileUrlConstants.USER_DEPARTMENTS_LIST_BY_IDENTITY)
  public HttpResponse<DepartmentListEdo> readUserDepartments(final String identity) throws Exception {

    final List<DepartmentEntity> list = this.usersService.getUserDepartments(identity);

    return HttpResponse.ok(new DepartmentListEdo(this.departmentMapper.toEdoList(list)));
  }

  
  @Get(value = ApiUrlConstants.ProfileUrlConstants.USER_DEPUTIES_LIST_BY_IDENTITY)
  public HttpResponse<UserListEdo> readUserDeputies(final String identity) throws Exception {

    final List<UserEntity> list = this.usersService.getUserDeputies(identity);
    final UserListEdo edo = new UserListEdo();
    edo.setUsers(this.userMapper.toEdoList(list));
    return HttpResponse.ok(edo);
  }

  
  @Get(value = ApiUrlConstants.ProfileUrlConstants.USER_USER_LIST_BY_COMPANYIDENTITY)
  public HttpResponse<UserListEdo> readCompanyUsers(final String companyidentity) throws Exception {

    final List<UserEntity> list = this.usersService.getCompanyUsers(companyidentity);
    final UserListEdo edo = new UserListEdo();
    edo.setUsers(this.userMapper.toEdoList(list));
    return HttpResponse.ok(edo);
  }

  
  @Get(value = ApiUrlConstants.ProfileUrlConstants.USER_USER_LIST_BY_DEPARTMENTIDENTITY)
  public HttpResponse<UserListEdo> readDepartmentUsers(final String identity) throws Exception {

    final List<UserEntity> list = this.usersService.getAllUserIdentityListByDepartmentIdentity(identity);
    final UserListEdo edo = new UserListEdo();
    edo.setUsers(this.userMapper.toEdoList(list));
    return HttpResponse.ok(edo);
  }

  
  @Get(value = ApiUrlConstants.ProfileUrlConstants.USERPROFILE_READ_BY_EMAIL)
  public HttpResponse<ProfileResponseEdo> readUserProfileByEmail(final String appIdentity,
                                                                 final String email) throws Exception {

    final Optional<ProfileResponse> profileResponseOptional = this.usersService.getProfileResponseByEmail(appIdentity, email);

    if(profileResponseOptional.isPresent()){
      return HttpResponse.ok(this.profileResponseMapper.toEdo(profileResponseOptional.get()));
    }
    return HttpResponse.notFound();
  }

  
  @Get(value = ApiUrlConstants.ProfileUrlConstants.USERPROFILE_READ_BY_USERIDENTITY)
  public HttpResponse<ProfileResponseEdo> readUserProfileByIdentity(final String appIdentity,
                                                                    final String identity) throws Exception {

    final Optional<ProfileResponse> profileResponseOptional = this.usersService.getProfileResponseByIdentity(appIdentity, identity);

    if(profileResponseOptional.isPresent()){
      return HttpResponse.ok(this.profileResponseMapper.toEdo(profileResponseOptional.get()));
    }
    return HttpResponse.notFound();
  }

  
  @Get(value = ApiUrlConstants.ProfileUrlConstants.USERDASHBOARDMENU_READ_BY_USERIDENTITY)
  public HttpResponse<UserDashboardMenuListEdo>
  readUserDashboardMenuByIdentity(final String appIdentity, final String userIdentity) throws Exception {

    final List<UserDashboardMenuEntity> list =
            this.usersService.getUserDashboardMenuListByUserIdentity(appIdentity, userIdentity);

    final List<UserDashboardMenuEdo> edoList = this.userDashboardMenuMapper.toEdoList(list);

    return HttpResponse.ok(new UserDashboardMenuListEdo(edoList));
  }

  @Post(value = ApiUrlConstants.ProfileUrlConstants.USERDASHBOARDMENU_SAVE_BY_USERIDENTITY)
  public HttpResponse<UserDashboardMenuListEdo>
      saveUserDashboardMenuByIdentity(@Body @Valid final UserDashboardMenuListEdo requestedEdoList,
                                      final String appIdentity,
                                      final String userIdentity)
          throws Exception {

    final List<UserDashboardMenuEntity> requestedModelList = this.userDashboardMenuMapper
        .fromEdoList(requestedEdoList.getUserDashboardMenus());

    final List<UserDashboardMenuEntity> list = this.usersService
        .saveUserDashboardMenuListByUserIdentity(appIdentity, userIdentity, requestedModelList);

    final List<UserDashboardMenuEdo> edoList = this.userDashboardMenuMapper.toEdoList(list);

    return HttpResponse.created(new UserDashboardMenuListEdo(edoList));
  }

}
