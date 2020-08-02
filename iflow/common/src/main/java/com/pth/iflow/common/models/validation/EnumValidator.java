package com.pth.iflow.common.models.validation;

import java.util.HashSet;
import java.util.Set;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.constraints.NotNull;

import com.pth.iflow.common.enums.IEnumValidator;

public class EnumValidator implements ConstraintValidator<AEnumValidator, String> {

  private final Set<@NotNull String> validValues = new HashSet<>();

  @Override
  public void initialize(final AEnumValidator constraintAnnotation) {

    final Class<? extends IEnumValidator> enumClass = constraintAnnotation.enumClazz();

    final IEnumValidator[] enumValArr = enumClass.getEnumConstants();

    for (final IEnumValidator enumVal : enumValArr) {
      this.validValues.add(enumVal.getEnumValue());
    }

  }

  @Override
  public boolean isValid(final String value, final ConstraintValidatorContext context) {

    return this.validValues.contains(value);
  }

}
