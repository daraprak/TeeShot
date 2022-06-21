package com.daraprak.BirdieGolf.controllers;

import com.daraprak.BirdieGolf.services.PlayerService;
import com.daraprak.BirdieGolf.services.TournamentService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequestMapping("tournaments")
public class TournamentController {

    PlayerService playerService;
    TournamentService tournamentService;

    @Autowired
    public TournamentController(PlayerService playerService, TournamentService tournamentService) {
        this.playerService = playerService;
        this.tournamentService = tournamentService;
    }

    @GetMapping
    public String getAllPlayers(Model model) {
        model.addAttribute("tournaments", tournamentService.findAll());
        return "tournaments";
    }

}
