package com.pth.profile.services.data;

import com.pth.common.edo.DepartmentEdo;
import com.pth.profile.entities.DepartmentEntity;
import com.pth.profile.entities.UserEntity;
import com.pth.common.mapping.IModelEdoMapper;

import java.util.Collection;
import java.util.List;


public interface IDepartmentService extends IModelEdoMapper<DepartmentEntity, DepartmentEdo> {

  DepartmentEntity save(DepartmentEntity model);

  void delete(DepartmentEntity model);

  DepartmentEntity getByIdentity(final String identity);

  List<DepartmentEntity> getListByIdentityList(final Collection<String> idList);

  List<DepartmentEntity> getListByIdCompanyIdentity(final String identity);

  UserEntity getDepartmentManager(final String identity);

  UserEntity getDepartmentDeputy(final String identity);

}
