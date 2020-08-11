package com.pth.profile.entities;

import com.pth.common.entities.BaseEntity;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "iflow_roles")
public class IflowRoleEntity extends BaseEntity {

  @Column(name = "title")
  private String title;

  @Column(name = "status")
  private Integer status;

  @CreationTimestamp
  @Column(name = "created_at", insertable = false, updatable = false)
  private Date createdAt;

  @UpdateTimestamp
  @Column(name = "updated_at", insertable = false, updatable = false)
  private Date updatedAt;

  @ManyToMany(mappedBy = "groups")
  private Set<UserEntity> users = new HashSet<>();

  public IflowRoleEntity() {

  }


  public String getTitle() {

    return this.title;
  }

  public void setTitle(final String title) {

    this.title = title;
  }

  public Integer getStatus() {

    return this.status;
  }

  public void setStatus(final Integer status) {

    this.status = status;
  }

  public Date getCreatedAt() {

    return this.createdAt;
  }

  public void setCreatedAt(final Date createdAt) {

    this.createdAt = createdAt;
  }

  public Date getUpdatedAt() {

    return this.updatedAt;
  }

  public void setUpdatedAt(final Date updatedAt) {

    this.updatedAt = updatedAt;
  }

  public void increaseVersion() {

    version += 1;
  }

  public Set<UserEntity> getUsers() {

    return users;
  }

  public void setUsers(final Set<UserEntity> users) {

    this.users = users;
  }
}
