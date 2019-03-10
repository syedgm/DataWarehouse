package com.warehouse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.warehouse.model.ValidDeals;
import com.warehouse.repository.ValidDealsRepository;

@Service
public class ValidDealsService {

	@Autowired
	private ValidDealsRepository dealsRepository;
	
	public void saveValidDeals(List<ValidDeals> validDeals) {
		dealsRepository.batchSave(validDeals);
	}

}
