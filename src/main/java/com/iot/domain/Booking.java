package com.iot.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Booking {
    private Integer id;
    private Data bookingDatetime = null;
    private Integer customerCardId;
    private String parkedCarNumberPlate;
}
