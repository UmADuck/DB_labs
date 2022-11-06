package com.iot.service.impl;

import com.iot.dao.CityDao;
import com.iot.domain.City;
import com.iot.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityDao cityDao;

    @Override
    public Optional<City> findByRegion(String regionName) {
        return cityDao.findByRegion(regionName);
    }

    @Override
    public Optional<City> findByCityName(String cityName) {
        return cityDao.findByCityName(cityName);
    }

    @Override
    public List<City> findAll() {
        return cityDao.findAll();
    }

    @Override
    public Optional<City> findById(String s) {
        return cityDao.findById(s);
    }

    @Override
    public int create(City entity) {
        return cityDao.create(entity);
    }

    @Override
    public int update(String cityName, City entity) {
        return cityDao.update(cityName, entity);
    }

    @Override
    public int delete(String cityName) {
        return cityDao.delete(cityName);
    }
}
