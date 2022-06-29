package com.daraprak.TeeShot.controllers;

import com.daraprak.TeeShot.dao.PlayerRepository;
import com.daraprak.TeeShot.dao.TournamentRepository;
import com.daraprak.TeeShot.models.Tournament;
import com.daraprak.TeeShot.services.PlayerService;
import com.daraprak.TeeShot.services.TournamentService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequestMapping("tournaments")
public class TournamentController {

    PlayerService playerService;
    PlayerRepository playerRepository;
    TournamentService tournamentService;
    TournamentRepository tournamentRepository;

    @Autowired
    public TournamentController(PlayerService playerService, PlayerRepository playerRepository, TournamentService tournamentService, TournamentRepository tournamentRepository) {
        this.playerService = playerService;
        this.playerRepository = playerRepository;
        this.tournamentService = tournamentService;
        this.tournamentRepository = tournamentRepository;
    }

    @GetMapping
    public String getAllTournaments(Model model) {
        model.addAttribute("tournaments", tournamentService.findAll());
        return "tournaments";
    }

    @GetMapping(value = "/tournamentform")
    public String tournamentForm(Model model) {
        model.addAttribute("tournament", new Tournament());
        return "tournamentupdate";
    }

    @PostMapping("/saveupdatetournament")
    public String saveUpdateTournament(RedirectAttributes model, @ModelAttribute("tournament") Tournament tournament) {
        log.warn("Tournament tournament: " + tournament);
        tournamentService.saveUpdateTournament(tournament);
        model.addFlashAttribute("tournament", tournamentService.findById(tournament.getId()));
        return "courseupdate";
    }

}
