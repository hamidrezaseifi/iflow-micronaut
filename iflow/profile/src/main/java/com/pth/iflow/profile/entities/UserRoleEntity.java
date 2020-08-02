package com.pth.iflow.profile.entities;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "users")
public class UserRoleEntity {

    @Column(name = "role", nullable = false)
    private String role;

    @Column(name = "identity", nullable = false)
    private Date created_at;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST,
                                                   CascadeType.MERGE})
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    public UserRoleEntity() {

    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

}
