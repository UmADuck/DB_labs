package com.iot.sevice.impl;

import com.iot.domain.CompanyCard;
import com.iot.exception.CompanyCardNotFoundException;
import com.iot.repository.CompanyCardRepository;
import com.iot.repository.CustomerCardRepository;
import com.iot.sevice.CompanyCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CompanyCardServiceImpl implements CompanyCardService {

    @Autowired
    CompanyCardRepository companyCardRepository;
    @Autowired
    CustomerCardRepository customerCardRepository;

    public List<CompanyCard> findAll() {
        return companyCardRepository.findAll();
    }

    public CompanyCard findById(Integer id) {
        return companyCardRepository.findById(id)
                .orElseThrow(() -> new CompanyCardNotFoundException(id));
    }

    @Transactional
    public CompanyCard create(CompanyCard entity) {
        companyCardRepository.save(entity);
        return entity;
    }

    @Transactional
    public void update(Integer id, CompanyCard entity) {
        CompanyCard companyCard = companyCardRepository.findById(id)
                .orElseThrow(() -> new CompanyCardNotFoundException(id));
        //update
        companyCard.setId(entity.getId());
        companyCard.setCompanyName(entity.getCompanyName());

        companyCardRepository.save(companyCard);
    }

    @Transactional
    public void delete(Integer id) {
        CompanyCard companyCard = companyCardRepository.findById(id)
                .orElseThrow(() -> new CompanyCardNotFoundException(id));
        companyCardRepository.delete(companyCard);
    }
}
