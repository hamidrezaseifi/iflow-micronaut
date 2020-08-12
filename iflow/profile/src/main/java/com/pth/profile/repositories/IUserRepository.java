package com.pth.profile.repositories;

import com.pth.common.repositories.IEntityRepository;
import com.pth.profile.entities.UserEntity;

import java.util.Optional;

public interface IUserRepository extends IEntityRepository<UserEntity> {
    Optional<UserEntity> getByUsername(String username);
    Optional<UserEntity> getByIdentity(String identity);
    Optional<UserEntity> getUserByEmail(String email);
}
