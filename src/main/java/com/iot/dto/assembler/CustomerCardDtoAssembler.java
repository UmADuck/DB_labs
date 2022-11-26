package com.iot.dto.assembler;


import com.iot.controller.CustomerCardController;
import com.iot.domain.CustomerCard;
import com.iot.dto.CustomerCardDto;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class CustomerCardDtoAssembler implements RepresentationModelAssembler<CustomerCard, CustomerCardDto> {
    @Override
    public CustomerCardDto toModel(CustomerCard entity) {
        CustomerCardDto customerCardDto = CustomerCardDto.builder()
                .id(entity.getId())
                .customerName(entity.getCustomerName())
                .customerName(entity.getCustomerName())
                .build();
        Link selfLink = linkTo(methodOn(CustomerCardController.class).getCustomerCard(customerCardDto.getId())).withSelfRel();
        customerCardDto.add(selfLink);
        return customerCardDto;
    }

    @Override
    public CollectionModel<CustomerCardDto> toCollectionModel(Iterable<? extends CustomerCard> entities) {
        CollectionModel<CustomerCardDto> customerCardDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(CustomerCardController.class).getAllCustomerCards()).withSelfRel();
        customerCardDtos.add(selfLink);
        return customerCardDtos;
    }

    public CollectionModel<CustomerCardDto> toCollectionModel(Iterable<? extends CustomerCard> entities, Link link) {
        CollectionModel<CustomerCardDto> customerCardDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        customerCardDtos.add(link);
        return customerCardDtos;
    }
}