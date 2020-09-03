package com.pth.gui.mapper.impl;

import com.pth.common.edo.UserDashboardMenuEdo;
import com.pth.common.mapping.ModelEdoMapperBase;
import com.pth.common.utils.MappingUtils;
import com.pth.gui.mapper.IUserDashboardMenuMapper;
import com.pth.gui.models.UserDashboardMenu;

import javax.inject.Singleton;

@Singleton
public class UserDashboardMenuMapper extends ModelEdoMapperBase<UserDashboardMenu, UserDashboardMenuEdo>
        implements IUserDashboardMenuMapper {
    @Override
    public UserDashboardMenu fromEdo(UserDashboardMenuEdo edo) {
        UserDashboardMenu model = MappingUtils.copyProperties(edo, new UserDashboardMenu());

        return model;
    }

    @Override
    public UserDashboardMenuEdo toEdo(UserDashboardMenu model) {
        UserDashboardMenuEdo edo = MappingUtils.copyProperties(model, new UserDashboardMenuEdo());

        return edo;
    }

}
