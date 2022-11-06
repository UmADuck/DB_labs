package com.iot.dao;

import com.iot.domain.CompanyCard;

import java.util.Optional;

public interface CompanyCardDao extends GeneralDao<CompanyCard, Integer>{

    Optional<CompanyCard> findByCompanyName(String companyName);
}
