package com.warehouse.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.web.multipart.MultipartFile;

import com.warehouse.validation.constraints.CsvFormat;

public class CsvValidator implements ConstraintValidator<CsvFormat, MultipartFile> {

	@Override
	public boolean isValid(MultipartFile file, ConstraintValidatorContext context) {
		
		if (file.isEmpty()) {
            return true;
        }

//        return file.getContentType().equals("text/csv");
        return true;
	}

}
