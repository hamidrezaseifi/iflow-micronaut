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
import java.util.UUID;


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

  
  @Get(value = ApiUrlConstants.ProfileUrlConstants.DEPARTMENT_READ_BY_ID)
  public HttpResponse<DepartmentEdo> readDepartment(final UUID id) throws Exception {

    final Optional<DepartmentEntity> modelOptional = this.departmentService.getById(id);

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

  
  @Get(value = ApiUrlConstants.ProfileUrlConstants.DEPARTMENT_READ_LIST_BY_COMPANYID)
  public HttpResponse<DepartmentListEdo> readDepartmentListByCompany(final UUID companyid) throws Exception {

    final List<DepartmentEntity> modelList = this.departmentService.getListByIdCompanyId(companyid);

    return HttpResponse.ok(new DepartmentListEdo(this.departmentMapper.toEdoList(modelList)));
  }

  
  @Get(value = ApiUrlConstants.ProfileUrlConstants.DEPARTMENT_READ_ALLUSERLIST_BY_DEPARTMENTID)
  public HttpResponse<UserListEdo> readAllUserListByDepartmentGroup(final UUID id) throws Exception {

    final List<UserEntity> modelList = this.userService.getAllUserListByDepartmentId(id);

    return HttpResponse.ok(new UserListEdo(this.userMapper.toEdoList(modelList)));
  }

  
  @Get(value = ApiUrlConstants.ProfileUrlConstants.DEPARTMENT_GET_MANAGER)
  public HttpResponse<UserEdo> getDepartmentGroupManager(final UUID id) throws Exception {

    final Optional<UserEntity> modelOptional = this.departmentService.getDepartmentManager(id);

    if(modelOptional.isPresent()){
      return HttpResponse.ok(this.userMapper.toEdo(modelOptional.get()));
    }
    return HttpResponse.notFound();
  }

  
  @Get(value = ApiUrlConstants.ProfileUrlConstants.DEPARTMENT_GET_DEPUTY)
  public HttpResponse<UserEdo> getDepartmentGroupDeputy(final UUID id) throws Exception {

    final Optional<UserEntity> modelOptional = this.departmentService.getDepartmentDeputy(id);


    if(modelOptional.isPresent()){
      return HttpResponse.ok(this.userMapper.toEdo(modelOptional.get()));
    }
    return HttpResponse.notFound();
  }

}
