package com.iot.controller.impl;

import com.iot.controller.BookingController;
import com.iot.domain.Booking;
import com.iot.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class BookingControllerImpl implements BookingController {

    @Autowired
    private BookingService bookingService;

    @Override
    public Optional<Booking> findByCustomerIdCard(Integer id) {
        return bookingService.findByCustomerIdCard(id);
    }

    @Override
    public List<Booking> findAll() {
        return bookingService.findAll();
    }

    @Override
    public Optional<Booking> findById(Integer id) {
        return bookingService.findById(id);
    }

    @Override
    public int create(Booking entity) {
        return bookingService.create(entity);
    }

    @Override
    public int update(Integer id, Booking entity) {
        return bookingService.update(id, entity);
    }

    @Override
    public int delete(Integer id) {
        return bookingService.delete(id);
    }
}
