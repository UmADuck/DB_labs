package com.iot.repository;

import com.iot.domain.CustomerCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerCardRepository extends JpaRepository<CustomerCard, Integer> {
}
