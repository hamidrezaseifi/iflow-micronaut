package com.pth.profile.mapper.impl;

import com.pth.common.edo.UserDepartmentEdo;
import com.pth.common.edo.UserEdo;
import com.pth.common.edo.UserGroupEdo;
import com.pth.common.mapping.ModelEdoMapperBase;
import com.pth.common.utils.MappingUtils;
import com.pth.profile.entities.UserDepartmentEntity;
import com.pth.profile.entities.UserEntity;
import com.pth.profile.entities.UserGroupEntity;
import com.pth.profile.mapper.IDepartmentMapper;
import com.pth.profile.mapper.IUserMapper;

import javax.inject.Singleton;

@Singleton
public class UserMapper extends ModelEdoMapperBase<UserEntity, UserEdo>
        implements IUserMapper {
    @Override
    public UserEntity fromEdo(UserEdo edo) {
        UserEntity model = MappingUtils.copyProperties(edo, new UserEntity());

        return model;
    }

    @Override
    public UserEdo toEdo(UserEntity model) {
        UserEdo edo = MappingUtils.copyProperties(model, new UserEdo());

        for(UserDepartmentEntity ud: model.getUserDepartments()){
            UserDepartmentEdo udEdo = MappingUtils.copyProperties(ud, new UserDepartmentEdo());
            edo.addUserDepartment(udEdo);
        }

        for(UserEntity ue: model.getDeputies()){
            UserEdo udEdo = MappingUtils.copyProperties(ue, new UserEdo());
            edo.addDeputy(udEdo);
        }


        for(UserGroupEntity ue: model.getGroups()){
            UserGroupEdo udEdo = MappingUtils.copyProperties(ue, new UserGroupEdo());
            edo.addGroup(udEdo);
        }
        return edo;
    }

}
