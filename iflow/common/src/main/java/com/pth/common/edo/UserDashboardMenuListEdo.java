package com.pth.common.edo;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSetter;
import io.micronaut.core.annotation.Introspected;

@Introspected
@JsonInclude(JsonInclude.Include.ALWAYS)
public class UserDashboardMenuListEdo {

  @NotNull
  private final List<UserDashboardMenuEdo> userDashboardMenus = new ArrayList<>();

  public UserDashboardMenuListEdo() {

  }

  public UserDashboardMenuListEdo(final List<UserDashboardMenuEdo> userDashboardMenus) {

    this.setUserDashboardMenus(userDashboardMenus);
  }

  public List<UserDashboardMenuEdo> getUserDashboardMenus() {

    return this.userDashboardMenus;
  }

  @JsonSetter
  public void setUserDashboardMenus(final List<UserDashboardMenuEdo> userDashboardMenus) {

    this.userDashboardMenus.clear();
    if (userDashboardMenus != null) {
      this.userDashboardMenus.addAll(userDashboardMenus);
    }
  }

}
