package com.daraprak.BirdieGolf;

import com.daraprak.BirdieGolf.dao.TournamentRepository;
import com.daraprak.BirdieGolf.services.PlayerService;
import com.daraprak.BirdieGolf.services.TournamentService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ApplicationCommandLineRunner implements CommandLineRunner {

    PlayerService playerService;
    TournamentService tournamentService;
    TournamentRepository repository;

    @Autowired
    public ApplicationCommandLineRunner(PlayerService playerService, TournamentService tournamentService, TournamentRepository repository) {
        this.playerService = playerService;
        this.tournamentService = tournamentService;
        this.repository = repository;
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
