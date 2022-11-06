package com.iot.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerCard {
    private Integer id;
    private String customerName;
    private String customerSurname;
    private String cityName;
    private Integer discountInPercantage;
    private Integer companyCardId;
}
