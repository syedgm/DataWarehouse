package com.warehouse.validation;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.warehouse.dto.DealDto;
import static com.warehouse.util.Constants.FORMATTER;

@Component
public class DealsValidator {
	
	public boolean isValid(DealDto dealDto) {
        if (StringUtils.isEmpty(dealDto.getDealId())) {
            return false;
        }

        if (StringUtils.isEmpty(dealDto.getToCurrency())) {
            return false;
        }

        if (StringUtils.isEmpty(dealDto.getFromCurrency())) {
            return false;
        }

        if (StringUtils.isEmpty(dealDto.getDateTime())) {
            return false;
        } else {
            try {
                LocalDateTime.parse(dealDto.getDateTime(), FORMATTER);
            } catch (DateTimeParseException e) {
                return false;
            }
        }

        if (StringUtils.isEmpty(dealDto.getAmount())) {
            return false;
        } else {
            try {
                new Double(dealDto.getAmount());
            } catch (NumberFormatException e) {
                return false;
            }
        }

        return Boolean.TRUE;
	}
}
