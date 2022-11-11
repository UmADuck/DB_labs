package com.iot.sevice.impl;

import com.iot.domain.Booking;
import com.iot.exception.BookingNotFoundException;
import com.iot.repository.BookingRepository;
import com.iot.repository.CustomerCardRepository;
import com.iot.sevice.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    BookingRepository bookingRepository;
    @Autowired
    CustomerCardRepository customerCardRepository;

    public List<Booking> findAll() {
        return bookingRepository.findAll();
    }

    public Booking findById(Integer id) {
        return bookingRepository.findById(id)
                .orElseThrow(() -> new BookingNotFoundException(id));
    }

    @Transactional
    public Booking create(Booking entity) {
        bookingRepository.save(entity);
    return entity;
    }

    @Transactional
    public void update(Integer id, Booking entity) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new BookingNotFoundException(id));
        //update
        booking.setId(entity.getId());
        booking.setParkedCarNumberPlate(entity.getParkedCarNumberPlate());

        bookingRepository.save(booking);
    }

    @Transactional
    public void delete(Integer id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new BookingNotFoundException(id));
        bookingRepository.delete(booking);
    }
}

