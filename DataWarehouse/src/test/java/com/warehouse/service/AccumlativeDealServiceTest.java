package com.warehouse.service;

import static org.hamcrest.CoreMatchers.any;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.isA;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import com.warehouse.model.AccumulativeDealCount;
import com.warehouse.model.ValidDeals;
import com.warehouse.repository.AccumlativeDealsRepository;

@RunWith(SpringRunner.class)
public class AccumlativeDealServiceTest {

	@Mock
    private AccumlativeDealsRepository accumlativeDealsRepository;

    @InjectMocks
    private AccumlativeDealService accumlativeDealService = new AccumlativeDealService();

    @Test
    public void testUpdateCountOfDealsPerCurrency() {

    	List<ValidDeals> validDeals = this.getValidDeals();

        AccumulativeDealCount accumulativeDealCount1 = new AccumulativeDealCount();
        accumulativeDealCount1.setCurrencyCode("AED");
        accumulativeDealCount1.setCountOfDeals(5);
        
        AccumulativeDealCount accumulativeDealCount2 = new AccumulativeDealCount();
        accumulativeDealCount2.setCurrencyCode("USD");
        accumulativeDealCount2.setCountOfDeals(10);

        when(accumlativeDealsRepository.findById(Mockito.anyString())).thenReturn(Optional.of(accumulativeDealCount1));
        when(accumlativeDealsRepository.findById(Mockito.anyString())).thenReturn(Optional.of(accumulativeDealCount2));
        when(accumlativeDealsRepository.save(Mockito.any(AccumulativeDealCount.class))).thenReturn(new AccumulativeDealCount());

        accumlativeDealService.updateDealsCurrencyCount(validDeals);
    }

	private List<ValidDeals> getValidDeals() {
        ValidDeals validDeal = new ValidDeals();
        validDeal.setDealTime(LocalDateTime.now());
        validDeal.setAmount(5d);
        validDeal.setFromCurrency("AED");
        validDeal.setToCurrency("AFN");
        validDeal.setFileName("filename.csv");
        validDeal.setDealId("0001");
        
        ValidDeals validDeal2 = new ValidDeals();
        validDeal2.setDealTime(LocalDateTime.now());
        validDeal2.setAmount(10d);
        validDeal2.setFromCurrency("USD");
        validDeal2.setToCurrency("AFN");
        validDeal2.setFileName("filename.csv");
        validDeal2.setDealId("0002");

		return Arrays.asList(validDeal, validDeal2);
	}
}
