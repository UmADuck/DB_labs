package com.iot.service.impl;

import com.iot.dao.CustomerCardDao;
import com.iot.domain.CustomerCard;
import com.iot.service.CustomerCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerCardServiceImpl implements CustomerCardService {

    @Autowired
    private CustomerCardDao customerCardDao;

    @Override
    public List<CustomerCard> findAll() {
        return customerCardDao.findAll();
    }

    @Override
    public Optional<CustomerCard> findById(Integer id) {
        return customerCardDao.findById(id);
    }

    @Override
    public int create(CustomerCard entity) {
        return customerCardDao.create(entity);
    }

    @Override
    public int update(Integer id, CustomerCard entity) {
        return customerCardDao.update(id, entity);
    }

    @Override
    public int delete(Integer id) {
        return customerCardDao.delete(id);
    }
}
