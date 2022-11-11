package com.iot.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;


@Builder
@Getter
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Relation(itemRelation = "companyCard", collectionRelation = "companyCards")
public class CustomerCardDto extends RepresentationModel<CustomerCardDto> {
    private final Integer id;
    private final String customerName;
    private final String customerSurname;
}