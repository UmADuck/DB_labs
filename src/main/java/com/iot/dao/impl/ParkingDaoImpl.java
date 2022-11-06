package com.iot.dao.impl;

import com.iot.dao.ParkingDao;
import com.iot.domain.Parking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@SuppressWarnings("SqlResolve")
@Service
public class ParkingDaoImpl implements ParkingDao {

    private static final String FIND_ALL = "SELECT * FROM parking";
    private static final String CREATE = "INSERT parking(number_of_parking_spots) VALUES (?)";
    private static final String UPDATE = "UPDATE parking SET number_of_parking_spots=? WHERE id=?";
    private static final String DELETE = "DELETE FROM parking WHERE id=?";
    private static final String FIND_BY_ID = "SELECT * FROM parking WHERE id=?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Parking> findAll() {
        return jdbcTemplate.query(FIND_ALL, BeanPropertyRowMapper.newInstance(Parking.class));
    }

    @Override
    public Optional<Parking> findById(Integer id) {
        Optional<Parking> parking;
        try {
            parking = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_ID,
                    BeanPropertyRowMapper.newInstance(Parking.class), id));
        } catch (EmptyResultDataAccessException e) {
            parking = Optional.empty();
        }
        return parking;
    }

    @Override
    public int create(Parking entity) {
        return jdbcTemplate.update(CREATE, entity.getNumberOfParkingSpots());
    }

    @Override
    public int update(Integer id, Parking entity) {
        return jdbcTemplate.update(UPDATE, entity.getNumberOfParkingSpots(), id);
    }

    @Override
    public int delete(Integer id) {
        return jdbcTemplate.update(DELETE, id);
    }
}
