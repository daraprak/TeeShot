package com.daraprak.BirdieGolf.services;

import com.daraprak.BirdieGolf.dao.ClubRepository;
import com.daraprak.BirdieGolf.dao.PlayerRepository;
import com.daraprak.BirdieGolf.dao.TournamentRepository;
import com.daraprak.BirdieGolf.models.Player;
import com.daraprak.BirdieGolf.models.Tournament;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
@Transactional(rollbackOn = {DataAccessException.class})
public class PlayerService {

    PlayerRepository playerRepository;
    TournamentRepository tournamentRepository;
    ClubRepository clubRepository;

    @Autowired
    public PlayerService(PlayerRepository playerRepository, TournamentRepository tournamentRepository, ClubRepository clubRepository) {
        this.playerRepository = playerRepository;
        this.tournamentRepository = tournamentRepository;
        this.clubRepository = clubRepository;
    }

    public List<Player> findAll() {
        return playerRepository.findAll();
    }

    @Transactional(rollbackOn = {NoSuchElementException.class})
    public Player findByEmail(String email) throws NoSuchElementException {
        return playerRepository.findById(email).orElseThrow();
    }

    public void save(Player player) {
        playerRepository.save(player);
    }

    public void delete(Player player) {
        playerRepository.delete(player);
    }

    @Transactional(rollbackOn = {NoSuchElementException.class})
    public void addTournament(String email, Tournament tournament) {
        Player player = playerRepository.findById(email).orElseThrow();
        tournament = tournamentRepository.save(tournament);
        player.addTournament(tournament);
        playerRepository.save(player);
    }

    public List<Player> findAllSortedBy(Sort sort) {
        return playerRepository.findAll(sort);
    }

}
