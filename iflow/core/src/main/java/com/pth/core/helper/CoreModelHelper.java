package com.pth.core.helper;

public abstract class CoreModelHelper {

  abstract public Long getId();

  abstract public void setId(final Long id);

  public boolean isNew() {
    return getId() == null || getId() <= 0;
  }

  public void increaseVersion() {
    setVersion(getVersion() + 1);
  }

  public abstract void setVersion(Integer version);

  public abstract Integer getVersion();

}
