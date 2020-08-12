package com.pth.profile.services.data;

import com.pth.common.edo.UserGroupEdo;
import com.pth.profile.entities.UserGroupEntity;
import com.pth.common.mapping.IModelEdoMapper;

import java.util.Collection;
import java.util.List;

public interface IUserGroupService extends IModelEdoMapper<UserGroupEntity, UserGroupEdo> {

  UserGroupEntity save(UserGroupEntity model);

  UserGroupEntity getByIdentity(final String identity);

  List<UserGroupEntity> getListByIdentityList(final Collection<String> idList);

  List<UserGroupEntity> getListByIdCompanyIdentity(final String companyIdentity);
}
