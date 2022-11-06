package com.iot.controller;

import com.iot.domain.City;

import java.util.Optional;

public interface CityController extends GeneralController<City, String> {

    Optional<City> findByRegion(String regionName);

    Optional<City> findByCityName(String cityName);
}
