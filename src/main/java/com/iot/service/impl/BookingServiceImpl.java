package com.iot.service.impl;

import com.iot.dao.BookingDao;
import com.iot.domain.Booking;
import com.iot.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingDao bookingDao;

    @Override
    public Optional<Booking> findByCustomerIdCard(Integer id) {
        return bookingDao.findByCustomerIdCard(id);
    }

    @Override
    public List<Booking> findAll() {
        return bookingDao.findAll();
    }

    @Override
    public Optional<Booking> findById(Integer id) {
        return bookingDao.findById(id);
    }

    @Override
    public int create(Booking entity) {
        return bookingDao.create(entity);
    }

    @Override
    public int update(Integer id, Booking entity) {
        return bookingDao.update(id, entity);
    }

    @Override
    public int delete(Integer id) {
        return bookingDao.delete(id);
    }
}
