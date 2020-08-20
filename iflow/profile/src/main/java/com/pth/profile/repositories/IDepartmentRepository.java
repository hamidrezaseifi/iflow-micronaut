package com.pth.profile.repositories;

import com.pth.common.repositories.IEntityRepository;
import com.pth.profile.entities.DepartmentEntity;
import com.pth.profile.entities.UserEntity;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface IDepartmentRepository extends IEntityRepository<DepartmentEntity> {
  Optional<DepartmentEntity> getByIdentity(final String identity);

  List<DepartmentEntity> getListByIdentityList(Collection<String> identityList);

  List<DepartmentEntity> getListByIdCompanyIdentity(final String identity);

}
