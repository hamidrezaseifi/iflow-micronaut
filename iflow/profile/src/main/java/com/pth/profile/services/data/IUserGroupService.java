package com.pth.profile.services.data;

import com.pth.common.edo.UserGroupEdo;
import com.pth.profile.entities.UserGroupEntity;
import com.pth.common.mapping.IModelEdoMapper;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface IUserGroupService {

  Optional<UserGroupEntity> save(UserGroupEntity model);

  Optional<UserGroupEntity> getByIdentity(final String identity);

  void delete(UserGroupEntity model);

  List<UserGroupEntity> getListByIdentityList(final Collection<String> idList);

  List<UserGroupEntity> getListByIdCompanyIdentity(final String companyIdentity);
}
