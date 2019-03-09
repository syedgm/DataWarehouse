package com.warehouse.validation.constraints;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.warehouse.validation.FileIsEmptyValidator;

@Target({ METHOD, FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = FileIsEmptyValidator.class)
public @interface FileEmpty {
	String message() default "File is empty, please upload valid csv file";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
