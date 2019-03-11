package com.warehouse.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.warehouse.service.FileUploadService;
import com.warehouse.validation.FormValidator;

@Controller
public class FileUploadController {
	private static final Logger LOGGER = LoggerFactory.getLogger(FileUploadController.class);
	
	@Autowired
	private FileUploadService fileUploadService;

	@GetMapping("/file")
    public ModelAndView index() {
		
		return getModelView();
    }
    
    @PostMapping("/upload")
    public ModelAndView processFileUpload(@ModelAttribute("formValidator") @Valid FormValidator form, BindingResult bindingResult, Model model) {

    	if (bindingResult.hasErrors()) {
            return new ModelAndView("upload");
        }

        final MultipartFile file = form.getFile();

        fileUploadService.uploadFile(file);

        final ModelAndView modelAndView = getModelView();
        modelAndView.addObject("success", true);

        return modelAndView;
    }
    
    @ExceptionHandler(Exception.class)
    public ModelAndView handleException(Exception e) {
        LOGGER.error("Failed importing file.", e);

        ModelAndView modelAndView = getModelView();
        modelAndView.addObject("error", true);

        return modelAndView;
    }
    
    private ModelAndView getModelView() {
        ModelAndView modelAndView = new ModelAndView("upload");
        modelAndView.addObject("formValidator", new FormValidator());

        return modelAndView;
    }

}
