package com.pth.common.edo.validation;

import com.pth.common.edo.enums.IEnumValidator;

import java.util.HashSet;
import java.util.Set;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.constraints.NotNull;


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
