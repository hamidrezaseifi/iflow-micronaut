package com.pth.profile.services.data.impl;

import com.pth.common.edo.enums.EUserDepartmentMemberType;
import com.pth.profile.entities.DepartmentEntity;
import com.pth.profile.entities.UserDepartmentEntity;
import com.pth.profile.entities.UserEntity;
import com.pth.profile.repositories.IDepartmentRepository;
import com.pth.profile.repositories.IUserRepository;
import com.pth.profile.services.data.IDepartmentService;

import javax.inject.Singleton;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Singleton
public class DepartmentService implements IDepartmentService {

    private final IDepartmentRepository departmentRepository;
    private final IUserRepository userRepository;

    public DepartmentService(IDepartmentRepository departmentRepository,
                             IUserRepository userRepository) {
        this.departmentRepository = departmentRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Optional<DepartmentEntity> save(DepartmentEntity model) {
        departmentRepository.save(model);
        return departmentRepository.getById(model.getId());
    }

    @Override
    public void delete(DepartmentEntity model) {

    }

    @Override
    public Optional<DepartmentEntity> getByIdentity(String identity) {
        return departmentRepository.getByIdentity(identity);
    }

    @Override
    public List<DepartmentEntity> getListByIdentityList(Collection<String> identityList) {
        return departmentRepository.getListByIdentityList(identityList);
    }

    @Override
    public List<DepartmentEntity> getListByIdCompanyIdentity(String identity) {
        return departmentRepository.getListByIdCompanyIdentity(identity);
    }

    @Override
    public UserEntity getDepartmentManager(String identity) {
        return findDepartmentMemberByMemberType(identity, EUserDepartmentMemberType.MANAGER);
    }

    @Override
    public UserEntity getDepartmentDeputy(String identity) {

        return findDepartmentMemberByMemberType(identity, EUserDepartmentMemberType.DEPUTY);
    }


    private UserEntity findDepartmentMemberByMemberType(String identity, EUserDepartmentMemberType memberType) {
        List<UserEntity> userList = this.userRepository.getUserListByDepartmentIdentity(identity);
        for(UserEntity userEntity:userList){
            if(userEntity.getUserDepartments() != null){
                for(UserDepartmentEntity userDepartmentEntity:userEntity.getUserDepartments()){
                    if(userDepartmentEntity.getDepartment().getIdentity().equals(identity) &&
                            userDepartmentEntity.getMemberType() == memberType.getValue()){
                        return userEntity;
                    }
                }
            }
        }

        return null;
    }


}
