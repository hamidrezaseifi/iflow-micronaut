package com.pth.common.edo;

import com.fasterxml.jackson.annotation.JsonSetter;

import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class IdListEdo {

  @NotNull(message = "IdList must not be null")
  private Set<UUID> idList = new HashSet<>();

  public IdListEdo() {

  }

  public IdListEdo(final Set<UUID> idList) {
    this.setIdList(idList);
  }

  public Set<UUID> getIdList() {
    return this.idList;
  }

  @JsonSetter
  public void setIdList(final Set<UUID> idList) {
    this.idList = idList;
  }

}
