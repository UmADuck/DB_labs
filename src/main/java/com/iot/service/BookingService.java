package com.iot.service;

import com.iot.domain.Booking;

import java.util.Optional;

public interface BookingService extends GeneralService<Booking, Integer>{

    Optional<Booking> findByCustomerIdCard(Integer id);
}
