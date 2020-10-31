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
        final UserGroup model = new UserGroup();

        model.setTitle(edo.getTitle());
        model.setStatus(edo.getStatus());
        model.setIdentity(edo.getIdentity());
        model.setCompanyIdentity(edo.getCompanyIdentity());
        model.setVersion(edo.getVersion());
        model.setId(edo.getId());

        return model;
    }

    @Override
    public UserGroupEdo toEdo(UserGroup model) {
        final UserGroupEdo edo = new UserGroupEdo();
        edo.setTitle(model.getTitle());
        edo.setStatus(model.getStatus());
        edo.setIdentity(model.getIdentity());
        edo.setCompanyIdentity(model.getCompanyIdentity());
        edo.setVersion(model.getVersion());
        edo.setId(model.getId());

        return edo;
    }

}
