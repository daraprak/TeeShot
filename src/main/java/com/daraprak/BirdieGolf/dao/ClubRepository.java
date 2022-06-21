package com.daraprak.BirdieGolf.dao;

import com.daraprak.BirdieGolf.models.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClubRepository extends JpaRepository<Club, String> {

    Iterable<Club> findClubsByManufacturer(String manufacturer);
    Iterable<Club> findClubsByModel(String model);
    Iterable<Club> findClubsByType(String type);

}
