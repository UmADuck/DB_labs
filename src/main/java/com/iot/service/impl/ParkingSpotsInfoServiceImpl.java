package com.iot.service.impl;

import com.iot.dao.ParkingSpotsInfoDao;
import com.iot.domain.ParkingSpotsInfo;
import com.iot.service.ParkingSpotsInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParkingSpotsInfoServiceImpl implements ParkingSpotsInfoService {

    @Autowired
    private ParkingSpotsInfoDao parkingSpotsInfoDao;

    @Override
    public List<ParkingSpotsInfo> findAll() {
        return parkingSpotsInfoDao.findAll();
    }

    @Override
    public Optional<ParkingSpotsInfo> findById(Integer id) {
        return parkingSpotsInfoDao.findById(id);
    }

    @Override
    public int create(ParkingSpotsInfo entity) {
        return parkingSpotsInfoDao.create(entity);
    }

    @Override
    public int update(Integer id, ParkingSpotsInfo entity) {
        return parkingSpotsInfoDao.update(id, entity);
    }

    @Override
    public int delete(Integer id) {
        return parkingSpotsInfoDao.delete(id);
    }
}
