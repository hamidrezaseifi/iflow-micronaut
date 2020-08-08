package com.pth.profile.authentication;

import com.pth.profile.repositories.IRefreshTokenRepository;
import edu.umd.cs.findbugs.annotations.NonNull;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;

import javax.transaction.Transactional;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import io.micronaut.data.annotation.Repository;

//@Repository(value = "h2")
@JdbcRepository(dialect = Dialect.H2)
public interface RefreshTokenRepository extends CrudRepository<RefreshTokenEntity, Long> { // <2>

    @Transactional
    RefreshTokenEntity save(@NonNull @NotBlank String username,
                            @NonNull @NotBlank String refreshToken,
                            @NonNull @NotNull Date issuedAt); // <3>

    Optional<RefreshTokenEntity> findByRefreshToken(@NonNull @NotBlank String refreshToken); // <4>

    Optional<RefreshTokenEntity> getByUsername(String name);

}
