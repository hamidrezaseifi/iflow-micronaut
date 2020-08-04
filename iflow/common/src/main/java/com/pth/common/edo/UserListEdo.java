package com.pth.common.edo;

import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonSetter;

public class UserListEdo {

  @NotNull
  private final List<UserEdo> users = new ArrayList<>();

  public UserListEdo() {

  }

  public UserListEdo(final List<UserEdo> users) {
    this.setUsers(users);
  }

  public List<UserEdo> getUsers() {
    return this.users;
  }

  @JsonSetter
  public void setUsers(final List<UserEdo> users) {
    this.users.clear();
    if (users != null) {
      this.users.addAll(users);
    }
  }

}
