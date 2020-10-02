package com.pth.common.edo;

import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSetter;
import io.micronaut.core.annotation.Introspected;

@Introspected
@JsonInclude(JsonInclude.Include.ALWAYS)
public class DepartmentListEdo {

  @NotNull
  private final List<DepartmentEdo> departments = new ArrayList<>();

  public DepartmentListEdo() {

  }

  public DepartmentListEdo(final List<DepartmentEdo> departments) {
    this.setDepartments(departments);
  }

  public List<DepartmentEdo> getDepartments() {
    return this.departments;
  }

  @JsonSetter
  public void setDepartments(final List<DepartmentEdo> departments) {
    this.departments.clear();
    if (departments != null) {
      this.departments.addAll(departments);
    }
  }

}
