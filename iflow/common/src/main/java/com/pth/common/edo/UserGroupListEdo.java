package com.pth.common.edo;

import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSetter;
import io.micronaut.core.annotation.Introspected;

@Introspected
@JsonInclude(JsonInclude.Include.ALWAYS)
public class UserGroupListEdo {

  @NotNull
  private final List<UserGroupEdo> userGroups = new ArrayList<>();

  public UserGroupListEdo() {

  }

  public UserGroupListEdo(final List<UserGroupEdo> userGroups) {
    this.setUserGroups(userGroups);
  }

  public List<UserGroupEdo> getUserGroups() {
    return this.userGroups;
  }

  @JsonSetter
  public void setUserGroups(final List<UserGroupEdo> users) {
    this.userGroups.clear();
    if (users != null) {
      this.userGroups.addAll(users);
    }
  }

}
