package com.iot.dao;

import com.iot.domain.City;

import java.util.Optional;

public interface CityDao extends GeneralDao<City, String> {

    Optional<City> findByRegion(String regionName);

    Optional<City> findByCityName(String cityName);
}
