package com.warehouse.validation.constraints;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import com.warehouse.validation.CsvValidator;

@Target({ METHOD, FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = CsvValidator.class)
public @interface CsvFormat {
	String message() default "Invalid file format, please upload CSV format only.";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
