package com.pth.profile.repositories;

import com.pth.common.repositories.IEntityRepository;
import com.pth.profile.entities.UserEntity;
import com.pth.profile.entities.UserGroupEntity;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IUserGroupRepository extends IEntityRepository<UserGroupEntity> {

    Optional<UserGroupEntity> getByIdentity(String identity);

    List<UserGroupEntity> getListByIdentityList(Collection<String> identityList);

    List<UserGroupEntity> getListByIdCompanyId(UUID id);
}
