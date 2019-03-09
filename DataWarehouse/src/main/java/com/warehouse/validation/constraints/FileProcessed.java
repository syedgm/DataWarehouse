package com.warehouse.validation.constraints;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.warehouse.validation.FileAlreadyProcessed;

@Target({ METHOD, FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = FileAlreadyProcessed.class)
public @interface FileProcessed {
	String message() default "File Already Processed.";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
