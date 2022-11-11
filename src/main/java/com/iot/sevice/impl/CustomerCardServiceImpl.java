package com.iot.sevice.impl;


import com.iot.domain.CustomerCard;
import com.iot.exception.CustomerCardNotFoundException;
import com.iot.repository.BookingRepository;
import com.iot.repository.CompanyCardRepository;
import com.iot.repository.CustomerCardRepository;
import com.iot.repository.ParkingTicketRepository;
import com.iot.sevice.CustomerCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CustomerCardServiceImpl implements CustomerCardService {

    @Autowired
    CompanyCardRepository companyCardRepository;
    @Autowired
    CustomerCardRepository customerCardRepository;
    @Autowired
    BookingRepository bookingRepository;
    @Autowired
    ParkingTicketRepository parkingTicketRepository;

    public List<CustomerCard> findAll() {
        return customerCardRepository.findAll();
    }

    public CustomerCard findById(Integer id) {
        return customerCardRepository.findById(id)
                .orElseThrow(() -> new CustomerCardNotFoundException(id));
    }

    @Transactional
    public CustomerCard create(CustomerCard entity) {
        customerCardRepository.save(entity);
        return entity;
    }

    @Transactional
    public void update(Integer id, CustomerCard entity) {
        CustomerCard customerCard = customerCardRepository.findById(id)
                .orElseThrow(() -> new CustomerCardNotFoundException(id));
        //update
        customerCard.setId(entity.getId());
        customerCard.setCustomerName(entity.getCustomerName());
        customerCard.setCustomerSurname(entity.getCustomerSurname());

        customerCardRepository.save(customerCard);
    }

    @Transactional
    public void delete(Integer id) {
        CustomerCard customerCard = customerCardRepository.findById(id)
                .orElseThrow(() -> new CustomerCardNotFoundException(id));
        customerCardRepository.delete(customerCard);
    }
}

