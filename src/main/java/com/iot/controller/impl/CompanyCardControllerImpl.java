package com.iot.controller.impl;

import com.iot.controller.CompanyCardController;
import com.iot.domain.CompanyCard;
import com.iot.service.CompanyCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class CompanyCardControllerImpl implements CompanyCardController {

    @Autowired
    private CompanyCardService companyCardService;

    @Override
    public Optional<CompanyCard> findByCompanyName(String companyName) {
        return companyCardService.findByCompanyName(companyName);
    }

    @Override
    public List<CompanyCard> findAll() {
        return companyCardService.findAll();
    }

    @Override
    public Optional<CompanyCard> findById(Integer id) {
        return companyCardService.findById(id);
    }

    @Override
    public int create(CompanyCard entity) {
        return companyCardService.create(entity);
    }

    @Override
    public int update(Integer id, CompanyCard entity) {
        return companyCardService.update(id, entity);
    }

    @Override
    public int delete(Integer id) {
        return companyCardService.delete(id);
    }
}
