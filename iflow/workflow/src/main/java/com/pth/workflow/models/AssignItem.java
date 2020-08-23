package com.pth.workflow.models;


import com.pth.common.edo.enums.EAssignType;

import java.util.UUID;

public class AssignItem {

  private UUID itemId;
  private EAssignType itemType;

  public AssignItem() {

  }

  public AssignItem(final UUID itemId, final EAssignType itemType) {
    this.setItemId(itemId);
    this.setItemType(itemType);
  }

  public UUID getItemId() {
    return itemId;
  }

  public void setItemId(UUID itemId) {
    this.itemId = itemId;
  }

  public EAssignType getItemType() {
    return this.itemType;
  }

  public void setItemType(final EAssignType itemType) {
    this.itemType = itemType;
  }

}
