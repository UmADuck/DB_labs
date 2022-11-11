package com.iot.dto.assembler;


import com.iot.controller.ParkingTicketController;
import com.iot.domain.ParkingTicket;
import com.iot.dto.ParkingTicketDto;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class ParkingTicketDtoAssembler implements RepresentationModelAssembler<ParkingTicket, ParkingTicketDto> {


    @Override
    public ParkingTicketDto toModel(ParkingTicket entity) {
        ParkingTicketDto parkingTicketDto = ParkingTicketDto.builder()
                .id(entity.getId())
                .parkedCarNumberPlates(entity.getParkedCarNumberPlate())
                .build();
        Link selfLink = linkTo(methodOn(ParkingTicketController.class).getBooking(parkingTicketDto.getId())).withSelfRel();
        parkingTicketDto.add(selfLink);
        return parkingTicketDto;
    }

    @Override
    public CollectionModel<ParkingTicketDto> toCollectionModel(Iterable<? extends ParkingTicket> entities) {
        CollectionModel<ParkingTicketDto> parkingTicketDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(ParkingTicketController.class).getAllBooks()).withSelfRel();
        parkingTicketDtos.add(selfLink);
        return parkingTicketDtos;
    }

    public CollectionModel<ParkingTicketDto> toCollectionModel(Iterable<? extends ParkingTicket> entities, Link link) {
        CollectionModel<ParkingTicketDto> parkingTicketDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        parkingTicketDtos.add(link);
        return parkingTicketDtos;
    }
}
