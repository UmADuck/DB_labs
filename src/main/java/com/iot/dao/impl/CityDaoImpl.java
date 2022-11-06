package com.iot.dao.impl;

import com.iot.dao.CityDao;
import com.iot.domain.City;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("SqlResolve")
@Service
public class CityDaoImpl implements CityDao {

    private static final String FIND_ALL = "SELECT * FROM city";
    private static final String CREATE = "INSERT city(region_name, name) VALUES (?, ?)";
    private static final String UPDATE = "UPDATE city SET region_name=?, name=? WHERE name=?";
    private static final String DELETE = "DELETE FROM city WHERE name=?";
    private static final String FIND_BY_REGION = "SELECT * FROM city WHERE region_name=?";
    private static final String FIND_BY_CITY_NAME = "SELECT * FROM city WHERE name=?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<City> findAll() {
        return jdbcTemplate.query(FIND_ALL, BeanPropertyRowMapper.newInstance(City.class));
    }

    @Override
    public Optional<City> findById(String s) {
        return Optional.empty();
    }

    @Override
    public int create(City entity) {
        return jdbcTemplate.update(CREATE, entity.getRegionName(), entity.getName());
    }

    @Override
    public int update(String cityName, City entity) {
        return jdbcTemplate.update(UPDATE, entity.getRegionName(), entity.getName(), cityName);
    }

    @Override
    public int delete(String cityName) {
        return jdbcTemplate.update(DELETE, cityName);
    }

    @Override
    public Optional<City> findByRegion(String regionName) {
        Optional<City> city;
        try {
            city = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_REGION,
                    BeanPropertyRowMapper.newInstance(City.class), regionName));
        } catch (EmptyResultDataAccessException e) {
            city = Optional.empty();
        }
        return city;
    }

    @Override
    public Optional<City> findByCityName(String cityName) {
        Optional<City> city;
        try {
            city = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_CITY_NAME,
                    BeanPropertyRowMapper.newInstance(City.class), cityName));
        } catch (EmptyResultDataAccessException e) {
            city = Optional.empty();
        }
        return city;
    }
}
