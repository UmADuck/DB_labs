package com.iot.controller;

import com.iot.domain.ParkingTicket;
import com.iot.dto.ParkingTicketDto;
import com.iot.dto.assembler.ParkingTicketDtoAssembler;
import com.iot.sevice.ParkingTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/tickets")
public class ParkingTicketController {

    @Autowired
    private ParkingTicketService parkingTicketService;
    @Autowired
    private ParkingTicketDtoAssembler parkingTicketDtoAssembler;

    @GetMapping(value = "/{parkingTicketId}")
    public ResponseEntity<ParkingTicketDto> getBooking(@PathVariable Integer parkingTicketId) {
        ParkingTicket parkingTicket = parkingTicketService.findById(parkingTicketId);
        ParkingTicketDto parkingTicketDto = parkingTicketDtoAssembler.toModel(parkingTicket);
        return new ResponseEntity<>(parkingTicketDto, HttpStatus.OK);
    }

    @GetMapping(value = "")
    public ResponseEntity<CollectionModel<ParkingTicketDto>> getAllBooks() {
        List<ParkingTicket> parkingTickets = parkingTicketService.findAll();
        CollectionModel<ParkingTicketDto> parkingTicketDtos = parkingTicketDtoAssembler.toCollectionModel(parkingTickets);
        return new ResponseEntity<>(parkingTicketDtos, HttpStatus.OK);
    }


    @PostMapping(value = "")
    public ResponseEntity<ParkingTicketDto> addBook(@RequestBody ParkingTicket parkingTicket) {
        ParkingTicket newParkingTicket = parkingTicketService.create(parkingTicket);
        ParkingTicketDto parkingTicketDto = parkingTicketDtoAssembler.toModel(newParkingTicket);
        return new ResponseEntity<>(parkingTicketDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{parkingTicketId}")
    public ResponseEntity<?> updateBook(@RequestBody ParkingTicket uParkingTicket, @PathVariable Integer parkingTicketId) {
        parkingTicketService.update(parkingTicketId, uParkingTicket);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{parkingTicketId}")
    public ResponseEntity<?> deleteBook(@PathVariable Integer parkingTicketId) {
        parkingTicketService.delete(parkingTicketId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
