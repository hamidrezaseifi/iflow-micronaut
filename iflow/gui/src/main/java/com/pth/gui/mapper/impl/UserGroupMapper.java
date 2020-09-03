package com.pth.gui.mapper.impl;

import com.pth.common.edo.UserGroupEdo;
import com.pth.common.mapping.ModelEdoMapperBase;
import com.pth.common.utils.MappingUtils;
import com.pth.profile.entities.UserGroupEntity;
import com.pth.profile.mapper.IUserGroupMapper;

import javax.inject.Singleton;

@Singleton
public class UserGroupMapper extends ModelEdoMapperBase<UserGroupEntity, UserGroupEdo>
        implements IUserGroupMapper {
    @Override
    public UserGroupEntity fromEdo(UserGroupEdo edo) {
        UserGroupEntity model = MappingUtils.copyProperties(edo, new UserGroupEntity());

        return model;
    }

    @Override
    public UserGroupEdo toEdo(UserGroupEntity model) {
        UserGroupEdo edo = MappingUtils.copyProperties(model, new UserGroupEdo());

        return edo;
    }

}
