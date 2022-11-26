package com.iot.sevice.impl;

import com.iot.repository.PersonnelRepository;
import com.iot.sevice.PersonnelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class PersonnelServiceImpl implements PersonnelService {

    public PersonnelRepository repository;

    @Autowired
    public PersonnelServiceImpl(@Qualifier("personnelRepository") PersonnelRepository repository) {
        this.repository = repository;
    }

    @Override
    public Integer createBunchOfPersonnel() {
        return repository.CreateBunchOfRepositories();
    }

    @Override
    public void createDatabasesUsingCursors() {
        repository.CreateDatabasesUsingCursors();
    }
}
