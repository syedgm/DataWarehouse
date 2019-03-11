package com.warehouse.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import com.warehouse.repository.SummaryReportRepository;
import com.warehouse.validation.constraints.FileProcessed;

public class FileAlreadyProcessed implements ConstraintValidator<FileProcessed, MultipartFile> {

	@Autowired
	private SummaryReportRepository summaryReportRepository;
	
	@Override
	public boolean isValid(MultipartFile file, ConstraintValidatorContext context) {
		
		if(file.isEmpty()) {
			return Boolean.TRUE;
		}
		
		return ObjectUtils.isEmpty(summaryReportRepository.findByFileName(file.getOriginalFilename()));
	}

}
