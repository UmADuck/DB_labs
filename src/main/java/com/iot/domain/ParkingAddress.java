package com.iot.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ParkingAddress {
    private Integer id;
    private String cityName;
    private String streetName;
    private String streetNumber;
}
