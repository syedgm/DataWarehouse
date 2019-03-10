package com.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.warehouse.model.InvalidDeals;
import com.warehouse.repository.custom.InvalidDealRepositoryCustom;

@Repository
public interface InvalidDealsRepository extends JpaRepository<InvalidDeals, Long>, InvalidDealRepositoryCustom {

	
}
