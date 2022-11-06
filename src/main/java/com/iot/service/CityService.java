package com.iot.service;


import com.iot.domain.City;

import java.util.Optional;

public interface CityService extends GeneralService<City, String>{

    Optional<City> findByRegion(String regionName);

    Optional<City> findByCityName(String cityName);
}
