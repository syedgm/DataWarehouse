package com.warehouse.dto;

import java.util.Date;

public class DealDto {

	private String dealId;
	private String fromCurrency;
	private String toCurrency;
	private Date dateTime;
	private Double amount;
	
	
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
	
	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}
}
