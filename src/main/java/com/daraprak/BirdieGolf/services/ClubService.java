package com.daraprak.BirdieGolf.services;

import com.daraprak.BirdieGolf.dao.ClubRepository;
import com.daraprak.BirdieGolf.dao.PlayerRepository;
import com.daraprak.BirdieGolf.dao.TournamentRepository;
import com.daraprak.BirdieGolf.models.Club;
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
public class ClubService {

    PlayerRepository playerRepository;
    TournamentRepository tournamentRepository;
    ClubRepository clubRepository;

    @Autowired
    public ClubService(PlayerRepository playerRepository, TournamentRepository tournamentRepository, ClubRepository clubRepository) {
        this.playerRepository = playerRepository;
        this.tournamentRepository = tournamentRepository;
        this.clubRepository = clubRepository;
    }

    public List<Club> findAll() {
        return clubRepository.findAll();
    }

    public Club save(Club club) {
        return clubRepository.save(club);
    }

    public Iterable<Club> findClubsByManufacturer(String manufacturer) {
        return clubRepository.findClubsByManufacturer(manufacturer);
    }

    public Iterable<Club> findClubsByModel(String model) {
        return clubRepository.findClubsByModel(model);
    }

    public Iterable<Club> findClubsByType(String type) {
        return clubRepository.findClubsByType(type);
    }

}
