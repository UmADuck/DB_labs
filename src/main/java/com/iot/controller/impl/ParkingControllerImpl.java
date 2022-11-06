package com.iot.controller.impl;

import com.iot.controller.ParkingController;
import com.iot.domain.Parking;
import com.iot.service.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class ParkingControllerImpl implements ParkingController {

    @Autowired
    private ParkingService parkingService;

    @Override
    public List<Parking> findAll() {
        return parkingService.findAll();
    }

    @Override
    public Optional<Parking> findById(Integer id) {
        return parkingService.findById(id);
    }

    @Override
    public int create(Parking entity) {
        return parkingService.create(entity);
    }

    @Override
    public int update(Integer id, Parking entity) {
        return parkingService.update(id, entity);
    }

    @Override
    public int delete(Integer id) {
        return parkingService.delete(id);
    }
}
