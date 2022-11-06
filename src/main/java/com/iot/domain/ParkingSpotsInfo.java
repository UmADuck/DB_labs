package com.iot.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ParkingSpotsInfo {
    private Integer parkingSpotNumber;
    private Integer parkingId;
    private Boolean isBooked;
    private Integer bookingId;
    private Boolean is_free;
    private Integer parkingTicketId;
    private Data spotIsFreeSince = null;
}
