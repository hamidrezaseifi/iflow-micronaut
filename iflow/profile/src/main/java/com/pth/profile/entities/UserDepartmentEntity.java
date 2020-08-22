package com.pth.profile.entities;

import com.pth.common.entities.BaseEntity;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Date;
import java.util.UUID;

@Entity
@Table(name = "user_departments")
public class UserDepartmentEntity {

  @Id
  @Column(name = "id")
  protected UUID id;

  @Column(name = "department_id")
  private UUID departmentId;

  @Column(name = "user_id")
  private UUID userId;

  @Column(name = "member_type")
  private int memberType;

  @CreationTimestamp
  @Column(name = "created_at", insertable = false, updatable = false)
  private Date createdAt;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "department_id", nullable = false, insertable = false, updatable = false)
  private DepartmentEntity department;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false, insertable = false, updatable = false)
  private UserEntity user;

  public UserDepartmentEntity() {
    super();

    this.id = UUID.randomUUID();
  }

  public UserDepartmentEntity(UserEntity userEntity, DepartmentEntity departmentEntity) {
    this();
    this.setDepartment(departmentEntity);
    this.setUser(userEntity);
  }

  public UUID getId() { return id; }
  public void setId(UUID id) {
    this.id = id;
  }

  public UUID getDepartmentId() {
    return departmentId;
  }

  public void setDepartmentId(UUID departmentId) {
    this.departmentId = departmentId;
  }

  public UUID getUserId() {
    return userId;
  }

  public void setUserId(UUID userId) {
    this.userId = userId;
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
    this.departmentId = department.getId();
    this.department = department;
  }

  public UserEntity getUser() {

    return user;
  }

  public void setUser(final UserEntity user) {
    this.userId = user.getId();
    this.user = user;
  }

  // EUserDepartmentAssignType
}
