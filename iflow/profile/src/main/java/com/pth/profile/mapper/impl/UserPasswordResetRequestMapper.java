package com.pth.profile.mapper.impl;

import com.pth.common.edo.CompanyEdo;
import com.pth.common.edo.UserPasswordResetRequestEdo;
import com.pth.common.mapping.ModelEdoMapperBase;
import com.pth.common.utils.MappingUtils;
import com.pth.profile.entities.CompanyEntity;
import com.pth.profile.mapper.ICompanyMapper;
import com.pth.profile.mapper.IUserPasswordResetRequestMapper;
import com.pth.profile.models.UserPasswordResetRequest;

import javax.inject.Singleton;

@Singleton
public class UserPasswordResetRequestMapper
        extends ModelEdoMapperBase<UserPasswordResetRequest, UserPasswordResetRequestEdo>
        implements IUserPasswordResetRequestMapper {
    @Override
    public UserPasswordResetRequest fromEdo(UserPasswordResetRequestEdo edo) {
        UserPasswordResetRequest model = MappingUtils.copyProperties(edo, new UserPasswordResetRequest());

        return model;
    }

    @Override
    public UserPasswordResetRequestEdo toEdo(UserPasswordResetRequest model) {
        UserPasswordResetRequestEdo edo = MappingUtils.copyProperties(model, new UserPasswordResetRequestEdo());

        return edo;
    }

}
