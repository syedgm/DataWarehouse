package com.warehouse.service;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import com.warehouse.model.ValidDeals;
import com.warehouse.repository.ValidDealsRepository;

@RunWith(SpringRunner.class)
public class ValidDealServiceTest {
	@Mock(name = "dealsRepository")
    private ValidDealsRepository dealsRepository;

    @InjectMocks
    private ValidDealsService validDealsService = new ValidDealsService();


    @Test
    public void testSaveValidDeals() {

        List<ValidDeals> validDeals = new ArrayList<>();

        validDealsService.saveValidDeals(validDeals);

        verify(dealsRepository, atLeastOnce()).batchSave(Mockito.eq(validDeals));
    }


    @Test
    public void testFIndValidDealByDealId() {

        ValidDeals testDeal = new ValidDeals();

        when(dealsRepository.findByDealId(Mockito.anyString())).thenReturn(testDeal);

        ValidDeals returnedDeal = validDealsService.getValidDealByDealId("deal_id");

        verify(dealsRepository, atLeastOnce()).findByDealId(Mockito.eq("deal_id"));
        assertThat(returnedDeal, is(testDeal));
    }

}
