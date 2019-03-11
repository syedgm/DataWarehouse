package com.warehouse.controller;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.warehouse.model.SummaryReport;
import com.warehouse.service.SummaryReportService;

import io.micrometer.core.instrument.util.StringUtils;

@Controller
@RequestMapping("/summary")
public class SummaryReportController {
	private static final Logger LOGGER = LoggerFactory.getLogger(SummaryReportController.class);
	
	@Autowired
	private SummaryReportService reportService;
	
    @GetMapping(value = "/report", produces = "application/json")
    public ResponseEntity summaryReport(@RequestParam("fileName") String fileName) {

        if (StringUtils.isBlank(fileName)) {
            return ResponseEntity.badRequest().body("Please Enter a File name.");
        }

        LOGGER.info("Summary Report by file name: {}", fileName);

        SummaryReport summary = reportService.getSummaryByFileName(fileName);

        if (Objects.isNull(summary)) {
            return ResponseEntity.badRequest().body("No Records Found.");
        }

        return ResponseEntity.ok(summary);
    }
}
