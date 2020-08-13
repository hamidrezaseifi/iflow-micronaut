package com.pth.profile.repositories.impl;

import com.pth.common.repositories.AEntityRdbmsHibernateRepository;
import com.pth.profile.entities.*;
import com.pth.profile.repositories.IUserRepository;
import io.micronaut.data.annotation.Repository;
import io.micronaut.spring.tx.annotation.Transactional;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.SetJoin;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

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
    public Optional<UserEntity> getByIdentity(String identity) {
        return queryScala((cb, root) -> (cb.equal(root.get(UserEntity_.identity), identity)));
    }

    @Override
    public Optional<UserEntity> getUserByEmail(String email) {
        return queryScala((cb, root) -> (cb.equal(root.get(UserEntity_.email), email)));
    }

    @Override
    public List<UserEntity> getUserListByCompanyId(UUID companyId) {

        return queryCollection((cb, root) -> (cb.equal(root.get(UserEntity_.companyId), companyId)));
    }

    @Override
    public List<UserEntity> getAllUserIdentityListByDepartmentIdentity(String departmentId) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<UserEntity> criteriaQuery = criteriaBuilder.createQuery(UserEntity.class);

        Root<UserEntity> root = criteriaQuery.from(UserEntity.class);
        SetJoin<UserEntity, UserDepartmentEntity> userDepartmentJoin = root.join(UserEntity_.userDepartments);
        CriteriaQuery<UserEntity> criteriaRootQuery = criteriaQuery.select(root);

        criteriaRootQuery.where( criteriaBuilder.equal(userDepartmentJoin.get(UserDepartmentEntity_.DEPARTMENT).get(DepartmentEntity_.IDENTITY) , departmentId));

        TypedQuery<UserEntity> typedQuery = entityManager.createQuery(criteriaRootQuery);

        return typedQuery.getResultList();

    }

    @Override
    public List<UserEntity> getUserListByIdentityList(Set<String> identityList) {
        return queryCollection((cb, root) -> ( root.get(UserEntity_.identity).in(identityList)));

    }


}
