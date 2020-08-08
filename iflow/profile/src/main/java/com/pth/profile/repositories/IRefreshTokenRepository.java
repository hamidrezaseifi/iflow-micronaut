package com.pth.profile.repositories;

import com.pth.profile.authentication.RefreshTokenEntity;

import java.util.Date;
import java.util.Optional;

public interface IRefreshTokenRepository {

    RefreshTokenEntity save(String username,
                            String refreshToken,
                            Date issuedAt);

    Optional<RefreshTokenEntity> findByRefreshToken(String refreshToken);

    Optional<RefreshTokenEntity> findByUsername(String username);

}
