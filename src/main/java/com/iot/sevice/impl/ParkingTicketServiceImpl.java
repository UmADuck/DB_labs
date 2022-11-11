package com.iot.sevice.impl;

import com.iot.domain.CustomerCard;
import com.iot.domain.ParkingTicket;
import com.iot.exception.ParkingTicketNotFoundException;
import com.iot.repository.CustomerCardRepository;
import com.iot.repository.ParkingTicketRepository;
import com.iot.sevice.ParkingTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ParkingTicketServiceImpl implements ParkingTicketService {

    @Autowired
    CustomerCardRepository customerCardRepository;
    @Autowired
    ParkingTicketRepository parkingTicketRepository;

    public List<ParkingTicket> findAll() {
        return parkingTicketRepository.findAll();
    }

    public ParkingTicket findById(Integer id) {
        return parkingTicketRepository.findById(id)
                .orElseThrow(() -> new ParkingTicketNotFoundException(id));
    }

    @Transactional
    public ParkingTicket create(ParkingTicket entity) {
        parkingTicketRepository.save(entity);
        return entity;
    }

    @Transactional
    public void update(Integer id, ParkingTicket entity) {
        ParkingTicket parkingTicket = parkingTicketRepository.findById(id)
                .orElseThrow(() -> new ParkingTicketNotFoundException(id));
        //update
        parkingTicket.setId(entity.getId());
        parkingTicket.setParkedCarNumberPlate(entity.getParkedCarNumberPlate());

        parkingTicketRepository.save(parkingTicket);
    }

    @Transactional
    public void delete(Integer id) {
        ParkingTicket parkingTicket = parkingTicketRepository.findById(id)
                .orElseThrow(() -> new ParkingTicketNotFoundException(id));
        parkingTicketRepository.delete(parkingTicket);
    }
}
