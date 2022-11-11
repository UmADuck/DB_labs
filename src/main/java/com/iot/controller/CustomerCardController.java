package com.iot.controller;

import com.iot.domain.CustomerCard;
import com.iot.dto.CustomerCardDto;
import com.iot.dto.assembler.CustomerCardDtoAssembler;
import com.iot.sevice.CustomerCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/customers")
public class CustomerCardController {


    @Autowired
    private CustomerCardService customerCardService;
    @Autowired
    private CustomerCardDtoAssembler customerCardDtoAssembler;

    @GetMapping(value = "/{customerCardId}")
    public ResponseEntity<CustomerCardDto> getCustomerCard(@PathVariable Integer customerCardId) {
        CustomerCard customerCard = customerCardService.findById(customerCardId);
        CustomerCardDto customerCardDto = customerCardDtoAssembler.toModel(customerCard);
        return new ResponseEntity<>(customerCardDto, HttpStatus.OK);
    }

    @GetMapping(value = "")
    public ResponseEntity<CollectionModel<CustomerCardDto>> getAllCustomerCards() {
        List<CustomerCard> customerCards = customerCardService.findAll();
        CollectionModel<CustomerCardDto> customerCardDtos = customerCardDtoAssembler.toCollectionModel(customerCards);
        return new ResponseEntity<>(customerCardDtos, HttpStatus.OK);
    }


    @PostMapping(value = "")
    public ResponseEntity<CustomerCardDto> addCustomerCard(@RequestBody CustomerCard customerCard) {
        CustomerCard newCustomerCard = customerCardService.create(customerCard);
        CustomerCardDto customerCardDto = customerCardDtoAssembler.toModel(newCustomerCard);
        return new ResponseEntity<>(customerCardDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{customerCardId}")
    public ResponseEntity<?> updateCustomerCard(@RequestBody CustomerCard uCustomerCard, @PathVariable Integer customerCardId) {
        customerCardService.update(customerCardId, uCustomerCard);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{customerCardId}")
    public ResponseEntity<?> deleteCustomerCard(@PathVariable Integer customerCardId) {
        customerCardService.delete(customerCardId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

