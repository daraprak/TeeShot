package com.daraprak.TeeShot.dao;

import com.daraprak.TeeShot.models.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TournamentRepository extends JpaRepository<Tournament, Integer> {

    @Query(value = "select * from tournaments where name = :name", nativeQuery = true)
    List<Tournament> findByName(String name);

}
