package com.iot.controller;

import com.iot.domain.Booking;

import java.util.Optional;

public interface BookingController extends GeneralController<Booking, Integer>{

Optional<Booking> findByCustomerIdCard(Integer id);

}
