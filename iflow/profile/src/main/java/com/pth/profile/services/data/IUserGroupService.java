package com.pth.profile.services.data;

import com.pth.common.edo.UserGroupEdo;
import com.pth.profile.entities.UserGroupEntity;
import com.pth.common.mapping.IModelEdoMapper;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IUserGroupService {

  Optional<UserGroupEntity> create(UserGroupEntity model);

  Optional<UserGroupEntity> save(UserGroupEntity model);

  Optional<UserGroupEntity> getById(final UUID id);

  void delete(UserGroupEntity model);

  List<UserGroupEntity> getListByIdentityList(final Collection<String> idList);

  List<UserGroupEntity> getListByIdCompanyId(final UUID companyId);
}
