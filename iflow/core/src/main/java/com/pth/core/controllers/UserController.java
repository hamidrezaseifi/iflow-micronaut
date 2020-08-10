package com.pth.core.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.pth.iflow.common.annotations.IflowGetRequestMapping;
import com.pth.iflow.common.annotations.IflowPostRequestMapping;
import com.pth.iflow.common.controllers.helper.ControllerHelper;
import com.pth.iflow.common.models.edo.DepartmentListEdo;
import com.pth.iflow.common.models.edo.ProfileResponseEdo;
import com.pth.iflow.common.models.edo.UserDashboardMenuEdo;
import com.pth.iflow.common.models.edo.UserDashboardMenuListEdo;
import com.pth.iflow.common.models.edo.UserEdo;
import com.pth.iflow.common.models.edo.UserGroupListEdo;
import com.pth.iflow.common.models.edo.UserListEdo;
import com.pth.iflow.common.rest.IflowRestPaths;
import com.pth.iflow.core.model.ProfileResponse;
import com.pth.iflow.core.model.entity.DepartmentEntity;
import com.pth.iflow.core.model.entity.UserDashboardMenuEntity;
import com.pth.iflow.core.model.entity.UserEntity;
import com.pth.iflow.core.model.entity.UserGroupEntity;
import com.pth.iflow.core.service.interfaces.IDepartmentService;
import com.pth.iflow.core.service.interfaces.IUserGroupService;
import com.pth.iflow.core.service.interfaces.IUsersService;

@RestController
@RequestMapping
public class UserController {

  final IUsersService usersService;
  final IUserGroupService userGroupService;
  final IDepartmentService departmentService;

  public UserController(@Autowired final IUsersService usersService, @Autowired final IUserGroupService userGroupService,
      @Autowired final IDepartmentService departmentService) {

    this.usersService = usersService;
    this.userGroupService = userGroupService;
    this.departmentService = departmentService;
  }

  @ResponseStatus(HttpStatus.ACCEPTED)
  @IflowPostRequestMapping(path = IflowRestPaths.CoreModule.USER_SAVE)
  public ResponseEntity<UserEdo> saveUser(@RequestBody final UserEdo userEdo, final HttpServletRequest request) throws Exception {

    final UserEntity user = this.usersService.save(this.usersService.fromEdo(userEdo));

    return ControllerHelper.createResponseEntity(request, this.usersService.toEdo(user), HttpStatus.ACCEPTED);
  }

  @ResponseStatus(HttpStatus.ACCEPTED)
  @IflowPostRequestMapping(path = IflowRestPaths.CoreModule.USER_DELETE)
  public void deleteUser(@RequestBody final UserEdo userEdo, final HttpServletRequest request) throws Exception {

    this.usersService.delete(this.usersService.fromEdo(userEdo));

  }

  @ResponseStatus(HttpStatus.OK)
  @IflowGetRequestMapping(path = IflowRestPaths.CoreModule.USER_READ_BY_IDENTITY)
  public ResponseEntity<UserEdo>
      readUserByIdentity(@PathVariable(name = "identity") final String identity, final HttpServletRequest request)
          throws Exception {

    final UserEntity user = this.usersService.getUserByIdentity(identity);

    return ControllerHelper.createResponseEntity(request, this.usersService.toEdo(user), HttpStatus.OK);
  }

  @ResponseStatus(HttpStatus.OK)
  @IflowGetRequestMapping(path = IflowRestPaths.CoreModule.USER_USERGROUPS_LIST_BY_IDENTITY)
  public ResponseEntity<UserGroupListEdo> readUserGroups(@PathVariable(name = "identity") final String identity,
      final HttpServletRequest request) throws Exception {

    final List<UserGroupEntity> groups = this.usersService.getUserGroups(identity);

    return ControllerHelper
        .createResponseEntity(request, new UserGroupListEdo(this.userGroupService.toEdoList(groups)),
            HttpStatus.OK);
  }

  @ResponseStatus(HttpStatus.OK)
  @IflowGetRequestMapping(path = IflowRestPaths.CoreModule.USER_DEPARTMENTS_LIST_BY_IDENTITY)
  public ResponseEntity<DepartmentListEdo> readUserDepartments(@PathVariable(name = "identity") final String identity,
      final HttpServletRequest request) throws Exception {

    final List<DepartmentEntity> list = this.usersService.getUserDepartments(identity);

    return ControllerHelper
        .createResponseEntity(request, new DepartmentListEdo(this.departmentService.toEdoList(list)),
            HttpStatus.OK);
  }

  @ResponseStatus(HttpStatus.OK)
  @IflowGetRequestMapping(path = IflowRestPaths.CoreModule.USER_DEPUTIES_LIST_BY_IDENTITY)
  public ResponseEntity<UserListEdo> readUserDeputies(@PathVariable(name = "identity") final String identity,
      final HttpServletRequest request) throws Exception {

    final List<UserEntity> list = this.usersService.getUserDeputies(identity);
    final UserListEdo edo = new UserListEdo();
    edo.setUsers(this.usersService.toEdoList(list));
    return ControllerHelper.createResponseEntity(request, edo, HttpStatus.OK);
  }

  @ResponseStatus(HttpStatus.OK)
  @IflowGetRequestMapping(path = IflowRestPaths.CoreModule.USER_USER_LIST_BY_COMPANYIDENTITY)
  public ResponseEntity<UserListEdo> readCompanyUsers(@PathVariable(name = "companyidentity") final String companyidentity,
      final HttpServletRequest request) throws Exception {

    final List<UserEntity> list = this.usersService.getCompanyUsers(companyidentity);
    final UserListEdo edo = new UserListEdo();
    edo.setUsers(this.usersService.toEdoList(list));
    return ControllerHelper.createResponseEntity(request, edo, HttpStatus.OK);
  }

  @ResponseStatus(HttpStatus.OK)
  @IflowGetRequestMapping(path = IflowRestPaths.CoreModule.USER_USER_LIST_BY_DEPARTMENTIDENTITY)
  public ResponseEntity<UserListEdo> readDepartmentUsers(@PathVariable(name = "identity") final String identity,
      final HttpServletRequest request) throws Exception {

    final List<UserEntity> list = this.usersService.getAllUserIdentityListByDepartmentIdentity(identity);
    final UserListEdo edo = new UserListEdo();
    edo.setUsers(this.usersService.toEdoList(list));
    return ControllerHelper.createResponseEntity(request, edo, HttpStatus.OK);
  }

  @ResponseStatus(HttpStatus.OK)
  @IflowGetRequestMapping(path = IflowRestPaths.CoreModule.USERPROFILE_READ_BY_EMAIL)
  public ResponseEntity<ProfileResponseEdo> readUserProfileByEmail(@PathVariable(name = "appIdentity") final String appIdentity,
      @PathVariable(name = "email") final String email,
      final HttpServletRequest request) throws Exception {

    final ProfileResponse profile = this.usersService.getProfileResponseByEmail(appIdentity, email);

    return ControllerHelper.createResponseEntity(request, this.usersService.toProfileResponseEdo(profile), HttpStatus.OK);
  }

  @ResponseStatus(HttpStatus.OK)
  @IflowGetRequestMapping(path = IflowRestPaths.CoreModule.USERPROFILE_READ_BY_USERIDENTITY)
  public ResponseEntity<ProfileResponseEdo> readUserProfileByIdentity(@PathVariable(name = "appIdentity") final String appIdentity,
      @PathVariable(name = "identity") final String identity,
      final HttpServletRequest request) throws Exception {

    final ProfileResponse profile = this.usersService.getProfileResponseByIdentity(appIdentity, identity);

    return ControllerHelper.createResponseEntity(request, this.usersService.toProfileResponseEdo(profile), HttpStatus.OK);
  }

  @ResponseStatus(HttpStatus.OK)
  @IflowGetRequestMapping(path = IflowRestPaths.CoreModule.USERDASHBOARDMENU_READ_BY_USERIDENTITY)
  public ResponseEntity<UserDashboardMenuListEdo> readUserDashboardMenuByIdentity(
      @PathVariable(name = "appIdentity") final String appIdentity, @PathVariable(name = "userIdentity") final String userIdentity,
      final HttpServletRequest request) throws Exception {

    final List<UserDashboardMenuEntity> list = this.usersService.getUserDashboardMenuListByUserIdentity(appIdentity, userIdentity);

    final List<UserDashboardMenuEdo> edoList = this.usersService.toUserDashboardMenuEdoList(list);

    return ControllerHelper.createResponseEntity(request, new UserDashboardMenuListEdo(edoList), HttpStatus.OK);
  }

  @ResponseStatus(HttpStatus.CREATED)
  @IflowPostRequestMapping(path = IflowRestPaths.CoreModule.USERDASHBOARDMENU_SAVE_BY_USERIDENTITY)
  public ResponseEntity<UserDashboardMenuListEdo>
      saveUserDashboardMenuByIdentity(@RequestBody final UserDashboardMenuListEdo requestedEdoList, @PathVariable(name = "appIdentity") final String appIdentity, @PathVariable(name = "userIdentity") final String userIdentity, final HttpServletRequest request)
          throws Exception {

    final List<UserDashboardMenuEntity> requestedModelList = this.usersService
        .fromUserDashboardMenuEdoList(requestedEdoList.getUserDashboardMenus());

    final List<UserDashboardMenuEntity> list = this.usersService
        .saveUserDashboardMenuListByUserIdentity(appIdentity, userIdentity, requestedModelList);

    final List<UserDashboardMenuEdo> edoList = this.usersService.toUserDashboardMenuEdoList(list);

    return ControllerHelper.createResponseEntity(request, new UserDashboardMenuListEdo(edoList), HttpStatus.CREATED);
  }

}
