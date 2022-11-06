package com.iot.controller.impl;

import com.iot.controller.CustomerCardController;
import com.iot.domain.CustomerCard;
import com.iot.service.CustomerCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class CustomerCardControllerImpl implements CustomerCardController {

    @Autowired
    private CustomerCardService customerCardService;

    @Override
    public List<CustomerCard> findAll() {
        return customerCardService.findAll();
    }

    @Override
    public Optional<CustomerCard> findById(Integer id) {
        return customerCardService.findById(id);
    }

    @Override
    public int create(CustomerCard entity) {
        return customerCardService.create(entity);
    }

    @Override
    public int update(Integer id, CustomerCard entity) {
        return customerCardService.update(id, entity);
    }

    @Override
    public int delete(Integer id) {
        return customerCardService.delete(id);
    }
}
