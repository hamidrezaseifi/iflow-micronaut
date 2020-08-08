package com.pth.profile.repositories;

import com.pth.profile.entities.RefreshTokenEntity;
import java.util.Optional;

public interface IRefreshTokenRepository {

    RefreshTokenEntity save(String username,
                            String refreshToken,
                            Boolean revoked);


    Optional<RefreshTokenEntity> findByRefreshToken(String refreshToken);


    long updateByUsername(String username,
                          Boolean revoked);
}
