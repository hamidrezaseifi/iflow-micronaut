package com.pth.workflow.mapper.impl;

import com.pth.common.edo.UserEdo;
import com.pth.common.mapping.ModelEdoMapperBase;
import com.pth.common.utils.MappingUtils;
import com.pth.workflow.mapper.IUserMapper;
import com.pth.workflow.models.User;

import javax.inject.Singleton;

@Singleton
public class UserMapper extends ModelEdoMapperBase<User, UserEdo>
        implements IUserMapper {
    @Override
    public User fromEdo(UserEdo edo) {
        User model = MappingUtils.copyProperties(edo, new User());

        return model;
    }

    @Override
    public UserEdo toEdo(User model) {
        UserEdo edo = MappingUtils.copyProperties(model, new UserEdo());

        return edo;
    }
}
