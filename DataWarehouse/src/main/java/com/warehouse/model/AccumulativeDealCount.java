package com.warehouse.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by tristandiaz on 10/29/17.
 */
@Entity
@Table(name = "accumulative_deal_count")
public class AccumulativeDealCount {

    @Id
    @Column(name = "currency_code", unique = true, nullable = false)
    private String currencyCode;

    @Column(name = "count_of_deals")
    private long countOfDeals = 0;

    public AccumulativeDealCount() {

    }

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public long getCountOfDeals() {
		return countOfDeals;
	}

	public void setCountOfDeals(long countOfDeals) {
		this.countOfDeals = countOfDeals;
	}
}
