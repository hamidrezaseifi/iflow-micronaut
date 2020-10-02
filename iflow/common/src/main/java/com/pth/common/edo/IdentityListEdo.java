package com.pth.common.edo;

import java.util.HashSet;
import java.util.Set;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSetter;
import io.micronaut.core.annotation.Introspected;

@Introspected
@JsonInclude(JsonInclude.Include.ALWAYS)
public class IdentityListEdo {

  @NotNull(message = "IdentityList must not be null")
  private Set<String> identityList = new HashSet<>();

  public IdentityListEdo() {

  }

  public IdentityListEdo(final Set<String> idList) {
    this.setIdentityList(idList);
  }

  public Set<String> getIdentityList() {
    return this.identityList;
  }

  @JsonSetter
  public void setIdentityList(final Set<String> identityList) {
    this.identityList = identityList;
  }

}
