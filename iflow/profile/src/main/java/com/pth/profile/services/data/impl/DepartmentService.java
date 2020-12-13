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
import java.util.UUID;

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
    public Optional<DepartmentEntity> create(DepartmentEntity model) {
        departmentRepository.save(model);
        return departmentRepository.getById(model.getId());
    }

    @Override
    public Optional<DepartmentEntity> update(DepartmentEntity model) {
        departmentRepository.update(model);
        return departmentRepository.getById(model.getId());
    }

    @Override
    public void delete(DepartmentEntity model) {
        departmentRepository.delete(model);
    }

    @Override
    public Optional<DepartmentEntity> getById(UUID id) {
        return departmentRepository.getById(id);
    }

    @Override
    public List<DepartmentEntity> getListByIdentityList(Collection<String> identityList) {
        return departmentRepository.getListByIdentityList(identityList);
    }

    @Override
    public List<DepartmentEntity> getListByIdCompanyId(UUID id) {
        return departmentRepository.getListByIdCompanyId(id);
    }

    @Override
    public Optional<UserEntity> getDepartmentManager(UUID id) {
        return findDepartmentMemberByMemberType(id, EUserDepartmentMemberType.MANAGER);
    }

    @Override
    public Optional<UserEntity> getDepartmentDeputy(UUID id) {

        return findDepartmentMemberByMemberType(id, EUserDepartmentMemberType.DEPUTY);
    }


    private Optional<UserEntity> findDepartmentMemberByMemberType(UUID id, EUserDepartmentMemberType memberType) {
        List<UserEntity> userList = this.userRepository.getUserListByDepartmentId(id);
        for(UserEntity userEntity:userList){
            if(userEntity.getUserDepartments() != null){
                for(UserDepartmentEntity userDepartmentEntity:userEntity.getUserDepartments()){
                    if(userDepartmentEntity.getDepartment().getId() == id &&
                            userDepartmentEntity.getMemberType() == memberType.getValue()){
                        return Optional.of(userEntity);
                    }
                }
            }
        }

        return Optional.empty();
    }


}
