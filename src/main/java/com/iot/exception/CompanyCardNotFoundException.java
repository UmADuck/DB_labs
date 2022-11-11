package com.iot.exception;

public class CompanyCardNotFoundException extends RuntimeException{
    public CompanyCardNotFoundException(Integer id){
        super("Could not find 'company_card' with id=" + id);
    }

}
