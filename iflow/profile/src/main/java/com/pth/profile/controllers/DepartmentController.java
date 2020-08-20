package com.pth.profile.controllers;

import com.pth.common.contants.ApiUrlConstants;
import com.pth.common.edo.*;
import com.pth.profile.entities.DepartmentEntity;
import com.pth.profile.entities.UserEntity;
import com.pth.profile.mapper.IDepartmentMapper;
import com.pth.profile.mapper.IUserMapper;
import com.pth.profile.services.data.IDepartmentService;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Validated
@Secured(SecurityRule.IS_AUTHENTICATED)
@Controller(ApiUrlConstants.ProfileUrlConstants.API001_CORE001_DEPARTMENT)
public class DepartmentController {

  private final IDepartmentService departmentService;
  private final IUsersService userService;
  private final IDepartmentMapper departmentMapper;
  private final IUserMapper userMapper;

  public DepartmentController(final IDepartmentService departmentService,
                              final IUsersService userService,
                              IDepartmentMapper departmentMapper,
                              IUserMapper userMapper) {

    this.departmentService = departmentService;
    this.userService = userService;
    this.departmentMapper = departmentMapper;
    this.userMapper = userMapper;
  }

  
  @Get(value = ApiUrlConstants.ProfileUrlConstants.DEPARTMENT_READ_BY_IDENTITY)
  public HttpResponse<DepartmentEdo> readDepartment(final String identity) throws Exception {

    final Optional<DepartmentEntity> modelOptional = this.departmentService.getByIdentity(identity);

    if(modelOptional.isPresent()){
      return HttpResponse.ok(this.departmentMapper.toEdo(modelOptional.get()));
    }
    return HttpResponse.notFound();
  }

  @Post(value = ApiUrlConstants.ProfileUrlConstants.DEPARTMENT_SAVE)
  public HttpResponse<DepartmentEdo> saveDepartment(@Body @Valid final DepartmentEdo edo) throws Exception {

    final Optional<DepartmentEntity> modelOptional = this.departmentService.save(this.departmentMapper.fromEdo(edo));

    if(modelOptional.isPresent()){
      return HttpResponse.ok(this.departmentMapper.toEdo(modelOptional.get()));
    }
    return HttpResponse.notFound();
  }

  
  @Post(value = ApiUrlConstants.ProfileUrlConstants.DEPARTMENT_DELETE)
  public void deleteDepartment(@Body @Valid final DepartmentEdo edo) throws Exception {

    this.departmentService.delete(this.departmentMapper.fromEdo(edo));

  }

  
  @Post(value = ApiUrlConstants.ProfileUrlConstants.DEPARTMENT_READ_LIST)
  public HttpResponse<DepartmentListEdo> readDepartmentList(@Body @Valid final IdentityListEdo idList) throws Exception {

    final List<DepartmentEntity> modelList = idList.getIdentityList().isEmpty() ? new ArrayList<>()
        : this.departmentService.getListByIdentityList(idList.getIdentityList());

    return HttpResponse.ok(new DepartmentListEdo(this.departmentMapper.toEdoList(modelList)));
  }

  
  @Get(value = ApiUrlConstants.ProfileUrlConstants.DEPARTMENT_READ_LIST_BY_COMPANYIDENTITY)
  public HttpResponse<DepartmentListEdo> readDepartmentListByCompany(final String companyidentity) throws Exception {

    final List<DepartmentEntity> modelList = this.departmentService.getListByIdCompanyIdentity(companyidentity);

    return HttpResponse.ok(new DepartmentListEdo(this.departmentMapper.toEdoList(modelList)));
  }

  
  @Get(value = ApiUrlConstants.ProfileUrlConstants.DEPARTMENT_READ_ALLUSERLIST_BY_DEPARTMENTIDENTITY)
  public HttpResponse<UserListEdo> readAllUserListByDepartmentGroup(final String identity) throws Exception {

    final List<UserEntity> modelList = this.userService.getAllUserIdentityListByDepartmentIdentity(identity);

    return HttpResponse.ok(new UserListEdo(this.userMapper.toEdoList(modelList)));
  }

  
  @Get(value = ApiUrlConstants.ProfileUrlConstants.DEPARTMENT_GET_MANAGER)
  public HttpResponse<UserEdo> getDepartmentGroupManager(final String identity) throws Exception {

    final UserEntity model = this.departmentService.getDepartmentManager(identity);

    return HttpResponse.ok(this.userMapper.toEdo(model));
  }

  
  @Get(value = ApiUrlConstants.ProfileUrlConstants.DEPARTMENT_GET_DEPUTY)
  public HttpResponse<UserEdo> getDepartmentGroupDeputy(final String identity) throws Exception {

    final UserEntity model = this.departmentService.getDepartmentDeputy(identity);

    return HttpResponse.ok(this.userMapper.toEdo(model));
  }

}
