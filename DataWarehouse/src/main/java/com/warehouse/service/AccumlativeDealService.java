package com.warehouse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.warehouse.model.AccumulativeDealCount;
import com.warehouse.model.ValidDeals;
import com.warehouse.repository.AccumlativeDealsRepository;

@Service
public class AccumlativeDealService {

	@Autowired
	private AccumlativeDealsRepository accumlativeRepository;

	public void updateDealsCurrencyCount(List<ValidDeals> validDeals) {
		for(ValidDeals deal : validDeals) {
			AccumulativeDealCount accumulativeDealCount = accumlativeRepository.findById(deal.getFromCurrency()).orElse(new AccumulativeDealCount());
			accumulativeDealCount.setCurrencyCode(deal.getFromCurrency());
			accumulativeDealCount.setCountOfDeals(accumulativeDealCount.getCountOfDeals() + 1);
			
			accumlativeRepository.save(accumulativeDealCount);
//			accumlativeRepository.updateDealsCountByCurrencyCode(deal.getFromCurrency());
		}
 	}
	
	
}
