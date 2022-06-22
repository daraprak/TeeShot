package com.daraprak.BirdieGolf.services;

import com.daraprak.BirdieGolf.dao.ClubRepository;
import com.daraprak.BirdieGolf.dao.PlayerRepository;
import com.daraprak.BirdieGolf.dao.TournamentRepository;
import com.daraprak.BirdieGolf.models.Tournament;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
@Transactional(rollbackOn = {DataAccessException.class})
public class TournamentService {

    PlayerRepository playerRepository;
    TournamentRepository tournamentRepository;
    ClubRepository clubRepository;

    @Autowired
    public TournamentService(PlayerRepository playerRepository, TournamentRepository tournamentRepository, ClubRepository clubRepository) {
        this.playerRepository = playerRepository;
        this.tournamentRepository = tournamentRepository;
        this.clubRepository = clubRepository;
    }

    public List<Tournament> findAll() {
        return tournamentRepository.findAll();
    }

    @Transactional(rollbackOn = {NoSuchElementException.class})
    public Tournament findById(int id) throws NoSuchElementException {
        return tournamentRepository.findById(id).orElseThrow();
    }

    public void save(Tournament tournament) {
        tournamentRepository.save(tournament);
    }

    public void delete(Tournament tournament) {
        tournamentRepository.delete(tournament);
    }

}
