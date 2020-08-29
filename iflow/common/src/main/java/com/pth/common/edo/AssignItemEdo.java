package com.pth.common.edo;

import com.pth.common.edo.enums.EAssignType;
import com.pth.common.edo.validation.AEnumNameValidator;

import javax.validation.constraints.NotNull;
import java.util.UUID;


public class AssignItemEdo {

  @NotNull(message = "ItemId must not be null")
  private UUID itemId;

  @NotNull(message = "ItemType must not be null")
  @AEnumNameValidator(enumClazz = EAssignType.class)
  private String itemType;

  public AssignItemEdo() {

  }

  public AssignItemEdo(final UUID itemId, final String itemType) {
    this.setItemId(itemId);
    this.setItemType(itemType);
  }

  public UUID getItemId() {
    return itemId;
  }

  public void setItemId(UUID itemId) {
    this.itemId = itemId;
  }

  public String getItemType() {
    return this.itemType;
  }

  public void setItemType(final String itemType) {
    this.itemType = itemType;
  }

}
