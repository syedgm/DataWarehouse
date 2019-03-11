package com.warehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.warehouse.model.SummaryReport;
import com.warehouse.repository.SummaryReportRepository;

@Service
public class SummaryReportService {

	@Autowired
	private SummaryReportRepository reportRepository;
	
	public void saveSummary(SummaryReport report) {
		reportRepository.save(report);
	}
	
	public SummaryReport getSummaryByFileName(String fileName) {
		return reportRepository.findByFileName(fileName);
	}

	
}
