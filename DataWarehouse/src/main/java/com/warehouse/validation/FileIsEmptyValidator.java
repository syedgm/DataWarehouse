package com.warehouse.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.web.multipart.MultipartFile;
import com.warehouse.validation.constraints.FileEmpty;

public class FileIsEmptyValidator implements ConstraintValidator<FileEmpty, MultipartFile> {

	@Override
	public boolean isValid(MultipartFile file, ConstraintValidatorContext context) {
		return !file.isEmpty();
	}

}
