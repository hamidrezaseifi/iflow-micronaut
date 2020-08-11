package com.pth.profile.services.data;

import com.pth.common.edo.UserGroupEdo;
import com.pth.profile.entities.UserGroupEntity;
import com.pth.profile.services.base.ICoreModelEdoMapperService;

import java.util.Collection;
import java.util.List;

public interface IUserGroupService extends ICoreModelEdoMapperService<UserGroupEntity, UserGroupEdo> {

  UserGroupEntity save(UserGroupEntity model);

  UserGroupEntity getByIdentity(final String identity);

  List<UserGroupEntity> getListByIdentityList(final Collection<String> idList);

  List<UserGroupEntity> getListByIdCompanyIdentity(final String companyIdentity);
}
