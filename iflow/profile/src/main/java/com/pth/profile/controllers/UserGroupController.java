package com.pth.profile.controllers;

import com.pth.common.contants.ApiUrlConstants;
import com.pth.common.edo.IdentityListEdo;
import com.pth.common.edo.UserGroupEdo;
import com.pth.common.edo.UserGroupListEdo;
import com.pth.profile.entities.UserGroupEntity;
import com.pth.profile.services.data.IUserGroupService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import io.micronaut.validation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Validated
@Secured(SecurityRule.IS_AUTHENTICATED)
@Controller(ApiUrlConstants.ProfileUrlConstants.API001_CORE001_USERGROUP)
public class UserGroupController {

  final IUserGroupService userGroupService;

  public UserGroupController(final IUserGroupService userGroupService) {
    this.userGroupService = userGroupService;
  }

  
  @Get(value = ApiUrlConstants.ProfileUrlConstants.USERGROUP_READ_BY_IDENTITY)
  public HttpResponse<UserGroupEdo> readUserGroup(final String identity) throws Exception {

    final UserGroupEntity model = this.userGroupService.getByIdentity(identity);

    return HttpResponse.ok(this.userGroupService.toEdo(model));
  }

  
  @Post(value = ApiUrlConstants.ProfileUrlConstants.USERGROUP_READ_LIST)
  public HttpResponse<UserGroupListEdo> readUserGroupList(@Body @Valid final IdentityListEdo idList) throws Exception {

    final List<UserGroupEntity> modelList = idList.getIdentityList().isEmpty() ? new ArrayList<>()
        : this.userGroupService.getListByIdentityList(idList.getIdentityList());

    return HttpResponse.ok(new UserGroupListEdo(this.userGroupService.toEdoList(modelList)));
  }

  
  @Get(value = ApiUrlConstants.ProfileUrlConstants.USERGROUP_READ_LIST_BY_COMPANYIDENTITY)
  public HttpResponse<UserGroupListEdo> readUserGroupListByCompany(final String companyidentity) throws Exception {

    final List<UserGroupEntity> modelList = this.userGroupService.getListByIdCompanyIdentity(companyidentity);

    return HttpResponse.ok(new UserGroupListEdo(this.userGroupService.toEdoList(modelList)));
  }

}
