package com.daraprak.TeeShot;

import com.daraprak.TeeShot.dao.AuthGroupRepository;
import com.daraprak.TeeShot.dao.TournamentRepository;
import com.daraprak.TeeShot.models.*;
import com.daraprak.TeeShot.services.CourseService;
import com.daraprak.TeeShot.services.PlayerService;
import com.daraprak.TeeShot.services.TeeTimeService;
import com.daraprak.TeeShot.services.TournamentService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;

@Component
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ApplicationCommandLineRunner implements CommandLineRunner {

    PlayerService playerService;
    TournamentService tournamentService;
    TournamentRepository tournamentRepository;
    CourseService courseService;
    TeeTimeService teeTimeService;
    AuthGroupRepository authGroupRepository;

    static final String PASSWORD = "password";
    static final String DARAID = "dara@gmail.com";
    static final String MICHAELID = "michael@gmail.com";
    static final String DWIGHTID = "dwight@gmail.com";
    static final String JIMID = "jim@gmail.com";
    static final String DARRYLID = "darryl@gmail.com";
    static final LocalDate TODAY = LocalDate.now();
    static final LocalTime TIME = LocalTime.of(6,00);
    static final  String ROLE_ADMIN = "ROLE_ADMIN";
    static final  String ROLE_USER = "ROLE_USER";

    @Autowired
    public ApplicationCommandLineRunner(PlayerService playerService, TournamentService tournamentService, TournamentRepository tournamentRepository, CourseService courseService, TeeTimeService teeTimeService, AuthGroupRepository authGroupRepository) {
        this.playerService = playerService;
        this.tournamentService = tournamentService;
        this.tournamentRepository = tournamentRepository;
        this.courseService = courseService;
        this.teeTimeService = teeTimeService;
        this.authGroupRepository = authGroupRepository;
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

        authGroupRepository.save(new AuthGroup(DARAID, ROLE_ADMIN));
        authGroupRepository.save(new AuthGroup(MICHAELID, ROLE_ADMIN));
        authGroupRepository.save(new AuthGroup(DWIGHTID, ROLE_USER));
        authGroupRepository.save(new AuthGroup(JIMID, ROLE_USER));
        authGroupRepository.save(new AuthGroup(DARRYLID, ROLE_USER));

        tournamentService.saveUpdateTournament(new Tournament(LocalDate.of(2022, Month.JUNE, 12), "Cave Creek Open","Cave Creek Golf Course", 12000));
        tournamentService.saveUpdateTournament(new Tournament(LocalDate.of(2022, Month.JUNE, 29), "Talking Stick Championship", "Talking Stick Golf Club", 20000));
        tournamentService.saveUpdateTournament(new Tournament(LocalDate.of(2022, Month.JULY, 17), "Biltmore Classic", "Arizona Biltmore Golf Club", 25000));
        tournamentService.saveUpdateTournament(new Tournament(LocalDate.of(2022, Month.AUGUST, 5), "GCU Championship", "Grand Canyon University Championship Golf Course", 16000));
        tournamentService.saveUpdateTournament(new Tournament(LocalDate.of(2022, Month.AUGUST, 27), "Aguila Open", "Aguila Golf Course", 11500));

        courseService.saveOrUpdate(new Course("Desert Mirage Golf Course", 4, 35));
        courseService.saveOrUpdate(new Course("Peoria Pines Golf Course", 3, 40));
        courseService.saveOrUpdate(new Course("Cave Creek Golf Course", 4, 60));
        courseService.saveOrUpdate(new Course("Encanto Golf Course", 2, 35));

        teeTimeService.saveOrUpdate(new TeeTime(TODAY, TIME));
        teeTimeService.saveOrUpdate(new TeeTime(TODAY, TIME.plusHours(1)));
        teeTimeService.saveOrUpdate(new TeeTime(TODAY, TIME.plusHours(2)));

    }

}