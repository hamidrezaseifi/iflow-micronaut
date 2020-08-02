package com.pth.iflow.common.models.edo;

import java.util.HashSet;
import java.util.Set;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.pth.iflow.common.models.base.IFlowJaxbDefinition;

@XmlRootElement(name = "IdentityList", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = IFlowJaxbDefinition.IFlow.NAMESPACE, name = "IdList" + IFlowJaxbDefinition.TYPE_PREFIX)
public class IdentityListEdo {

  @NotNull(message = "IdentityList must not be null")
  @XmlElementWrapper(name = "IdList", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
  @XmlElement(name = "ID", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
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
