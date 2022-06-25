package com.daraprak.TeeShot.dao;

import com.daraprak.TeeShot.models.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TournamentRepository extends JpaRepository<Tournament, Integer> {

}
