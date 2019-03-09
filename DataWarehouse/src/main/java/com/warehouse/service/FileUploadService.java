package com.warehouse.service;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.warehouse.dto.DealDto;
import com.warehouse.exception.DataWarehouseExcecption;
import com.warehouse.model.InvalidDeals;
import com.warehouse.model.ValidDeals;

@Service
public class FileUploadService {

	private static final Logger LOGGER = LoggerFactory.getLogger(FileUploadService.class);
	
	@Autowired
	private ValidDealsService validDealsService;
	
	@Autowired
	private InvalidDealsService invalidDealsService;
	
	
    @Transactional
    public void uploadFile(MultipartFile file) {

        LOGGER.info("Upload File, File Name: {}", file.getOriginalFilename());

        Reader reader = null;

        try {

            final Long startTime = System.currentTimeMillis();

            reader = new InputStreamReader(file.getInputStream());

            final Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(reader);

//            final Map<CurrencyCode, Long> currencyCountMap = accumulativeDealCountService.findAllDealsCurrencyCountMap();

            final List<ValidDeals> validDeals = new LinkedList<>();
            final List<InvalidDeals> invalidDeals = new LinkedList<>();


            for (CSVRecord record: records) {

                final DealDto dealDto = new DealDto();
                dealDto.setDealId(record.get(Headers.DEAL_ID.name()));
                dealDto.setFromCurrency(record.get(Headers.FROM_CURRENCY.name()));
                dealDto.setToCurrency(record.get(Headers.TO_CURRENCY.name()));
//                dealDto.setDateTime(record.get(Headers.TIMESTAMP));
//                dealDto.setAmount(record.get(Headers.AMOUNT));

//                if (dealsValidator.valid(dealDto)) {
//
//                    final ValidDeal validDeal = ValidDeal.valueOf(dealDto);
//                    validDeal.setFileName(transactionLog.getFileName());
//
//                    validDeals.add(validDeal);
//
//                    incrementCurrencyCount(currencyCountMap, CurrencyCode.valueOf(dealDto.getFromCurrency()));
//
//                } else {
//
//                    final InvalidDeal invalidDeal = InvalidDeal.valueOf(dealDto);
//                    invalidDeal.setFileName(transactionLog.getFileName());
//
//                    invalidDeals.add(invalidDeal);
//                }
//            }
//
            }
//            validDealsService.saveValidDeals(validDeals);
//            invalidDealsService.saveInvalidDeals(invalidDeals);

            final Long endTime = System.currentTimeMillis();

            LOGGER.info("Deals Imported Summary: Number of Valid Deals - {}, Number of Invalid Deals - {}, Elapsed Time - {}s", validDeals.size(), invalidDeals.size(), (endTime - startTime));
        } catch (Exception e) {
            throw new DataWarehouseExcecption(String.format("Error importing deals: "+e.getMessage()));
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                LOGGER.error("Error closing the Reader.");
            }
        }
    }
    
    private enum Headers {
        DEAL_ID,
        FROM_CURRENCY,
        TO_CURRENCY,
        TIMESTAMP,
        AMOUNT
    }

}
