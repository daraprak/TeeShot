package com.daraprak.TeeShot;

import com.daraprak.TeeShot.dao.TournamentRepository;
import com.daraprak.TeeShot.models.Player;
import com.daraprak.TeeShot.models.Tournament;
import com.daraprak.TeeShot.services.PlayerService;
import com.daraprak.TeeShot.services.TournamentService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ApplicationCommandLineRunner implements CommandLineRunner {

    PlayerService playerService;
    TournamentService tournamentService;
    TournamentRepository tournamentRepository;

    static final String PASSWORD = "password";
    static final String DARAID = "dara@gmail.com";
    static final String MICHAELID = "michael@gmail.com";
    static final String DWIGHTID = "dwight@gmail.com";
    static final String JIMID = "jim@gmail.com";
    static final String DARRYLID = "darryl@gmail.com";

    @Autowired
    public ApplicationCommandLineRunner(PlayerService playerService, TournamentService tournamentService, TournamentRepository tournamentRepository) {
        this.playerService = playerService;
        this.tournamentService = tournamentService;
        this.tournamentRepository = tournamentRepository;
    }

    @PostConstruct
    public void postConstruct() {
        log.warn("========== Application CommandLine Runner ==========");
    }

    @Override
    public void run(String... args) throws Exception {

        playerService.saveOrUpdate(new Player(DARAID, "Dara", "Prak", PASSWORD, true,20000));
        playerService.saveOrUpdate(new Player(MICHAELID, "Michael", "Scott", PASSWORD, true, 15000));
        playerService.saveOrUpdate(new Player(DWIGHTID, "Dwight", "Schrute", PASSWORD, false));
        playerService.saveOrUpdate(new Player(JIMID, "Jim", "Halpert", PASSWORD, true, 11000));
        playerService.saveOrUpdate(new Player(DARRYLID, "Darryl", "Philbin", PASSWORD, false));

        tournamentService.saveUpdateTournament(new Tournament("June 12", "Cave Creek Open","Cave Creek Golf Course", 12000));
        tournamentService.saveUpdateTournament(new Tournament("June 29", "Talking Stick Championship", "Talking Stick Golf Club", 20000));
        tournamentService.saveUpdateTournament(new Tournament("July 17", "Biltmore Classic", "Arizona Biltmore Golf Club", 25000));
        tournamentService.saveUpdateTournament(new Tournament("Aug 5", "GCU Championship", "Grand Canyon University Championship Golf Course", 16000));
        tournamentService.saveUpdateTournament(new Tournament("Aug 27", "Aguila Open", "Aguila Golf Course", 11500));

    }
}