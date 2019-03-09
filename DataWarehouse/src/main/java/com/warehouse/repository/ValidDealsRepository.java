package com.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.warehouse.model.InvalidDeals;

@Repository
public interface ValidDealsRepository extends JpaRepository<InvalidDeals, Long>{

}
