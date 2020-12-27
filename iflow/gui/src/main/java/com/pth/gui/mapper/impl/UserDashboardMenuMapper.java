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
        model.setUserId(edo.getUserId());
        model.setColumnIndex(edo.getColumnIndex());
        model.setAppId(edo.getAppId());
        model.setMenuId(edo.getMenuId());
        model.setRowIndex(edo.getRowIndex());
        model.setStatus(edo.getStatus());
        model.setVersion(edo.getVersion());
        model.setId(edo.getId());

        return model;
    }

    @Override
    public UserDashboardMenuEdo toEdo(UserDashboardMenu model) {
        final UserDashboardMenuEdo edo = new UserDashboardMenuEdo();
        edo.setUserId(model.getUserId());
        edo.setColumnIndex(model.getColumnIndex());
        edo.setAppId(model.getAppId());
        edo.setMenuId(model.getMenuId());
        edo.setRowIndex(model.getRowIndex());
        edo.setStatus(model.getStatus());
        edo.setVersion(model.getVersion());
        edo.setId(model.getId());

        return edo;
    }

}
