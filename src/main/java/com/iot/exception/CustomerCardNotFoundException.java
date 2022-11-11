package com.iot.exception;

public class CustomerCardNotFoundException extends RuntimeException{
    public CustomerCardNotFoundException(Integer id){
        super("Could not find 'customer_card' with id=" + id);
    }

}
