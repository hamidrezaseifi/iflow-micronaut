package com.pth.core.controllers;

import java.util.ArrayList;
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
import com.pth.iflow.common.models.edo.IdentityListEdo;
import com.pth.iflow.common.models.edo.UserGroupEdo;
import com.pth.iflow.common.models.edo.UserGroupListEdo;
import com.pth.iflow.common.rest.IflowRestPaths;
import com.pth.iflow.core.model.entity.UserGroupEntity;
import com.pth.iflow.core.service.interfaces.IUserGroupService;

@RestController
@RequestMapping
public class UserGroupController {

  final IUserGroupService userGroupService;

  public UserGroupController(@Autowired final IUserGroupService userGroupService) {
    this.userGroupService = userGroupService;
  }

  @ResponseStatus(HttpStatus.OK)
  @IflowGetRequestMapping(path = IflowRestPaths.CoreModule.USERGROUP_READ_BY_IDENTITY)
  public ResponseEntity<UserGroupEdo> readUserGroup(@PathVariable(name = "identity") final String identity,
      final HttpServletRequest request) throws Exception {

    final UserGroupEntity model = this.userGroupService.getByIdentity(identity);

    return ControllerHelper.createResponseEntity(request, this.userGroupService.toEdo(model), HttpStatus.OK);
  }

  @ResponseStatus(HttpStatus.OK)
  @IflowPostRequestMapping(path = IflowRestPaths.CoreModule.USERGROUP_READ_LIST)
  public ResponseEntity<UserGroupListEdo> readUserGroupList(@RequestBody final IdentityListEdo idList,
      final HttpServletRequest request) throws Exception {

    final List<UserGroupEntity> modelList = idList.getIdentityList().isEmpty() ? new ArrayList<>()
        : this.userGroupService.getListByIdentityList(idList.getIdentityList());

    return ControllerHelper.createResponseEntity(request, new UserGroupListEdo(this.userGroupService.toEdoList(modelList)),
        HttpStatus.OK);
  }

  @ResponseStatus(HttpStatus.OK)
  @IflowGetRequestMapping(path = IflowRestPaths.CoreModule.USERGROUP_READ_LIST_BY_COMPANYIDENTITY)
  public ResponseEntity<UserGroupListEdo> readUserGroupListByCompany(
      @PathVariable(name = "companyidentity") final String companyidentity, final HttpServletRequest request) throws Exception {

    final List<UserGroupEntity> modelList = this.userGroupService.getListByIdCompanyIdentity(companyidentity);

    return ControllerHelper.createResponseEntity(request, new UserGroupListEdo(this.userGroupService.toEdoList(modelList)),
        HttpStatus.OK);
  }

}
