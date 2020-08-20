package com.pth.profile.services.data.impl;

import com.pth.common.edo.enums.EUserDepartmentMemberType;
import com.pth.profile.entities.UserGroupEntity;
import com.pth.profile.entities.UserDepartmentEntity;
import com.pth.profile.entities.UserEntity;
import com.pth.profile.repositories.IDepartmentRepository;
import com.pth.profile.repositories.IUserGroupRepository;
import com.pth.profile.repositories.IUserRepository;
import com.pth.profile.services.data.IDepartmentService;
import com.pth.profile.services.data.IUserGroupService;

import javax.inject.Singleton;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Singleton
public class UserGroupService implements IUserGroupService {

    private final IUserGroupRepository userGroupRepository;

    public UserGroupService(IUserGroupRepository userGroupRepository) {
        this.userGroupRepository = userGroupRepository;
    }

    @Override
    public Optional<UserGroupEntity> save(UserGroupEntity model) {
        userGroupRepository.save(model);
        return userGroupRepository.getById(model.getId());
    }

    @Override
    public void delete(UserGroupEntity model) {
        userGroupRepository.delete(model);
    }

    @Override
    public Optional<UserGroupEntity> getByIdentity(String identity) {
        return userGroupRepository.getByIdentity(identity);
    }

    @Override
    public List<UserGroupEntity> getListByIdentityList(Collection<String> identityList) {
        return userGroupRepository.getListByIdentityList(identityList);
    }

    @Override
    public List<UserGroupEntity> getListByIdCompanyIdentity(String identity) {
        return userGroupRepository.getListByIdCompanyIdentity(identity);
    }




}
