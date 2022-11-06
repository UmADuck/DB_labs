package com.iot.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ParkingTicket {
    private Integer id;
    private String  parkedCarNumberPlate;
    private Data spotOccupationTime = null;
    private Integer customerCardId;
}
