package com.iot.controller;

import com.iot.domain.CompanyCard;

import java.util.Optional;

public interface CompanyCardController extends GeneralController<CompanyCard, Integer>{

    Optional<CompanyCard> findByCompanyName(String companyName);
}
