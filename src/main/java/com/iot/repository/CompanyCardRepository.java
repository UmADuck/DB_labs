package com.iot.repository;

import com.iot.domain.CompanyCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyCardRepository extends JpaRepository<CompanyCard, Integer> {
}
