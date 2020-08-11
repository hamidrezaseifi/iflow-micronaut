package com.pth.profile.entities;

import com.pth.common.edo.enums.EUserStatus;
import com.pth.common.entities.BaseEntity;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"username"})
})
public class UserEntity extends BaseEntity {

    @Column(length = 50, nullable = false)
    private String identity;

    @Column(length = 50, nullable = false , name = "company_id")
    private UUID companyId;

    @Column(length = 50, nullable = false)
    private String username;

    @Column(name = "password_hash", length = 500, nullable = false)
    private String passwordHash;

    @Column(name = "password_salt", length = 255, nullable = false)
    private String passwordSalt;

    @Column(name = "email")
    private String email;

    @Column(name = "birthdate")
    private java.sql.Date birthDate;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "permission")
    private Integer permission;

    @Column(name = "status")
    protected Integer status;

    @Column(name = "created_at", insertable = false, updatable = false)
    private Date createdAt;

    @Column(name = "updated_at", insertable = false, updatable = false)
    private Date updatedAt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "company_id", insertable = false, updatable = false)
    private CompanyEntity company;

    @ElementCollection
    @CollectionTable(name="users_roles", joinColumns=@JoinColumn(name="user_id"))
    @Column(name="role")
    @LazyCollection(LazyCollectionOption.FALSE)
    private Set<String> roles;

    @ElementCollection
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "user_usergroup", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = { @JoinColumn(name = "user_group") }
    )
    private Set<UserGroupEntity> groups;

    @ElementCollection
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "user_deputy", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = { @JoinColumn(name = "deputy_id") }
    )
    private Set<UserEntity> deputies;

    @ElementCollection
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "user_departments", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = { @JoinColumn(name = "department_id") }
    )
    private Set<UserDepartmentEntity> userDepartments;

    public UserEntity() {
        super();

        this.roles = new HashSet<>();

    }

    public UserEntity(String username,
                      String passwordHash,
                      String passwordSalt) {
        this();

        this.username = username;
        this.passwordHash = passwordHash;
        this.passwordSalt = passwordSalt;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public UUID getCompanyId() {
        return companyId;
    }

    public void setCompanyId(UUID companyId) {
        this.companyId = companyId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public boolean isLocked(){
        return this.status == EUserStatus.LOCKED.getValue();
    }

    public boolean isActive(){
        return this.status == EUserStatus.ACTIVE.getValue();
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getUsername() { return username; }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }
    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getPasswordSalt() {
        return passwordSalt;
    }
    public void setPasswordSalt(String passwordSalt) {
        this.passwordSalt = passwordSalt;
    }


    public Set<String> getRoles() {
        return roles;
    }
    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

}