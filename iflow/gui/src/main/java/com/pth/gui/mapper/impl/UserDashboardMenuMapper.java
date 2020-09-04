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
        final UserDashboardMenu model = new UserDashboardMenu();
        model.setUserIdentity(edo.getUserIdentity());
        model.setColumnIndex(edo.getColumnIndex());
        model.setAppId(edo.getAppId());
        model.setMenuId(edo.getMenuId());
        model.setRowIndex(edo.getRowIndex());
        model.setStatus(edo.getStatus());
        model.setVersion(edo.getVersion());

        return model;
    }

    @Override
    public UserDashboardMenuEdo toEdo(UserDashboardMenu model) {
        final UserDashboardMenuEdo edo = new UserDashboardMenuEdo();
        edo.setUserIdentity(model.getUserIdentity());
        edo.setColumnIndex(model.getColumnIndex());
        edo.setAppId(model.getAppId());
        edo.setMenuId(model.getMenuId());
        edo.setRowIndex(model.getRowIndex());
        edo.setStatus(model.getStatus());
        edo.setVersion(model.getVersion());

        return edo;
    }

}
