package com.warehouse.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.web.multipart.MultipartFile;

import com.warehouse.validation.constraints.FileProcessed;

public class FileAlreadyProcessed implements ConstraintValidator<FileProcessed, MultipartFile> {

	@Override
	public boolean isValid(MultipartFile value, ConstraintValidatorContext context) {
		// TODO Auto-generated method stub
		return true;
	}

}
