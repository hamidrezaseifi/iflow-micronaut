package com.pth.iflow.common.models.validation;

import java.util.HashSet;
import java.util.Set;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.constraints.NotNull;

import com.pth.iflow.common.enums.IEnumValueValidator;

public class EnumValueValidator implements ConstraintValidator<AEnumValueValidator, Integer> {

  private final Set<@NotNull Integer> validValues = new HashSet<>();

  @Override
  public void initialize(final AEnumValueValidator constraintAnnotation) {

    final Class<? extends IEnumValueValidator> enumClass = constraintAnnotation.enumClazz();

    final IEnumValueValidator[] enumValArr = enumClass.getEnumConstants();

    for (final IEnumValueValidator enumVal : enumValArr) {
      this.validValues.add(enumVal.getValue());
    }

  }

  @Override
  public boolean isValid(final Integer value, final ConstraintValidatorContext context) {
    return this.validValues.contains(value);
  }

}
