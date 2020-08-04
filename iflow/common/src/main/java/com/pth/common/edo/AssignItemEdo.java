package com.pth.common.edo;

import com.pth.common.edo.enums.EAssignType;
import com.pth.common.edo.validation.AEnumNameValidator;

import javax.validation.constraints.NotNull;


public class AssignItemEdo {

  @NotNull(message = "ItemIdentity must not be null")
  private String itemIdentity;

  @NotNull(message = "ItemType must not be null")
  @AEnumNameValidator(enumClazz = EAssignType.class)
  private String itemType;

  public AssignItemEdo() {

  }

  public AssignItemEdo(final String itemIdentity, final String itemType) {
    this.setItemIdentity(itemIdentity);
    this.setItemType(itemType);
  }

  public String getItemIdentity() {
    return this.itemIdentity;
  }

  public void setItemIdentity(final String itemIdentity) {
    this.itemIdentity = itemIdentity;
  }

  public String getItemType() {
    return this.itemType;
  }

  public void setItemType(final String itemType) {
    this.itemType = itemType;
  }

}
