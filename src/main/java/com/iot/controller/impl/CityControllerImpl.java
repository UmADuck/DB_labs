package com.iot.controller.impl;

import com.iot.controller.CityController;
import com.iot.domain.City;
import com.iot.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class CityControllerImpl implements CityController {

    @Autowired
    private CityService cityService;

    @Override
    public Optional<City> findByRegion(String regionName) {
        return cityService.findByRegion(regionName);
    }

    @Override
    public Optional<City> findByCityName(String cityName) {
        return cityService.findByCityName(cityName);
    }

    @Override
    public List<City> findAll() {
        return cityService.findAll();
    }

    @Override
    public Optional<City> findById(String s) {
        return null;
    }

    @Override
    public int create(City entity) {
        return cityService.create(entity);
    }

    @Override
    public int update(String newCityName, City entity) {
        return cityService.update(newCityName, entity);
    }

    @Override
    public int delete(String cityName) {
        return cityService.delete(cityName);
    }
}
