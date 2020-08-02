package com.pth.iflow.common.models.edo;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.pth.iflow.common.enums.EAssignType;
import com.pth.iflow.common.models.base.IFlowJaxbDefinition;
import com.pth.iflow.common.models.validation.AEnumNameValidator;

@XmlRootElement(name = "AssignItem", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = IFlowJaxbDefinition.IFlow.NAMESPACE, name = "AssignItem" + IFlowJaxbDefinition.TYPE_PREFIX)
public class AssignItemEdo {

  @NotNull(message = "ItemIdentity must not be null")
  @XmlElement(name = "ItemIdentity", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
  private String itemIdentity;

  @NotNull(message = "ItemType must not be null")
  @AEnumNameValidator(enumClazz = EAssignType.class)
  @XmlElement(name = "ItemType", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
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
