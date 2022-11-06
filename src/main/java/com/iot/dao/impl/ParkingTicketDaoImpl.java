package com.iot.dao.impl;

import com.iot.dao.ParkingTicketDao;
import com.iot.domain.Parking;
import com.iot.domain.ParkingTicket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@SuppressWarnings("SqlResolve")
@Service
public class ParkingTicketDaoImpl implements ParkingTicketDao {

    private static final String FIND_ALL = "SELECT * FROM parking_ticket";
    private static final String CREATE = "INSERT parking_ticket(parked_car_number_plate) VALUES (?)";
    private static final String UPDATE = "UPDATE parking_ticket SET parked_car_number_plate=? WHERE id=?";
    private static final String DELETE = "DELETE FROM parking_ticket WHERE id=?";
    private static final String FIND_BY_ID = "SELECT * FROM parking_ticket WHERE id=?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<ParkingTicket> findAll() {
        return jdbcTemplate.query(FIND_ALL, BeanPropertyRowMapper.newInstance(ParkingTicket.class));
    }

    @Override
    public Optional<ParkingTicket> findById(Integer id) {
        Optional<ParkingTicket> parkingTicket;
        try {
            parkingTicket = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_ID,
                    BeanPropertyRowMapper.newInstance(ParkingTicket.class), id));
        } catch (EmptyResultDataAccessException e) {
            parkingTicket = Optional.empty();
        }
        return parkingTicket;
    }

    @Override
    public int create(ParkingTicket entity) {
        return jdbcTemplate.update(CREATE, entity.getParkedCarNumberPlate());
    }

    @Override
    public int update(Integer id, ParkingTicket entity) {
        return jdbcTemplate.update(UPDATE, entity.getParkedCarNumberPlate(), id);
    }

    @Override
    public int delete(Integer id) {
        return jdbcTemplate.update(DELETE, id);
    }
}
