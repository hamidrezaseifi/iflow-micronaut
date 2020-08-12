package com.pth.profile.mapper.impl;

import com.pth.common.edo.UserEdo;
import com.pth.common.mapping.ModelEdoMapperBase;
import com.pth.common.utils.MappingUtils;
import com.pth.profile.entities.UserEntity;
import com.pth.profile.mapper.IDepartmentMapper;
import com.pth.profile.mapper.IUserMapper;

import javax.inject.Singleton;

@Singleton
public class UserMapper extends ModelEdoMapperBase<UserEntity, UserEdo>
        implements IUserMapper {
    @Override
    public UserEntity fromEdo(UserEdo edo) {
        UserEntity model = MappingUtils.copyProperties(edo, new UserEntity());

        return model;
    }

    @Override
    public UserEdo toEdo(UserEntity model) {
        UserEdo edo = MappingUtils.copyProperties(model, new UserEdo());

        return edo;
    }

}
