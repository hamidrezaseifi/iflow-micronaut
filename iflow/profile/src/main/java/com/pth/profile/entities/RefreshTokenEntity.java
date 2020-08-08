//tag::clazzwithoutsettersandgetters[]
package com.pth.profile.entities;

import edu.umd.cs.findbugs.annotations.NonNull;
import io.micronaut.data.annotation.DateCreated;
import io.micronaut.data.annotation.GeneratedValue;
import javax.persistence.Id;
import io.micronaut.data.annotation.MappedEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.Instant;

@Entity
@Table(name = "tokens")
@MappedEntity
public class RefreshTokenEntity {
    @Id
    @GeneratedValue
    @NonNull
    private Long id;

    @NonNull
    @NotBlank
    private String username;

    @NonNull
    @NotBlank
    private String refreshToken;

    @NonNull
    @NotNull
    private Boolean revoked;

    @DateCreated // <4>
    @NonNull
    @NotNull
    private Instant dateCreated;

    public RefreshTokenEntity() {
    }

    public RefreshTokenEntity(@NonNull @NotBlank String username,
                              @NonNull @NotBlank String refreshToken,
                              @NonNull @NotNull Boolean revoked) {
        super();
        this.username = username;
        this.refreshToken = refreshToken;
        this.revoked = revoked;
    }

    //end::clazzwithoutsettersandgetters[]

    @NonNull
    public Long getId() {
        return id;
    }

    public void setId(@NonNull Long id) {
        this.id = id;
    }

    @NonNull
    public String getUsername() {
        return username;
    }

    public void setUsername(@NonNull String username) {
        this.username = username;
    }

    @NonNull
    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(@NonNull String refreshToken) {
        this.refreshToken = refreshToken;
    }

    @NonNull
    public Boolean getRevoked() {
        return revoked;
    }

    public void setRevoked(@NonNull Boolean revoked) {
        this.revoked = revoked;
    }

    @NonNull
    public Instant getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(@NonNull Instant dateCreated) {
        this.dateCreated = dateCreated;
    }
}
