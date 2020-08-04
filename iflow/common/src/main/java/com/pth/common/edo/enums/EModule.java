package com.pth.common.edo.enums;

/**
 * A enumeration of names for MDM used modules
 *
 * @author bjoern frohberg
 */
public enum EModule {
  CORE("Core service"),
  WORKFLOW("Workflow service"),
  PROFILE("Profile service"),
  BACKED("Backed ui service"),
  GUI("GUI ui service");

  private final String moduleName;

  private EModule(final String moduleName) {
    this.moduleName = moduleName;
  }

  public String getModuleName() {
    return moduleName;
  }
}
