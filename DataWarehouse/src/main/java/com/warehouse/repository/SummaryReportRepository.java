package com.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.warehouse.model.SummaryReport;

@Repository
public interface SummaryReportRepository extends JpaRepository<SummaryReport, Long>{
	
	SummaryReport findByFileName(String fileName);
}
