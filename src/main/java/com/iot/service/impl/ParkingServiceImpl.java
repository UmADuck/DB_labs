package com.iot.service.impl;

import com.iot.dao.ParkingDao;
import com.iot.domain.Parking;
import com.iot.service.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParkingServiceImpl implements ParkingService {

    @Autowired
    private ParkingDao parkingDao;


    @Override
    public List<Parking> findAll() {
        return parkingDao.findAll();
    }

    @Override
    public Optional<Parking> findById(Integer id) {
        return parkingDao.findById(id);
    }

    @Override
    public int create(Parking entity) {
        return parkingDao.create(entity);
    }

    @Override
    public int update(Integer id, Parking entity) {
        return parkingDao.update(id, entity);
    }

    @Override
    public int delete(Integer id) {
        return parkingDao.delete(id);
    }
}
