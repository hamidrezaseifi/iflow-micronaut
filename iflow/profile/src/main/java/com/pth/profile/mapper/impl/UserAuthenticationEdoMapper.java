package com.pth.profile.mapper.impl;

import com.pth.common.edo.TokenValidationRequestEdo;
import com.pth.common.edo.UserAuthenticationRequestEdo;
import com.pth.common.edo.UserAuthenticationResponseEdo;
import com.pth.common.edo.enums.EApplication;
import com.pth.common.utils.MappingUtils;
import com.pth.profile.mapper.IUserAuthenticationEdoMapper;
import com.pth.profile.models.TokenValidationRequest;
import com.pth.profile.models.UserAuthenticationRequest;
import com.pth.profile.models.UserAuthenticationResponse;

import javax.inject.Singleton;

@Singleton
public class UserAuthenticationEdoMapper implements IUserAuthenticationEdoMapper {

    @Override
    public UserAuthenticationResponseEdo toEdo(UserAuthenticationResponse response){
        UserAuthenticationResponseEdo edo =
                MappingUtils.copyProperties(response, new UserAuthenticationResponseEdo());

        return edo;
    }

    @Override
    public UserAuthenticationResponse fromEdo(UserAuthenticationResponseEdo edo){
        UserAuthenticationResponse response =
                MappingUtils.copyProperties(edo, new UserAuthenticationResponse());

        return response;
    }

    @Override
    public UserAuthenticationRequestEdo toEdo(UserAuthenticationRequest request){
        UserAuthenticationRequestEdo edo =
                MappingUtils.copyProperties(request, new UserAuthenticationRequestEdo());

        return edo;

    }

    @Override
    public UserAuthenticationRequest fromEdo(UserAuthenticationRequestEdo edo){
        UserAuthenticationRequest request =
                MappingUtils.copyProperties(edo, new UserAuthenticationRequest());

        return request;
    }

    @Override
    public TokenValidationRequestEdo toEdo(TokenValidationRequest request){
        TokenValidationRequestEdo edo =
                MappingUtils.copyProperties(request, new TokenValidationRequestEdo());

        return edo;

    }

    @Override
    public TokenValidationRequest fromEdo(TokenValidationRequestEdo edo){
        TokenValidationRequest request =
                MappingUtils.copyProperties(edo, new TokenValidationRequest());

        request.setApp(EApplication.valueFromId(edo.getAppId()));
        return request;
    }



}
