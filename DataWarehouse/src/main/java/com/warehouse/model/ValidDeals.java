package com.warehouse.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "valid_deals")
public class ValidDeals {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", unique = true, nullable = false)
	private Long  id;
	
	@Column(name="deal_id")
	private String dealId;
	
	@Column(name="from_currency")
	private String fromCurrency;

	@Column(name="to_currency")
	private String toCurrency;
	
	@Column(name="deal_time")
	private LocalDateTime dealTime;
	
	@Column(name="amount")
	private Double amount;
	
	@Column(name="file_name")
	private String fileName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDealId() {
		return dealId;
	}

	public void setDealId(String dealId) {
		this.dealId = dealId;
	}

	public String getFromCurrency() {
		return fromCurrency;
	}

	public void setFromCurrency(String fromCurrency) {
		this.fromCurrency = fromCurrency;
	}

	public String getToCurrency() {
		return toCurrency;
	}

	public void setToCurrency(String toCurrency) {
		this.toCurrency = toCurrency;
	}

	public LocalDateTime getDealTime() {
		return dealTime;
	}

	public void setDealTime(LocalDateTime dealTime) {
		this.dealTime = dealTime;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
}
