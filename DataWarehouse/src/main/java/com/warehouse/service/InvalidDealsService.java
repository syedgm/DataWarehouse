package com.warehouse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.warehouse.model.InvalidDeals;
import com.warehouse.repository.InvalidDealsRepository;

@Service
public class InvalidDealsService {

	@Autowired
	private InvalidDealsRepository invalidDealsRepository;
	
	public void saveInvalidDeals(List<InvalidDeals> invalidDeals) {
		invalidDealsRepository.batchSave(invalidDeals);
	}

}
