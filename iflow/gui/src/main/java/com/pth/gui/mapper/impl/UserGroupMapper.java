package com.pth.gui.mapper.impl;

import com.pth.common.edo.UserGroupEdo;
import com.pth.common.mapping.ModelEdoMapperBase;
import com.pth.common.utils.MappingUtils;
import com.pth.gui.mapper.IUserGroupMapper;
import com.pth.gui.models.UserGroup;

import javax.inject.Singleton;

@Singleton
public class UserGroupMapper extends ModelEdoMapperBase<UserGroup, UserGroupEdo>
        implements IUserGroupMapper {
    @Override
    public UserGroup fromEdo(UserGroupEdo edo) {
        UserGroup model = MappingUtils.copyProperties(edo, new UserGroup());

        return model;
    }

    @Override
    public UserGroupEdo toEdo(UserGroup model) {
        UserGroupEdo edo = MappingUtils.copyProperties(model, new UserGroupEdo());

        return edo;
    }

}
