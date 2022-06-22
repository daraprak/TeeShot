package com.daraprak.BirdieGolf;

import com.daraprak.BirdieGolf.dao.TournamentRepository;
import com.daraprak.BirdieGolf.models.Club;
import com.daraprak.BirdieGolf.models.Player;
import com.daraprak.BirdieGolf.models.Tournament;
import com.daraprak.BirdieGolf.services.ClubService;
import com.daraprak.BirdieGolf.services.PlayerService;
import com.daraprak.BirdieGolf.services.TournamentService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.NoSuchElementException;

@Component
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ApplicationCommandLineRunner implements CommandLineRunner {

    PlayerService playerService;
    TournamentService tournamentService;
    ClubService clubService;
    TournamentRepository tournamentRepository;

    static final String PASSWORD = "password";
    static final String DARAID = "dara@gmail.com";
    static final String MICHAELID = "michael@gmail.com";
    static final String DWIGHTID = "dwight@gmail.com";
    static final String JIMID = "jim@gmail.com";

    @Autowired
    public ApplicationCommandLineRunner(PlayerService playerService, TournamentService tournamentService, ClubService clubService, TournamentRepository tournamentRepository) {
        this.playerService = playerService;
        this.tournamentService = tournamentService;
        this.clubService = clubService;
        this.tournamentRepository = tournamentRepository;
    }

    @PostConstruct
    public void postConstruct() {
        log.warn("========== Application CommandLine Runner ==========");
    }

    @Override
    public void run(String... args) throws Exception {

        playerService.save(new Player(DARAID, "Dara", "Prak", PASSWORD));
        playerService.save(new Player(MICHAELID, "Michael", "Scott", PASSWORD));
        playerService.save(new Player(DWIGHTID, "Dwight", "Schrute", PASSWORD));
        playerService.save(new Player(JIMID, "Jim", "Halpert", PASSWORD));

        tournamentService.save(new Tournament("Masters", "07-06-2022", "7:00AM", "Talking Stick Golf Club"));
        tournamentService.save(new Tournament("US Open", "07-11-2022", "7:00AM", "Grand Canyon University Championship Golf Course"));
        tournamentService.save(new Tournament("PGA", "08-01-2022", "7:00AM", "Aguila Golf Course"));

        try {
            playerService.addTournament(DARAID, tournamentService.findById(1));
            playerService.addTournament(DARAID, tournamentService.findById(2));
            playerService.addTournament(DARAID, tournamentService.findById(3));
            playerService.addTournament(MICHAELID, tournamentService.findById(1));
            playerService.addTournament(MICHAELID, tournamentService.findById(2));
            playerService.addTournament(MICHAELID, tournamentService.findById(3));
            playerService.addTournament(DWIGHTID, tournamentService.findById(2));
            playerService.addTournament(DWIGHTID, tournamentService.findById(3));
            playerService.addTournament(JIMID, tournamentService.findById(3));
        } catch (NoSuchElementException ex) {
            log.error("Couldn't add player to tournament!");
            ex.printStackTrace();
        } catch (RuntimeException e) {
            log.error("Couldn't add tournament!");
            e.printStackTrace();
        }
        log.info("Find All Players Sorted By Name Desc");
        log.warn(playerService.findAllSortedBy(Sort.by(Sort.Direction.DESC, "lastName")).toString());
    }

}
