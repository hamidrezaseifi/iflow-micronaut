package com.pth.iflow.profile.entities;


import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "users")
public class UserEntity {

    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "identity", nullable = false)
    private String identity;


    @Column(name = "identity", nullable = false)
    private String company_id;

    @Column(name = "identity", nullable = false)
    private String username;

    @Column(name = "identity", nullable = false)
    private String password_hash;

    @Column(name = "identity", nullable = false)
    private String password_salt;

    @Column(name = "identity", nullable = false)
    private int status;

    @Column(name = "identity", nullable = false)
    private Date created_at;

    @Column(name = "identity", nullable = false)
    private Date updated_at;

    @OneToMany(fetch = FetchType.LAZY , mappedBy ="user" , cascade = {CascadeType.REFRESH})
    private Collection<UserRoleEntity> roles;

    public UserEntity() {

    }


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getCompany_id() {
        return company_id;
    }

    public void setCompany_id(String company_id) {
        this.company_id = company_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword_hash() {
        return password_hash;
    }

    public void setPassword_hash(String password_hash) {
        this.password_hash = password_hash;
    }

    public String getPassword_salt() {
        return password_salt;
    }

    public void setPassword_salt(String password_salt) {
        this.password_salt = password_salt;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }
}
