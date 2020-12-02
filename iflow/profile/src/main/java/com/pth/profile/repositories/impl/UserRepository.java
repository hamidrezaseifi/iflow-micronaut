package com.pth.profile.repositories.impl;

import com.pth.common.repositories.AEntityRdbmsHibernateRepository;
import com.pth.profile.entities.*;
import com.pth.profile.repositories.IUserRepository;
import io.micronaut.spring.tx.annotation.Transactional;
import org.springframework.stereotype.Repository;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.SetJoin;
import java.util.*;

@Singleton
@Repository
@Transactional
//@Requires(property = "micronaut.extensions.repositories.type", value = "RdbmsHibernate")
public class UserRepository extends AEntityRdbmsHibernateRepository<UserEntity>
        implements IUserRepository {

    @PersistenceContext
    private final EntityManager entityManager;

    public UserRepository(EntityManager entityManager) {
        super(UserEntity.class, entityManager);
        this.entityManager = entityManager;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserEntity> getByUsername(String username) {

        return queryScala((cb, root) -> (cb.equal(root.get(UserEntity_.username), username)));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserEntity> getById(UUID id) {
        return queryScala((cb, root) -> (cb.equal(root.get(UserEntity_.id), id)));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserEntity> getUserByUsername(String email) {
        return queryScala((cb, root) -> (cb.equal(root.get(UserEntity_.username), email)));
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserEntity> getUserListByCompanyId(UUID companyId) {

        return queryCollection((cb, root) -> (cb.equal(root.get(UserEntity_.companyId), companyId)));
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserEntity> getUserListByDepartmentId(UUID departmentId) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<UserEntity> criteriaQuery = criteriaBuilder.createQuery(UserEntity.class);

        Root<UserEntity> root = criteriaQuery.from(UserEntity.class);
        SetJoin<UserEntity, UserDepartmentEntity> userDepartmentJoin = root.join(UserEntity_.userDepartments);
        CriteriaQuery<UserEntity> criteriaRootQuery = criteriaQuery.select(root);

        criteriaRootQuery.where( criteriaBuilder.equal(userDepartmentJoin.get(UserDepartmentEntity_.DEPARTMENT).get(DepartmentEntity_.ID) , departmentId));

        TypedQuery<UserEntity> typedQuery = entityManager.createQuery(criteriaRootQuery);

        return typedQuery.getResultList();

    }

    @Override
    @Transactional(readOnly = true)
    public List<UserEntity> getUserListByIdList(Set<UUID> idList) {
        return queryCollection((cb, root) -> ( root.get(UserEntity_.id).in(idList)));

    }

    @Override
    public void update(UserEntity model){

        Optional<UserEntity> foundUserOptional = getById(model.getId());
        if(foundUserOptional.isPresent()){
            UserEntity foundModel = foundUserOptional.get();

            model.setPasswordHash(foundModel.getPasswordHash());
            model.setPasswordSalt(foundModel.getPasswordSalt());

            super.update(model);
        }


    }


}
