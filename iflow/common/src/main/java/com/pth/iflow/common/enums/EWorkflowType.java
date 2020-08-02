package com.pth.iflow.common.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.pth.iflow.common.exceptions.IFlowInvalidEnumValueException;

/**
 * A enumeration of names for MDM used modules
 *
 * @author bjoern frohberg
 */
public enum EWorkflowType implements IEnumNameValidator {

  NONE("None", ""),
  SINGLE_TASK_WORKFLOW_TYPE("singletaskworkflowtype", "singletask"),
  TESTTHREE_TASK_WORKFLOW_TYPE("threetaskworkflowtype", "testthreetask"),
  INVOICE_WORKFLOW_TYPE("invoiceworkflowtype", "invoice");

  private final String typeIdentity;
  private final String pagePreffix;

  EWorkflowType(final String enumName, final String pagePreffix) {
    this.typeIdentity = enumName;
    this.pagePreffix = pagePreffix;
  }

  @Override
  @JsonValue
  public final String getIdentity() {
    return this.typeIdentity;
  }

  public final String getPagePreffix() {
    return this.pagePreffix;
  }

  @JsonCreator
  public static EWorkflowType valueFromIdentity(final String typeIdentity) {
    for (final EWorkflowType item : values()) {
      if (item.typeIdentity.equals(typeIdentity)) {
        return item;
      }
    }

    throw new IFlowInvalidEnumValueException("Invalid value('" + typeIdentity + "') for EWorkflowType.");
  }

}
