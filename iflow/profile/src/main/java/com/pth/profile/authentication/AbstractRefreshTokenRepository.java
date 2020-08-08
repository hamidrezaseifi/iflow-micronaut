package com.pth.profile.authentication;

import com.pth.common.exceptions.EntityAlreadyExistsException;
import com.pth.common.exceptions.EntityPropertyMissingException;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.jdbc.runtime.JdbcOperations;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;
import io.micronaut.data.annotation.Repository;
import org.hibernate.exception.ConstraintViolationException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.transaction.Transactional;
import java.sql.ResultSet;
import java.util.List;
import java.util.stream.Collectors;

@Repository(value = "h2")
@JdbcRepository(dialect = Dialect.H2)
public abstract  class AbstractRefreshTokenRepository implements CrudRepository<RefreshTokenEntity, Long> {

    private final JdbcOperations jdbcOperations;

    public AbstractRefreshTokenRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Transactional
    public List<RefreshTokenEntity> findByUsername(String username) {
        String sql = "SELECT * FROM RefreshTokenEntity AS token WHERE token.username = ?";
        return jdbcOperations.prepareStatement(sql, statement -> {
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            return jdbcOperations.entityStream(resultSet, RefreshTokenEntity.class).collect(Collectors.toList());
        });
    }

}
