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

        return fromEdoPrivate(edo, true);

    }

    @Override
    public UserEdo toEdo(UserEntity model) {
        UserEdo edo = new UserEdo();

        edo.setFirstName(model.getFirstName());
        edo.setLastName(model.getLastName());
        edo.setPermission(model.getPermission());
        edo.setStatus(model.getStatus());
        edo.setVersion(model.getVersion());
        edo.setEmail(model.getEmail());
        edo.setBirthDate(model.getBirthDate());
        edo.setCompanyId(model.getCompanyId());
        edo.setRoles(model.getRoles());
        edo.setRoles(model.getRoles());
        edo.setId(model.getId());
        edo.setUsername(model.getUsername());

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

    private UserEntity fromEdoPrivate(UserEdo edo, boolean mapDetails) {
        final UserEntity model = new UserEntity();

        model.setFirstName(edo.getFirstName());
        model.setLastName(edo.getLastName());
        model.setPermission(edo.getPermission());
        model.setStatus(edo.getStatus());
        model.setVersion(edo.getVersion());
        model.setEmail(edo.getEmail());
        model.setBirthDate(edo.getBirthDate());
        model.setCompanyId(edo.getCompanyId());
        model.setRoles(edo.getRoles());
        model.setRoles(edo.getRoles());
        model.setId(edo.getId());
        model.setUsername(edo.getUsername());

        if(mapDetails){
            for(UserDepartmentEdo udEdo: edo.getUserDepartments()){
                //UserDepartment udE = MappingUtils.copyProperties(udEdo, new UserDepartment());
                UserDepartmentEntity udE = new UserDepartmentEntity();
                udE.setMemberType(udEdo.getMemberType());
                udE.setDepartmentId(udEdo.getDepartmentId());
                model.addUserDepartment(udE);
            }

            for(UserEdo udEdo: edo.getDeputies()){
                UserEntity udE = fromEdoPrivate(udEdo, false);
                model.addDeputy(udE);
            }


            for(UserGroupEdo udEdo: edo.getGroups()){
                UserGroupEntity udE = MappingUtils.copyProperties(udEdo, new UserGroupEntity());
                model.addUserGroup(udE);
            }
        }

        return model;
    }

}
