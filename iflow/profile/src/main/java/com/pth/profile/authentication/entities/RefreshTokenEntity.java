//tag::clazzwithoutsettersandgetters[]
package com.pth.profile.authentication.entities;

import edu.umd.cs.findbugs.annotations.NonNull;
import io.micronaut.data.annotation.GeneratedValue;

import javax.persistence.*;

import io.micronaut.data.annotation.MappedEntity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.UUID;

@Entity
@Table
//@MappedEntity
public class RefreshTokenEntity {
    @Id
    @GeneratedValue
    private UUID id;

    private String username;

    @Column(length = 1000)
    private String refreshToken;

    private Date issuedAt;

    public RefreshTokenEntity() {
        super();
        this.id = UUID.randomUUID();
    }

    public RefreshTokenEntity(String username,
                              String refreshToken,
                              Date issuedAt) {
        this();
        this.username = username;
        this.refreshToken = refreshToken;
        this.issuedAt = issuedAt;
    }


    public UUID getId() {
        return id;
    }

    public void setId( UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername( String username) {
        this.username = username;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }


    public Date getIssuedAt() {
        return issuedAt;
    }

    public void setIssuedAt( Date issuedAt) {
        this.issuedAt = issuedAt;
    }
}
