package com.daraprak.TeeShot.services;

import com.daraprak.TeeShot.dao.PlayerRepository;
import com.daraprak.TeeShot.dao.TeeRepository;
import com.daraprak.TeeShot.dao.TournamentRepository;
import com.daraprak.TeeShot.models.Player;
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
public class PlayerService {

    PlayerRepository playerRepository;
    TournamentRepository tournamentRepository;
    TeeRepository teeRepository;

    @Autowired
    public PlayerService(PlayerRepository playerRepository, TournamentRepository tournamentRepository, TeeRepository teeRepository) {
        this.playerRepository = playerRepository;
        this.tournamentRepository = tournamentRepository;
        this.teeRepository = teeRepository;
    }

    public List<Player> findAll() {
        return playerRepository.findAll();
    }

    public List<Player> findAllPlayers() {
        return playerRepository.findAllPlayers();
    }

    @Transactional(rollbackOn = {NoSuchElementException.class})
    public Player findByEmail(String email) throws NoSuchElementException {
        return playerRepository.findById(email).orElseThrow();
    }

    public void saveOrUpdate(Player player) {
        log.info(player.toString());
        playerRepository.save(player);
    }

    public void isWinner(Player player) {
        player.setWinner(true);
    }

}
