package com.iot.dao.impl;

import com.iot.dao.BookingDao;
import com.iot.domain.Booking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("SqlResolve")
@Service
public class BookingDaoImpl implements BookingDao {

    private static final String FIND_ALL = "SELECT * FROM booking";
    private static final String CREATE = "INSERT booking(customer_card_id, parked_car_number_plate) VALUES (?, ?)";
    private static final String UPDATE = "UPDATE book SET customer_card_id=?, parked_car_number_plate=? WHERE id=?";
    private static final String DELETE = "DELETE FROM booking WHERE id=?";
    private static final String FIND_BY_ID = "SELECT * FROM booking WHERE id=?";
    private static final String FIND_BY_CUSTOMER_ID = "SELECT * FROM book UNION customer_card WHERE customer.id=?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Optional<Booking> findByCustomerIdCard(Integer id) {
        Optional<Booking> booking;
        try {
            booking = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_CUSTOMER_ID,
                    BeanPropertyRowMapper.newInstance(Booking.class), id));
        } catch (EmptyResultDataAccessException e) {
            booking = Optional.empty();
        }
        return booking;
    }

    @Override
    public List<Booking> findAll() {
        return jdbcTemplate.query(FIND_ALL, BeanPropertyRowMapper.newInstance(Booking.class));
    }

    @Override
    public Optional<Booking> findById(Integer id) {
        Optional<Booking> booking;
        try {
            booking = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_ID,
                    BeanPropertyRowMapper.newInstance(Booking.class), id));
        } catch (EmptyResultDataAccessException e) {
            booking = Optional.empty();
        }
        return booking;
    }

    @Override
    public int create(Booking entity) {
        return jdbcTemplate.update(CREATE, entity.getCustomerCardId(), entity.getParkedCarNumberPlate());
    }

    @Override
    public int update(Integer id, Booking entity) {
        return jdbcTemplate.update(UPDATE, entity.getCustomerCardId(), entity.getParkedCarNumberPlate(), id);
    }

    @Override
    public int delete(Integer id) {
        return jdbcTemplate.update(DELETE, id);
    }
}
