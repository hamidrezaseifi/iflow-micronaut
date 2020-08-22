package com.pth.workflow.models;


import com.pth.common.edo.enums.EAssignType;

public class AssignItem {

  private String      itemIdentity;
  private EAssignType itemType;

  public AssignItem() {

  }

  public AssignItem(final String itemIdentity, final EAssignType itemType) {
    this.setItemIdentity(itemIdentity);
    this.setItemType(itemType);
  }

  public String getItemIdentity() {
    return itemIdentity;
  }

  public void setItemIdentity(final String itemIdentity) {
    this.itemIdentity = itemIdentity;
  }

  public EAssignType getItemType() {
    return this.itemType;
  }

  public void setItemType(final EAssignType itemType) {
    this.itemType = itemType;
  }

}
