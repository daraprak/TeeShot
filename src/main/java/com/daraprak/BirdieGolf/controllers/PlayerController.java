package com.daraprak.BirdieGolf.controllers;

import com.daraprak.BirdieGolf.services.PlayerService;
import com.daraprak.BirdieGolf.services.TournamentService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequestMapping("players")
public class PlayerController {

    PlayerService playerService;
    TournamentService tournamentService;



}
