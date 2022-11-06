package com.iot.controller.impl;

import com.iot.controller.ParkingTicketController;
import com.iot.domain.ParkingTicket;
import com.iot.service.ParkingTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class ParkingTicketControllerImpl implements ParkingTicketController {

    @Autowired
    private ParkingTicketService parkingTicketService;

    @Override
    public List<ParkingTicket> findAll() {
        return parkingTicketService.findAll();
    }

    @Override
    public Optional<ParkingTicket> findById(Integer id) {
        return parkingTicketService.findById(id);
    }

    @Override
    public int create(ParkingTicket entity) {
        return parkingTicketService.create(entity);
    }

    @Override
    public int update(Integer id, ParkingTicket entity) {
        return parkingTicketService.update(id, entity);
    }

    @Override
    public int delete(Integer id) {
        return parkingTicketService.delete(id);
    }
}
