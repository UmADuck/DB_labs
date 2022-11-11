package com.iot.dto.assembler;


import com.iot.controller.BookingController;
import com.iot.domain.Booking;
import com.iot.dto.BookingDto;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class BookingDtoAssembler implements RepresentationModelAssembler<Booking, BookingDto> {

    @Override
    public BookingDto toModel(Booking entity) {
        BookingDto bookingDto = BookingDto.builder()
                .id(entity.getId())
                .parkedCarNumberPlates(entity.getParkedCarNumberPlate())
                .build();
        Link selfLink = linkTo(methodOn(BookingController.class).getBooking(bookingDto.getId())).withSelfRel();
        bookingDto.add(selfLink);
        return bookingDto;
    }

    @Override
    public CollectionModel<BookingDto> toCollectionModel(Iterable<? extends Booking> entities) {
        CollectionModel<BookingDto> bookingDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(BookingController.class).getAllBooks()).withSelfRel();
        bookingDtos.add(selfLink);
        return bookingDtos;
    }

    public CollectionModel<BookingDto> toCollectionModel(Iterable<? extends Booking> entities, Link link) {
        CollectionModel<BookingDto> bookingDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        bookingDtos.add(link);
        return bookingDtos;
    }
}
