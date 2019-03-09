package com.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.warehouse.model.ValidDeals;

@Repository
public interface InvalidDealsRepository extends JpaRepository<ValidDeals, Long>{

}
