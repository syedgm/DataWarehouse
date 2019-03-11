package com.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.warehouse.model.ValidDeals;
import com.warehouse.repository.custom.ValidDealRepositoryCustom;

@Repository
public interface ValidDealsRepository extends JpaRepository<ValidDeals, Long>, ValidDealRepositoryCustom {

	ValidDeals findByDealId(@Param("dealId") String dealId);
	
	
}
