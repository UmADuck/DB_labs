package com.iot.service;


import com.iot.domain.CompanyCard;

import java.util.Optional;

public interface CompanyCardService extends GeneralService<CompanyCard, Integer>{

    Optional<CompanyCard> findByCompanyName(String companyName);
}
