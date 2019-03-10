package com.warehouse.repository.custom;

import java.util.List;

import com.warehouse.model.InvalidDeals;

public interface InvalidDealRepositoryCustom {

	void batchSave(List<InvalidDeals> invalidDeals);
}
