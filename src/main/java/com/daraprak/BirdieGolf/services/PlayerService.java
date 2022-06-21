package com.daraprak.BirdieGolf.services;

import com.daraprak.BirdieGolf.dao.PlayerRepository;
import com.daraprak.BirdieGolf.dao.TournamentRepository;
import com.daraprak.BirdieGolf.models.Player;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
@Transactional(rollbackOn = {DataAccessException.class})
public class PlayerService {

    PlayerRepository playerRepository;
    TournamentRepository tournamentRepository;

    @Autowired
    public PlayerService(PlayerRepository playerRepository, TournamentRepository tournamentRepository) {
        this.playerRepository = playerRepository;
        this.tournamentRepository = tournamentRepository;
    }

    public List<Player> findAll() {
        return playerRepository.findAll();
    }

}
