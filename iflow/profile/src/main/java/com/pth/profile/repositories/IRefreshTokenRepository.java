package com.pth.profile.repositories;

import com.pth.profile.authentication.entities.RefreshTokenEntity;

import java.util.Date;
import java.util.Optional;

public interface IRefreshTokenRepository {

    Optional<RefreshTokenEntity> save(String username,
                            String refreshToken,
                            Date issuedAt);

    Optional<RefreshTokenEntity> findByRefreshToken(String refreshToken);

    Optional<RefreshTokenEntity> findByUsername(String username);

    void update(RefreshTokenEntity entity);

    void updateOrCreate(String username,
                        String refreshToken,
                        Date issuedAt);

}
