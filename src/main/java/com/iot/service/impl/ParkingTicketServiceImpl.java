package com.iot.service.impl;

import com.iot.dao.ParkingTicketDao;
import com.iot.domain.ParkingTicket;
import com.iot.service.ParkingTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParkingTicketServiceImpl implements ParkingTicketService {

    @Autowired
    private ParkingTicketDao parkingTicketDao;

    @Override
    public List<ParkingTicket> findAll() {
        return parkingTicketDao.findAll();
    }

    @Override
    public Optional<ParkingTicket> findById(Integer id) {
        return parkingTicketDao.findById(id);
    }

    @Override
    public int create(ParkingTicket entity) {
        return parkingTicketDao.create(entity);
    }

    @Override
    public int update(Integer id, ParkingTicket entity) {
        return parkingTicketDao.update(id, entity);
    }

    @Override
    public int delete(Integer id) {
        return parkingTicketDao.delete(id);
    }
}
