package com.iot.exception;

public class ParkingTicketNotFoundException extends RuntimeException{
    public ParkingTicketNotFoundException(Integer id){
        super("Could not find 'parking_ticket' with id=" + id);
    }

}
