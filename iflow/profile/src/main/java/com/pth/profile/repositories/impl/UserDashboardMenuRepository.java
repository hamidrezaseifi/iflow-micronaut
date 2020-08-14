package com.pth.profile.repositories.impl;

import com.pth.common.repositories.AEntityRdbmsHibernateRepository;
import com.pth.profile.entities.UserDashboardMenuEntity;
import com.pth.profile.entities.UserDashboardMenuEntity_;
import com.pth.profile.repositories.IUserDashboardMenuRepository;
import org.springframework.stereotype.Repository;
import io.micronaut.spring.tx.annotation.Transactional;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.UUID;

@Singleton
@Repository
@Transactional
//@Requires(property = "micronaut.extensions.repositories.type", value = "RdbmsHibernate")
public class UserDashboardMenuRepository extends AEntityRdbmsHibernateRepository<UserDashboardMenuEntity>
        implements IUserDashboardMenuRepository {
    public UserDashboardMenuRepository(EntityManager entityManager) {
        super(UserDashboardMenuEntity.class, entityManager);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserDashboardMenuEntity> getByUserId(UUID userId, String appId) {
        return queryCollection((cb, root) -> (
                cb.and(cb.equal(root.get(UserDashboardMenuEntity_.userId), userId),
                cb.equal(root.get(UserDashboardMenuEntity_.appId), appId)))
        );
    }
}
