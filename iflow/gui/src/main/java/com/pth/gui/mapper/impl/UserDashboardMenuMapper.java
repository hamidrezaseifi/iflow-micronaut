package com.pth.gui.mapper.impl;

import com.pth.common.edo.UserDashboardMenuEdo;
import com.pth.common.mapping.ModelEdoMapperBase;
import com.pth.common.utils.MappingUtils;
import com.pth.profile.entities.UserDashboardMenuEntity;
import com.pth.profile.mapper.IUserDashboardMenuMapper;

import javax.inject.Singleton;

@Singleton
public class UserDashboardMenuMapper extends ModelEdoMapperBase<UserDashboardMenuEntity, UserDashboardMenuEdo>
        implements IUserDashboardMenuMapper {
    @Override
    public UserDashboardMenuEntity fromEdo(UserDashboardMenuEdo edo) {
        UserDashboardMenuEntity model = MappingUtils.copyProperties(edo, new UserDashboardMenuEntity());

        return model;
    }

    @Override
    public UserDashboardMenuEdo toEdo(UserDashboardMenuEntity model) {
        UserDashboardMenuEdo edo = MappingUtils.copyProperties(model, new UserDashboardMenuEdo());

        return edo;
    }

}
