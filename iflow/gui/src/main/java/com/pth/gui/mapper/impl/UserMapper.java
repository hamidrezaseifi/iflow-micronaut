package com.pth.gui.mapper.impl;

import com.pth.common.edo.UserDepartmentEdo;
import com.pth.common.edo.UserEdo;
import com.pth.common.edo.UserGroupEdo;
import com.pth.common.mapping.ModelEdoMapperBase;
import com.pth.common.utils.MappingUtils;
import com.pth.gui.mapper.IUserMapper;
import com.pth.gui.models.User;
import com.pth.gui.models.UserDepartment;
import com.pth.gui.models.UserGroup;

import javax.inject.Singleton;
import java.util.stream.Collectors;

@Singleton
public class UserMapper extends ModelEdoMapperBase<User, UserEdo>
        implements IUserMapper {
    @Override
    public User fromEdo(UserEdo edo) {
        final User model = new User();

        model.setFirstName(edo.getFirstName());
        model.setLastName(edo.getLastName());
        model.setPermission(edo.getPermission());
        model.setStatus(edo.getStatus());
        model.setVersion(edo.getVersion());
        model.setEmail(edo.getEmail());
        model.setBirthDate(edo.getBirthDate());
        model.setCompanyId(edo.getCompanyId());
        model.setRoles(edo.getRoles());
        model.setIdentity(edo.getIdentity());

        for(UserDepartmentEdo udEdo: edo.getUserDepartments()){
            UserDepartment udE = MappingUtils.copyProperties(udEdo, new UserDepartment());
            model.addUserDepartment(udE);
        }

        for(UserEdo udEdo: edo.getDeputies()){
            User udE = MappingUtils.copyProperties(udEdo, new User());
            model.addDeputy(udE);
        }


        for(UserGroupEdo udEdo: edo.getGroups()){
            UserGroup udE = MappingUtils.copyProperties(udEdo, new UserGroup());
            model.addGroup(udE);
        }

        return model;
    }

    @Override
    public UserEdo toEdo(User model) {
        UserEdo edo = MappingUtils.copyProperties(model, new UserEdo());

        for(UserDepartment ud: model.getUserDepartments()){
            UserDepartmentEdo udEdo = MappingUtils.copyProperties(ud, new UserDepartmentEdo());
            edo.addUserDepartment(udEdo);
        }

        for(User ue: model.getDeputies()){
            UserEdo udEdo = MappingUtils.copyProperties(ue, new UserEdo());
            edo.addDeputy(udEdo);
        }


        for(UserGroup ue: model.getGroups()){
            UserGroupEdo udEdo = MappingUtils.copyProperties(ue, new UserGroupEdo());
            edo.addGroup(udEdo);
        }
        return edo;
    }

}
