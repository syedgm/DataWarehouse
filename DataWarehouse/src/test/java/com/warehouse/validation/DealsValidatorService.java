package com.warehouse.validation;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import com.warehouse.dto.DealDto;

@RunWith(SpringRunner.class)
public class DealsValidatorService {

	
	private DealsValidator dealsValidator;
	
	@Before
	public void setUp() {
		dealsValidator = new DealsValidator();	
	}
	
	@Test
    public void test_with_valid_vaues() {

        DealDto dealDto = new DealDto();
        dealDto.setAmount("1000.00");
        dealDto.setDealId("0101011");
        dealDto.setDateTime("2017-11-03 01:57:59");
        dealDto.setFromCurrency("PHP");
        dealDto.setToCurrency("AED");

        assertTrue(dealsValidator.isValid(dealDto));
    }

    @Test
    public void testInvalidWithAllNullValues() {
    	DealDto dealDto = new DealDto();

        assertFalse(dealsValidator.isValid(dealDto));

    }
    
    @Test
    public void testInvalidWithAllBlankOrEmptyValues() {
    	DealDto dealDto = new DealDto();
        dealDto.setAmount("");
        dealDto.setDealId("1");
        dealDto.setDateTime("2017-11-03 01:57:59");
        dealDto.setFromCurrency("PHP");
        dealDto.setToCurrency("AED");

        assertFalse(dealsValidator.isValid(dealDto));
    }
    
    @Test
    public void testInvalidDoubleAmountFormat() {
    	DealDto dealDto = new DealDto();
        dealDto.setAmount("$1000.00");
        dealDto.setDealId("1");
        dealDto.setDateTime("2017-11-03 01:57:59");
        dealDto.setFromCurrency("PHP");
        dealDto.setToCurrency("AED");

        assertFalse(dealsValidator.isValid(dealDto));
    }
    
    @Test
    public void testInvalidWithBlankDealId() {
    	DealDto dealDto = new DealDto();
    	dealDto.setAmount("1000.00");
        dealDto.setDealId("");
        dealDto.setDateTime("2017-11-03 01:57:59");
        dealDto.setFromCurrency("PHP");
        dealDto.setToCurrency("AED");

        assertFalse(dealsValidator.isValid(dealDto));
    }

    @Test
    public void testInvalidWithInvalidDateFormat() {

    	DealDto dealDto = new DealDto();
        dealDto.setAmount("1000.00");
        dealDto.setDealId("11");
        dealDto.setDateTime("2017/11/03 01:57:59");
        dealDto.setFromCurrency("PHP");
        dealDto.setToCurrency("AED");

        assertFalse(dealsValidator.isValid(dealDto));
    }
    
    @Test
    public void testInvalidWithEmptyFromCurrencyCode() {
    	
    	DealDto dealDto = new DealDto();
        dealDto.setAmount("1000.00");
        dealDto.setDealId("11");
        dealDto.setDateTime("2017-11-03 01:57:59");
        dealDto.setFromCurrency("");
        dealDto.setToCurrency("AED");

        assertFalse(dealsValidator.isValid(dealDto));
    }

    @Test
    public void testInvalidWithWrongFromCurrencyCode() {
        DealDto dealDto = new DealDto();
        dealDto.setAmount("1000.00");
        dealDto.setDealId("11");
        dealDto.setDateTime("2017-11-03 01:57:59");
        dealDto.setFromCurrency("*#&#");
        dealDto.setToCurrency("AED");

        assertFalse(dealsValidator.isValid(dealDto));
    }
    
    @Test
    public void testInvalidWithEmptyToCurrencyCode() {
    	DealDto dealDto = new DealDto();
        dealDto.setAmount("1000.00");
        dealDto.setDealId("11");
        dealDto.setDateTime("2017-11-03 01:57:59");
        dealDto.setFromCurrency("AED");
        dealDto.setToCurrency("");

        assertFalse(dealsValidator.isValid(dealDto));
    }

    @Test
    public void testInvalidWithWrongToCurrencyCode() {
        
        DealDto dealDto = new DealDto();
        dealDto.setAmount("1000.00");
        dealDto.setDealId("11");
        dealDto.setDateTime("2017-11-03 01:57:59");
        dealDto.setFromCurrency("USD");
        dealDto.setToCurrency("*$($");

        assertFalse(dealsValidator.isValid(dealDto));
    }

}
