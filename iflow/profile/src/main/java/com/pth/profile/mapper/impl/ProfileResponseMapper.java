package com.pth.profile.mapper.impl;

import com.pth.common.edo.ProfileResponseEdo;
import com.pth.common.edo.UserEdo;
import com.pth.common.mapping.ModelEdoMapperBase;
import com.pth.common.utils.MappingUtils;
import com.pth.profile.entities.UserEntity;
import com.pth.profile.mapper.IProfileResponseMapper;
import com.pth.profile.mapper.IUserMapper;
import com.pth.profile.models.ProfileResponse;

import javax.inject.Singleton;

@Singleton
public class ProfileResponseMapper extends ModelEdoMapperBase<ProfileResponse, ProfileResponseEdo>
        implements IProfileResponseMapper {
    @Override
    public ProfileResponse fromEdo(ProfileResponseEdo edo) {
        ProfileResponse model = MappingUtils.copyProperties(edo, new ProfileResponse());

        return model;
    }

    @Override
    public ProfileResponseEdo toEdo(ProfileResponse model) {
        ProfileResponseEdo edo = MappingUtils.copyProperties(model, new ProfileResponseEdo());

        return edo;
    }

}
