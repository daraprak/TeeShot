package com.daraprak.TeeShot.services;

import com.daraprak.TeeShot.dao.PlayerRepository;
import com.daraprak.TeeShot.dao.TournamentRepository;
import com.daraprak.TeeShot.models.Tournament;
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

    @Autowired
    public TournamentService(PlayerRepository playerRepository, TournamentRepository tournamentRepository) {
        this.playerRepository = playerRepository;
        this.tournamentRepository = tournamentRepository;
    }

    public List<Tournament> findAll() {
        return tournamentRepository.findAll();
    }

    @Transactional(rollbackOn = {NoSuchElementException.class})
    public Tournament findById(int id) throws NoSuchElementException {
        return tournamentRepository.findById(id).orElseThrow();
    }

    public void saveUpdateTournament(Tournament tournament) {
        tournamentRepository.save(tournament);
    }

    public void deleteTournament(Tournament tournament) {
        tournamentRepository.delete(tournament);
    }

//    public Tournament findTournamentByName(String name) {
//        return tournamentRepository.findByName(name).orElseThrow();
//    }



}
