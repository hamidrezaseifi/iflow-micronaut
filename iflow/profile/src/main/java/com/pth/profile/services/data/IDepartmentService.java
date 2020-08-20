package com.pth.profile.services.data;

import com.pth.profile.entities.DepartmentEntity;
import com.pth.profile.entities.UserEntity;

import java.util.Collection;
import java.util.List;
import java.util.Optional;


public interface IDepartmentService {

  Optional<DepartmentEntity> save(DepartmentEntity model);

  void delete(DepartmentEntity model);

  Optional<DepartmentEntity> getByIdentity(final String identity);

  List<DepartmentEntity> getListByIdentityList(final Collection<String> idList);

  List<DepartmentEntity> getListByIdCompanyIdentity(final String identity);

  UserEntity getDepartmentManager(final String identity);

  UserEntity getDepartmentDeputy(final String identity);

}
