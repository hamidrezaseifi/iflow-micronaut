package com.pth.profile.controllers;

import com.pth.common.contants.ApiUrlConstants;
import com.pth.common.edo.*;
import com.pth.profile.entities.DepartmentEntity;
import com.pth.profile.entities.UserDashboardMenuEntity;
import com.pth.profile.entities.UserEntity;
import com.pth.profile.entities.UserGroupEntity;
import com.pth.profile.mapper.*;
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
import java.util.UUID;

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
  private final IUserGroupMapper userGroupMapper;

  public UserController(IUsersService usersService,
                        IUserGroupService userGroupService,
                        IDepartmentService departmentService,
                        IUserMapper userMapper,
                        IDepartmentMapper departmentMapper,
                        IProfileResponseMapper profileResponseMapper,
                        IUserDashboardMenuMapper userDashboardMenuMapper,
                        IUserGroupMapper userGroupMapper) {

    this.usersService = usersService;
    this.userGroupService = userGroupService;
    this.departmentService = departmentService;
    this.userMapper = userMapper;
    this.departmentMapper = departmentMapper;
    this.profileResponseMapper = profileResponseMapper;
    this.userDashboardMenuMapper = userDashboardMenuMapper;
    this.userGroupMapper = userGroupMapper;
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

  
  @Get(value = ApiUrlConstants.ProfileUrlConstants.USER_READ_BY_ID)
  public HttpResponse<UserEdo> readUserByIdentity(final UUID id) throws Exception {

    final Optional<UserEntity> userOptional = this.usersService.getUserById(id);

    if(userOptional.isPresent()){
      return HttpResponse.ok(this.userMapper.toEdo(userOptional.get()));
    }
    return HttpResponse.badRequest();
  }

  
  @Get(value = ApiUrlConstants.ProfileUrlConstants.USER_USERGROUPS_LIST_BY_ID)
  public HttpResponse<UserGroupListEdo> readUserGroups(final UUID id) throws Exception {

    final List<UserGroupEntity> groups = this.usersService.getUserGroups(id);

    return HttpResponse.ok(new UserGroupListEdo(this.userGroupMapper.toEdoList(groups)));
  }

  
  @Get(value = ApiUrlConstants.ProfileUrlConstants.USER_DEPARTMENTS_LIST_BY_ID)
  public HttpResponse<DepartmentListEdo> readUserDepartments(final UUID id) throws Exception {

    final List<DepartmentEntity> list = this.usersService.getUserDepartments(id);

    return HttpResponse.ok(new DepartmentListEdo(this.departmentMapper.toEdoList(list)));
  }

  
  @Get(value = ApiUrlConstants.ProfileUrlConstants.USER_DEPUTIES_LIST_BY_ID)
  public HttpResponse<UserListEdo> readUserDeputies(final UUID id) throws Exception {

    final List<UserEntity> list = this.usersService.getUserDeputies(id);
    final UserListEdo edo = new UserListEdo();
    edo.setUsers(this.userMapper.toEdoList(list));
    return HttpResponse.ok(edo);
  }

  
  @Get(value = ApiUrlConstants.ProfileUrlConstants.USER_USER_LIST_BY_COMPANYID)
  public HttpResponse<UserListEdo> readCompanyUsers(final UUID companyid) throws Exception {

    final List<UserEntity> list = this.usersService.getCompanyUsers(companyid);
    final UserListEdo edo = new UserListEdo();
    edo.setUsers(this.userMapper.toEdoList(list));
    return HttpResponse.ok(edo);
  }

  
  @Get(value = ApiUrlConstants.ProfileUrlConstants.USER_USER_LIST_BY_DEPARTMENTID)
  public HttpResponse<UserListEdo> readDepartmentUsers(final UUID id) throws Exception {

    final List<UserEntity> list = this.usersService.getAllUserListByDepartmentId(id);
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

  @Get(value = ApiUrlConstants.ProfileUrlConstants.USERPROFILE_READ_BY_USERID)
  public HttpResponse<ProfileResponseEdo> readUserProfileById(final String appIdentity,
                                                                    final UUID id) throws Exception {

    final Optional<ProfileResponse> profileResponseOptional = this.usersService.getProfileResponseById(appIdentity, id);

    if(profileResponseOptional.isPresent()){
      return HttpResponse.ok(this.profileResponseMapper.toEdo(profileResponseOptional.get()));
    }
    return HttpResponse.notFound();
  }

  
  @Get(value = ApiUrlConstants.ProfileUrlConstants.USERDASHBOARDMENU_READ_BY_USERID)
  public HttpResponse<UserDashboardMenuListEdo>
      readUserDashboardMenuByIdentity(final String appIdentity, final UUID userId) throws Exception {

    final List<UserDashboardMenuEntity> list =
            this.usersService.getUserDashboardMenuListByUserId(appIdentity, userId);

    final List<UserDashboardMenuEdo> edoList = this.userDashboardMenuMapper.toEdoList(list);

    return HttpResponse.ok(new UserDashboardMenuListEdo(edoList));
  }

  @Post(value = ApiUrlConstants.ProfileUrlConstants.USERDASHBOARDMENU_SAVE_BY_USERID)
  public HttpResponse<UserDashboardMenuListEdo>
      saveUserDashboardMenuByIdentity(@Body @Valid final UserDashboardMenuListEdo requestedEdoList,
                                      final String appIdentity,
                                      final UUID userId)
          throws Exception {

    final List<UserDashboardMenuEntity> requestedModelList = this.userDashboardMenuMapper
        .fromEdoList(requestedEdoList.getUserDashboardMenus());

    final List<UserDashboardMenuEntity> list = this.usersService
        .saveUserDashboardMenuListByUserId(appIdentity, userId, requestedModelList);

    final List<UserDashboardMenuEdo> edoList = this.userDashboardMenuMapper.toEdoList(list);

    return HttpResponse.created(new UserDashboardMenuListEdo(edoList));
  }

}
