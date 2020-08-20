package com.pth.profile.repositories.impl;

import com.pth.common.repositories.AEntityRdbmsHibernateRepository;
import com.pth.profile.entities.DepartmentEntity_;
import com.pth.profile.entities.UserGroupEntity;
import com.pth.profile.entities.UserGroupEntity_;
import com.pth.profile.repositories.IUserGroupRepository;
import org.springframework.stereotype.Repository;
import io.micronaut.spring.tx.annotation.Transactional;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Singleton
@Repository
@Transactional
//@Requires(property = "micronaut.extensions.repositories.type", value = "RdbmsHibernate")
public class UserGroupRepository extends AEntityRdbmsHibernateRepository<UserGroupEntity>
        implements IUserGroupRepository {
    public UserGroupRepository(EntityManager entityManager) {
        super(UserGroupEntity.class, entityManager);
    }


    @Override
    public Optional<UserGroupEntity> getByIdentity(String identity) {
        return queryScala((cb, root) -> (cb.equal(root.get(UserGroupEntity_.identity), identity)));
    }

    @Override
    public List<UserGroupEntity> getListByIdentityList(Collection<String> identityList) {
        return queryCollection((cb, root) -> ( root.in(identityList)));
    }

    @Override
    public List<UserGroupEntity> getListByIdCompanyIdentity(String identity) {
        return queryCollection((cb, root) -> (cb.equal(root.get(UserGroupEntity_.companyId), identity)));
    }
}
