package com.warehouse.validation;

import org.springframework.web.multipart.MultipartFile;

import com.warehouse.validation.constraints.CsvFormat;
import com.warehouse.validation.constraints.FileEmpty;
import com.warehouse.validation.constraints.FileProcessed;

public class FormValidator {

	@FileEmpty
    @CsvFormat
    @FileProcessed
    private MultipartFile file;

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}
	
}
