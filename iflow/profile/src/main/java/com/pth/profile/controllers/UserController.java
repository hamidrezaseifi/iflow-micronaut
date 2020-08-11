package com.pth.profile.controllers;

import com.pth.common.contants.ApiUrlConstants;
import com.pth.common.edo.*;
import com.pth.profile.entities.DepartmentEntity;
import com.pth.profile.entities.UserDashboardMenuEntity;
import com.pth.profile.entities.UserEntity;
import com.pth.profile.entities.UserGroupEntity;
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

@Validated
@Secured(SecurityRule.IS_AUTHENTICATED)
@Controller(ApiUrlConstants.ProfileUrlConstants.API001_CORE001_USERS)
public class UserController {

  final IUsersService usersService;
  final IUserGroupService userGroupService;
  final IDepartmentService departmentService;

  public UserController(final IUsersService usersService, final IUserGroupService userGroupService,
      final IDepartmentService departmentService) {

    this.usersService = usersService;
    this.userGroupService = userGroupService;
    this.departmentService = departmentService;
  }

  @Post(value = ApiUrlConstants.ProfileUrlConstants.USER_SAVE)
  public HttpResponse<UserEdo> saveUser(@Body @Valid final UserEdo userEdo) throws Exception {

    final UserEntity user = this.usersService.save(this.usersService.fromEdo(userEdo));

    return HttpResponse.created(this.usersService.toEdo(user));
  }
  
  @Post(value = ApiUrlConstants.ProfileUrlConstants.USER_DELETE)
  public HttpResponse<?> deleteUser(@Body @Valid final UserEdo userEdo) throws Exception {

    this.usersService.delete(this.usersService.fromEdo(userEdo));

    return HttpResponse.accepted();
  }

  
  @Get(value = ApiUrlConstants.ProfileUrlConstants.USER_READ_BY_IDENTITY)
  public HttpResponse<UserEdo>
      readUserByIdentity(final String identity)
          throws Exception {

    final UserEntity user = this.usersService.getUserByIdentity(identity);

    return HttpResponse.ok(this.usersService.toEdo(user));
  }

  
  @Get(value = ApiUrlConstants.ProfileUrlConstants.USER_USERGROUPS_LIST_BY_IDENTITY)
  public HttpResponse<UserGroupListEdo> readUserGroups(final String identity) throws Exception {

    final List<UserGroupEntity> groups = this.usersService.getUserGroups(identity);

    return HttpResponse.ok(new UserGroupListEdo(this.userGroupService.toEdoList(groups)));
  }

  
  @Get(value = ApiUrlConstants.ProfileUrlConstants.USER_DEPARTMENTS_LIST_BY_IDENTITY)
  public HttpResponse<DepartmentListEdo> readUserDepartments(final String identity) throws Exception {

    final List<DepartmentEntity> list = this.usersService.getUserDepartments(identity);

    return HttpResponse.ok(new DepartmentListEdo(this.departmentService.toEdoList(list)));
  }

  
  @Get(value = ApiUrlConstants.ProfileUrlConstants.USER_DEPUTIES_LIST_BY_IDENTITY)
  public HttpResponse<UserListEdo> readUserDeputies(final String identity) throws Exception {

    final List<UserEntity> list = this.usersService.getUserDeputies(identity);
    final UserListEdo edo = new UserListEdo();
    edo.setUsers(this.usersService.toEdoList(list));
    return HttpResponse.ok(edo);
  }

  
  @Get(value = ApiUrlConstants.ProfileUrlConstants.USER_USER_LIST_BY_COMPANYIDENTITY)
  public HttpResponse<UserListEdo> readCompanyUsers(final String companyidentity) throws Exception {

    final List<UserEntity> list = this.usersService.getCompanyUsers(companyidentity);
    final UserListEdo edo = new UserListEdo();
    edo.setUsers(this.usersService.toEdoList(list));
    return HttpResponse.ok(edo);
  }

  
  @Get(value = ApiUrlConstants.ProfileUrlConstants.USER_USER_LIST_BY_DEPARTMENTIDENTITY)
  public HttpResponse<UserListEdo> readDepartmentUsers(final String identity) throws Exception {

    final List<UserEntity> list = this.usersService.getAllUserIdentityListByDepartmentIdentity(identity);
    final UserListEdo edo = new UserListEdo();
    edo.setUsers(this.usersService.toEdoList(list));
    return HttpResponse.ok(edo);
  }

  
  @Get(value = ApiUrlConstants.ProfileUrlConstants.USERPROFILE_READ_BY_EMAIL)
  public HttpResponse<ProfileResponseEdo> readUserProfileByEmail(final String appIdentity,
                                                                 final String email) throws Exception {

    final ProfileResponse profile = this.usersService.getProfileResponseByEmail(appIdentity, email);

    return HttpResponse.ok(this.usersService.toProfileResponseEdo(profile));
  }

  
  @Get(value = ApiUrlConstants.ProfileUrlConstants.USERPROFILE_READ_BY_USERIDENTITY)
  public HttpResponse<ProfileResponseEdo>
  readUserProfileByIdentity(final String appIdentity,
                            final String identity) throws Exception {

    final ProfileResponse profile = this.usersService.getProfileResponseByIdentity(appIdentity, identity);

    return HttpResponse.ok(this.usersService.toProfileResponseEdo(profile));
  }

  
  @Get(value = ApiUrlConstants.ProfileUrlConstants.USERDASHBOARDMENU_READ_BY_USERIDENTITY)
  public HttpResponse<UserDashboardMenuListEdo>
  readUserDashboardMenuByIdentity(final String appIdentity, final String userIdentity) throws Exception {

    final List<UserDashboardMenuEntity> list =
            this.usersService.getUserDashboardMenuListByUserIdentity(appIdentity, userIdentity);

    final List<UserDashboardMenuEdo> edoList = this.usersService.toUserDashboardMenuEdoList(list);

    return HttpResponse.ok(new UserDashboardMenuListEdo(edoList));
  }

  @Post(value = ApiUrlConstants.ProfileUrlConstants.USERDASHBOARDMENU_SAVE_BY_USERIDENTITY)
  public HttpResponse<UserDashboardMenuListEdo>
      saveUserDashboardMenuByIdentity(@Body @Valid final UserDashboardMenuListEdo requestedEdoList,
                                      final String appIdentity,
                                      final String userIdentity)
          throws Exception {

    final List<UserDashboardMenuEntity> requestedModelList = this.usersService
        .fromUserDashboardMenuEdoList(requestedEdoList.getUserDashboardMenus());

    final List<UserDashboardMenuEntity> list = this.usersService
        .saveUserDashboardMenuListByUserIdentity(appIdentity, userIdentity, requestedModelList);

    final List<UserDashboardMenuEdo> edoList = this.usersService.toUserDashboardMenuEdoList(list);

    return HttpResponse.created(new UserDashboardMenuListEdo(edoList));
  }

}
