package com.iot.controller;


import com.iot.domain.Booking;
import com.iot.dto.BookingDto;
import com.iot.dto.assembler.BookingDtoAssembler;
import com.iot.sevice.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;
    @Autowired
    private BookingDtoAssembler bookingDtoAssembler;

    @GetMapping(value = "/{bookingId}")
    public ResponseEntity<BookingDto> getBooking(@PathVariable Integer bookingId) {
        Booking booking = bookingService.findById(bookingId);
        BookingDto bookingDto = bookingDtoAssembler.toModel(booking);
        return new ResponseEntity<>(bookingDto, HttpStatus.OK);
    }

    @GetMapping(value = "")
    public ResponseEntity<CollectionModel<BookingDto>> getAllBooks() {
        List<Booking> books = bookingService.findAll();
        CollectionModel<BookingDto> bookingDtos = bookingDtoAssembler.toCollectionModel(books);
        return new ResponseEntity<>(bookingDtos, HttpStatus.OK);
    }


    @PostMapping(value = "")
    public ResponseEntity<BookingDto> addBook(@RequestBody Booking booking) {
        Booking newBooking = bookingService.create(booking);
        BookingDto bookingDto = bookingDtoAssembler.toModel(newBooking);
        return new ResponseEntity<>(bookingDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{bookingId}")
    public ResponseEntity<?> updateBook(@RequestBody Booking uBooking, @PathVariable Integer bookingId) {
        bookingService.update(bookingId, uBooking);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{bookingId}")
    public ResponseEntity<?> deleteBook(@PathVariable Integer bookingId) {
        bookingService.delete(bookingId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
