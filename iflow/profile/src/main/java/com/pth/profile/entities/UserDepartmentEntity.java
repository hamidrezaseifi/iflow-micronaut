package com.pth.profile.entities;

import com.pth.common.entities.BaseEntity;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "user_departments")
public class UserDepartmentEntity extends BaseEntity {

  @Column(name = "member_type")
  private int memberType;

  @CreationTimestamp
  @Column(name = "created_at", insertable = false, updatable = false)
  private Date createdAt;

  @ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.REFRESH })
  @JoinColumn(name = "department_id", nullable = false)
  private DepartmentEntity department;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  private UserEntity user;

  public UserDepartmentEntity() {
    super();
  }

  public UserDepartmentEntity(UserEntity userEntity, DepartmentEntity departmentEntity) {
    this();
    this.setDepartment(departmentEntity);
    this.setUser(userEntity);
  }

  public int getMemberType() {

    return memberType;
  }

  public void setMemberType(final int memberType) {

    this.memberType = memberType;
  }

  public Date getCreatedAt() {

    return createdAt;
  }

  public void setCreatedAt(final Date createdAt) {

    this.createdAt = createdAt;
  }

  public DepartmentEntity getDepartment() {

    return department;
  }

  public void setDepartment(final DepartmentEntity department) {

    this.department = department;
  }

  public UserEntity getUser() {

    return user;
  }

  public void setUser(final UserEntity user) {

    this.user = user;
  }

  // EUserDepartmentAssignType
}
