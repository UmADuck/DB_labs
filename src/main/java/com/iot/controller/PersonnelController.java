package com.iot.controller;

import com.iot.sevice.PersonnelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/personnel")
public class PersonnelController {

    @Autowired
    private final PersonnelService personnelService;

    public PersonnelController(PersonnelService personnelService) {
        this.personnelService = personnelService;
    }

    @PostMapping("/bunch")
    public ResponseEntity<Integer> createBunchOfCountries() {
        Integer bunch = personnelService.createBunchOfPersonnel();
        return new ResponseEntity<>(bunch, HttpStatus.OK);
    }

    @PostMapping("/withCursor")
    public ResponseEntity<String> createDatabasesUsingCursors() {
        personnelService.createDatabasesUsingCursors();
        return new ResponseEntity<>("New databases were created", HttpStatus.OK);
    }

}
