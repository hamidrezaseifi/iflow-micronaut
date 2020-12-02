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

        return fromEdoPrivate(edo, true);
    }

    @Override
    public UserEdo toEdo(User model) {
        UserEdo edo = extractUserEdo(model);

        for(UserDepartment ud: model.getUserDepartments()){
            UserDepartmentEdo udEdo = new UserDepartmentEdo();
            udEdo.setDepartmentId(ud.getDepartmentId());
            udEdo.setMemberType(ud.getMemberType().getValue());
            udEdo.setUserId(model.getId());
            edo.addUserDepartment(udEdo);
        }

        for(User ue: model.getDeputies()){
            UserEdo udEdo = extractUserEdo(ue);
            edo.addDeputy(udEdo);
        }


        for(UserGroup ue: model.getGroups()){
            UserGroupEdo udEdo = MappingUtils.copyProperties(ue, new UserGroupEdo());
            edo.addGroup(udEdo);
        }
        return edo;
    }

    private UserEdo extractUserEdo(User model) {
        UserEdo edo = new UserEdo();

        edo.setFirstName(model.getFirstName());
        edo.setLastName(model.getLastName());
        edo.setPermission(model.getPermission().getPermission());
        edo.setStatus(model.getStatus());
        edo.setVersion(model.getVersion());
        edo.setEmail(model.getEmail());
        edo.setBirthDate(model.getBirthDate());
        edo.setCompanyId(model.getCompanyId());
        edo.setRoles(model.getRoles());
        edo.setIdentity(model.getIdentity());
        edo.setRoles(model.getRoles());
        edo.setId(model.getId());
        edo.setUsername(model.getUsername());
        return edo;
    }

    private User fromEdoPrivate(UserEdo edo, boolean mapDetails) {
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
        model.setRoles(edo.getRoles());
        model.setId(edo.getId());
        model.setUsername(edo.getUsername());

        if(mapDetails){
            for(UserDepartmentEdo udEdo: edo.getUserDepartments()){
                //UserDepartment udE = MappingUtils.copyProperties(udEdo, new UserDepartment());
                UserDepartment udE = new UserDepartment();
                udE.setMemberType(udEdo.getMemberType());
                udE.setDepartmentId(udEdo.getDepartmentId());
                model.addUserDepartment(udE);
            }

            for(UserEdo udEdo: edo.getDeputies()){
                User udE = fromEdoPrivate(udEdo, false);
                model.addDeputy(udE);
            }


            for(UserGroupEdo udEdo: edo.getGroups()){
                UserGroup udE = MappingUtils.copyProperties(udEdo, new UserGroup());
                model.addGroup(udE);
            }
        }

        return model;
    }

}
