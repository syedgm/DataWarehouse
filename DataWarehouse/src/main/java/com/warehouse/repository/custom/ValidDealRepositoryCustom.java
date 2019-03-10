package com.warehouse.repository.custom;

import java.util.List;

import com.warehouse.model.ValidDeals;

public interface ValidDealRepositoryCustom {

	void batchSave(List<ValidDeals> validDeals);
}
