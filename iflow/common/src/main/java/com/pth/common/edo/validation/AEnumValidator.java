package com.pth.common.edo.validation;

import com.pth.common.edo.enums.IEnumValidator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;


@Documented
@Constraint(validatedBy = EnumValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@ReportAsSingleViolation
public @interface AEnumValidator {

  Class<? extends IEnumValidator> enumClazz();

  String message() default "Enum value is not valid";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

}
