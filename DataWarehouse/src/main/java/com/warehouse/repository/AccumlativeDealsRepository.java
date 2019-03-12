package com.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.warehouse.model.AccumulativeDealCount;

@Repository
public interface AccumlativeDealsRepository extends JpaRepository<AccumulativeDealCount, String> {

	@Modifying
	@Query("update AccumulativeDealCount deal set deal.countOfDeals = deal.countOfDeals + 1 where deal.currencyCode = :currencyCode")
	int updateDealsCountByCurrencyCode(@Param("currencyCode") String currencyCode);
}
