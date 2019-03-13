package com.warehouse.service;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import com.warehouse.model.InvalidDeals;
import com.warehouse.repository.InvalidDealsRepository;

@RunWith(SpringRunner.class)
public class InvalidDealsServiceTest {

	@Mock
	private InvalidDealsRepository invalidDealsRepository;
	
	@InjectMocks
	private InvalidDealsService invalidDealsService;

	@Test
	public void testSaveInvalidDeals() {
		List<InvalidDeals> invalidDeals = new ArrayList<>();
	
		invalidDealsService.saveInvalidDeals(invalidDeals);
	
		verify(invalidDealsRepository, atLeastOnce()).batchSave(Mockito.eq(invalidDeals));
	}

}
