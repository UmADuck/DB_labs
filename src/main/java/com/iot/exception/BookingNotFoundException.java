package com.iot.exception;

public class BookingNotFoundException extends RuntimeException{
    public BookingNotFoundException(Integer id){
        super("Could not find 'booking' with id=" + id);
    }
}
