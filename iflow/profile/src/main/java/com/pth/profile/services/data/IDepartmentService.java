package com.pth.profile.services.data;

import com.pth.profile.entities.DepartmentEntity;
import com.pth.profile.entities.UserEntity;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface IDepartmentService {

  Optional<DepartmentEntity> create(DepartmentEntity model);

  Optional<DepartmentEntity> save(DepartmentEntity model);

  void delete(DepartmentEntity model);

  Optional<DepartmentEntity> getById(final UUID id);

  List<DepartmentEntity> getListByIdentityList(final Collection<String> idList);

  List<DepartmentEntity> getListByIdCompanyId(final UUID id);

  Optional<UserEntity> getDepartmentManager(final UUID id);

  Optional<UserEntity> getDepartmentDeputy(final UUID id);

}
