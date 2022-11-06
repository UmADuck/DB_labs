package com.iot.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CompanyAdditionalInfo {
    private Integer companyId;
    private String streetName;
    private String streetNumber;
    private Integer numberOfWorkers;
    private String companyBankNumber;
    private String cityName;

}
