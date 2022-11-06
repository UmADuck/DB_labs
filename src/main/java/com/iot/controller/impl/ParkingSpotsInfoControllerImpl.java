package com.iot.controller.impl;

import com.iot.controller.ParkingSpotsInfoController;
import com.iot.domain.ParkingSpotsInfo;
import com.iot.service.BookingService;
import com.iot.service.ParkingSpotsInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class ParkingSpotsInfoControllerImpl implements ParkingSpotsInfoController {

    @Autowired
    private ParkingSpotsInfoService parkingSpotsInfoService;

    @Override
    public List<ParkingSpotsInfo> findAll() {
        return parkingSpotsInfoService.findAll();
    }

    @Override
    public Optional<ParkingSpotsInfo> findById(Integer id) {
        return parkingSpotsInfoService.findById(id);
    }

    @Override
    public int create(ParkingSpotsInfo entity) {
        return parkingSpotsInfoService.create(entity);
    }

    @Override
    public int update(Integer id, ParkingSpotsInfo entity) {
        return parkingSpotsInfoService.update(id, entity);
    }

    @Override
    public int delete(Integer id) {
        return parkingSpotsInfoService.delete(id);
    }
}
