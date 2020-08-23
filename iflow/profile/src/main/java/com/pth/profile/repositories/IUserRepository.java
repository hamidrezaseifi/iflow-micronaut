package com.pth.profile.repositories;

import com.pth.common.repositories.IEntityRepository;
import com.pth.profile.entities.UserEntity;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface IUserRepository extends IEntityRepository<UserEntity> {
    Optional<UserEntity> getByUsername(String username);
    Optional<UserEntity> getByIdentity(String identity);
    Optional<UserEntity> getUserByEmail(String email);
    List<UserEntity> getUserListByCompanyId(UUID companyId);
    List<UserEntity> getUserListByDepartmentId(UUID departmentId);
    List<UserEntity> getUserListByIdentityList(Set<String> identityList);

}
