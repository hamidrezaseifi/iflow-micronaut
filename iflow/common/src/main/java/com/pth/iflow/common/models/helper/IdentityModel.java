package com.pth.iflow.common.models.helper;

import org.apache.commons.lang3.StringUtils;

import com.pth.iflow.common.enums.EIdentity;

public abstract class IdentityModel {

  abstract public String getIdentity();

  abstract public void setIdentity(final String identity);

  public boolean isNew() {

    return this instanceof IdentityModel && IdentityModel.isIdentityNew(this.getIdentity());
  }

  public void setIdentityToNew() {

    this.setIdentity("");
  }

  public static boolean isIdentityNew(final String identity) {

    return EIdentity.isNotSet(identity);
  }

  public boolean hasSameIdentity(final String identity) {

    return areSameIdentity(this.getIdentity(), identity);
  }

  public boolean hasNotSameIdentity(final String identity) {

    return areSameIdentity(this.getIdentity(), identity) == false;
  }

  public static boolean areSameIdentity(final String identity1, final String identity2) {

    return (StringUtils.isEmpty(identity2) && StringUtils.isEmpty(identity1)) || identity2.equals(identity1);
  }

}
