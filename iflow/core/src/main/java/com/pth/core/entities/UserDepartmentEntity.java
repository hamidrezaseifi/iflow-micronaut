package com.pth.core.entities;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "user_departments")
public class UserDepartmentEntity {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "user_department_id_seq")
  @SequenceGenerator(
                     name = "user_department_id_seq", allocationSize = 1, initialValue = 1, sequenceName = "user_department_id_seq"
  )
  private Long id;

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

  }

  public Long getId() {

    return id;
  }

  public void setId(final Long id) {

    this.id = id;
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
