//tag::clazzwithoutsettersandgetters[]
package com.pth.profile.authentication;

import edu.umd.cs.findbugs.annotations.NonNull;
import io.micronaut.data.annotation.GeneratedValue;
import javax.persistence.Id;
import io.micronaut.data.annotation.MappedEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "tokens")
//@MappedEntity
public class RefreshTokenEntity {
    @Id
    @GeneratedValue
    private Long id;

    private String username;

    private String refreshToken;

    private Date issuedAt;

    public RefreshTokenEntity() {
    }

    public RefreshTokenEntity(@NonNull @NotBlank String username,
                              @NonNull @NotBlank String refreshToken,
                              @NonNull @NotNull Date issuedAt) {
        super();
        this.username = username;
        this.refreshToken = refreshToken;
        this.issuedAt = issuedAt;
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
    public Date getIssuedAt() {
        return issuedAt;
    }

    public void setIssuedAt(@NonNull Date issuedAt) {
        this.issuedAt = issuedAt;
    }
}
