package com.warehouse.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "summary_report")
public class SummaryReport {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private Long  id;

	@Column(name = "file_name", nullable = false)
	private String fileName;
	
	@Column(name = "nbr_of_valid_deals")
	private int nbrOfValidDeals = 0;
	
	@Column(name = "nbr_of_invalid_deals")
	private int nbrOfInvalidDeals = 0;
	
	@Column(name = "process_duration")
	private double processDuration;

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public int getNbrOfValidDeals() {
		return nbrOfValidDeals;
	}

	public void setNbrOfValidDeals(int nbrOfValidDeals) {
		this.nbrOfValidDeals = nbrOfValidDeals;
	}

	public int getNbrOfInvalidDeals() {
		return nbrOfInvalidDeals;
	}

	public void setNbrOfInvalidDeals(int nbrOfInvalidDeals) {
		this.nbrOfInvalidDeals = nbrOfInvalidDeals;
	}

	public double getProcessDuration() {
		return processDuration;
	}

	public void setProcessDuration(double processDuration) {
		this.processDuration = processDuration;
	}
	
}
