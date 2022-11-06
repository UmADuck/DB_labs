package com.iot.domain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Parking {
    private Integer id;
    private String numberOfParkingSpots;
    private String companyOwnerName;
    private String occupiedParkingSpots;
}
