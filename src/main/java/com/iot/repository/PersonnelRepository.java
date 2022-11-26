package com.iot.repository;

import com.iot.domain.Personnel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonnelRepository extends JpaRepository<Personnel, Integer> {
    @Procedure
    @Query(value = "CALL CreateBunchOfRepositories(@a);", nativeQuery = true)
    Integer CreateBunchOfRepositories();

    @Procedure
    @Query(value = "CALL CreateDatabasesUsingCursors();", nativeQuery = true)
    void CreateDatabasesUsingCursors();
}