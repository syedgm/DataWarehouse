package com.warehouse.service;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.time.LocalDateTime;
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
import com.warehouse.model.SummaryReport;
import com.warehouse.model.ValidDeals;
import com.warehouse.repository.SummaryReportRepository;
import com.warehouse.validation.DealsValidator;

import static com.warehouse.util.Constants.*;

@Service
public class FileUploadService {

	private static final Logger LOGGER = LoggerFactory.getLogger(FileUploadService.class);
	
	@Autowired
	private ValidDealsService validDealsService;
	
	@Autowired
	private InvalidDealsService invalidDealsService;
	
	@Autowired
	private SummaryReportService reportService;
	
	@Autowired
	private DealsValidator dealsValidator;
	
    @Transactional
    public void uploadFile(MultipartFile file) {

        LOGGER.info("Upload File, File Name: {}", file.getOriginalFilename());

        Reader reader = null;

        try {

            final Long startTime = System.currentTimeMillis();

            reader = new InputStreamReader(file.getInputStream());
            Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(reader);

            List<ValidDeals> validDeals = new LinkedList<>();
            List<InvalidDeals> invalidDeals = new LinkedList<>();

            for (CSVRecord record: records) {

                final DealDto dealDto = new DealDto();
                dealDto.setDealId(record.get(DEAL_ID));
                dealDto.setFromCurrency(record.get(FROM_CURRENCY));
                dealDto.setToCurrency(record.get(TO_CURRENCY));
                dealDto.setDateTime(record.get(TIMESTAMP));
                dealDto.setAmount(record.get(AMOUNT));

                if (dealsValidator.isValid(dealDto)) {
                    ValidDeals validDeal = populateValidDeal(dealDto);
                    validDeal.setFileName(file.getOriginalFilename());

                    validDeals.add(validDeal);
                } else {
                    InvalidDeals invalidDeal = populateInvalidDeal(dealDto);
                    invalidDeal.setFileName(file.getOriginalFilename());

                    invalidDeals.add(invalidDeal);
                }
            }
            
            validDealsService.saveValidDeals(validDeals);
            invalidDealsService.saveInvalidDeals(invalidDeals);

            final Long endTime = System.currentTimeMillis();
            
            double processDuration = (endTime - startTime) / 1000;
            
            SummaryReport report = new SummaryReport();
            
            report.setFileName(file.getOriginalFilename());
            report.setNbrOfValidDeals(validDeals.size());
            report.setNbrOfInvalidDeals(invalidDeals.size());
            report.setProcessDuration(processDuration);
            
            reportService.saveSummary(report);

            LOGGER.info("Deals Imported Summary: Number of Valid Deals - {}, Number of Invalid Deals - {}, Elapsed Time - {}s", validDeals.size(), invalidDeals.size(), processDuration);
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
    
    private ValidDeals populateValidDeal(DealDto dealDto) {
    	
    	ValidDeals validDeal = new ValidDeals();
    	
        validDeal.setDealId(dealDto.getDealId());
        validDeal.setFromCurrency(dealDto.getFromCurrency());
        validDeal.setToCurrency(dealDto.getToCurrency());
        validDeal.setAmount(Double.parseDouble(dealDto.getAmount()));
        validDeal.setDealTime(LocalDateTime.parse(dealDto.getDateTime(), FORMATTER));

        return  validDeal;
    }

    private InvalidDeals populateInvalidDeal(DealDto dealDto) {
    	
    	InvalidDeals invalidDeal = new InvalidDeals();
    	
    	invalidDeal.setDealId(dealDto.getDealId());
    	invalidDeal.setFromCurrency(dealDto.getFromCurrency());
    	invalidDeal.setToCurrency(dealDto.getToCurrency());
    	invalidDeal.setAmount(dealDto.getAmount());
    	invalidDeal.setDealTime(dealDto.getDateTime());
    	
    	return  invalidDeal;
    }
}
