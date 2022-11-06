package com.iot.service.impl;

import com.iot.dao.CompanyCardDao;
import com.iot.domain.CompanyCard;
import com.iot.service.CompanyCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyCardServiceImpl implements CompanyCardService {

    @Autowired
    private CompanyCardDao companyCardDao;

    @Override
    public Optional<CompanyCard> findByCompanyName(String companyName) {
        return companyCardDao.findByCompanyName(companyName);
    }

    @Override
    public List<CompanyCard> findAll() {
        return companyCardDao.findAll();
    }

    @Override
    public Optional<CompanyCard> findById(Integer id) {
        return companyCardDao.findById(id);
    }

    @Override
    public int create(CompanyCard entity) {
        return companyCardDao.create(entity);
    }

    @Override
    public int update(Integer id, CompanyCard entity) {
        return companyCardDao.update( id, entity);
    }

    @Override
    public int delete(Integer id) {
        return companyCardDao.delete(id);
    }
}
