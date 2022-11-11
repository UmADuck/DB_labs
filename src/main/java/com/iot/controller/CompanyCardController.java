package com.iot.controller;


import com.iot.domain.CompanyCard;
import com.iot.dto.CompanyCardDto;
import com.iot.dto.assembler.CompanyCardDtoAssembler;
import com.iot.sevice.CompanyCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/companies")
public class CompanyCardController {

    @Autowired
    private CompanyCardService companyCardService;
    @Autowired
    private CompanyCardDtoAssembler companyCardDtoAssembler;

    @GetMapping(value = "/{companyCardId}")
    public ResponseEntity<CompanyCardDto> getCompanyCard(@PathVariable Integer companyCardId) {
        CompanyCard companyCard = companyCardService.findById(companyCardId);
        CompanyCardDto companyCardDto = companyCardDtoAssembler.toModel(companyCard);
        return new ResponseEntity<>(companyCardDto, HttpStatus.OK);
    }

    @GetMapping(value = "")
    public ResponseEntity<CollectionModel<CompanyCardDto>> getAllCompanyCards() {
        List<CompanyCard> companyCards = companyCardService.findAll();
        CollectionModel<CompanyCardDto> companyCardDtos = companyCardDtoAssembler.toCollectionModel(companyCards);
        return new ResponseEntity<>(companyCardDtos, HttpStatus.OK);
    }


    @PostMapping(value = "")
    public ResponseEntity<CompanyCardDto> addCompanyCard(@RequestBody CompanyCard companyCard) {
        CompanyCard newCompanyCard = companyCardService.create(companyCard);
        CompanyCardDto companyCardDto = companyCardDtoAssembler.toModel(newCompanyCard);
        return new ResponseEntity<>(companyCardDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{companyCardId}")
    public ResponseEntity<?> updateCompanyCard(@RequestBody CompanyCard uCompanyCard, @PathVariable Integer companyCardId) {
        companyCardService.update(companyCardId, uCompanyCard);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{companyCardId}")
    public ResponseEntity<?> deleteCompanyCard(@PathVariable Integer companyCardId) {
        companyCardService.delete(companyCardId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
