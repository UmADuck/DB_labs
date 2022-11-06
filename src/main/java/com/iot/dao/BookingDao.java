package com.iot.dao;

import com.iot.domain.Booking;

import java.util.Optional;

public interface BookingDao extends GeneralDao<Booking, Integer> {

    Optional<Booking> findByCustomerIdCard(Integer id);
}
