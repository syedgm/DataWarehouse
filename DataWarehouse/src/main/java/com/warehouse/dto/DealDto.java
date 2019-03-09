package com.warehouse.dto;

public class DealDto {

	private String dealId;
	private String fromCurrency;
	private String toCurrency;
	private String dateTime;
	private String amount;
	
	
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
	
	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}
}
