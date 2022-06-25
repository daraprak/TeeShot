package com.daraprak.TeeShot.controllers;

import com.daraprak.TeeShot.dao.PlayerRepository;
import com.daraprak.TeeShot.dao.TournamentRepository;
import com.daraprak.TeeShot.models.Player;
import com.daraprak.TeeShot.services.PlayerService;
import com.daraprak.TeeShot.services.TournamentService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequestMapping("admin")
public class AdminController {

    PlayerService playerService;
    PlayerRepository playerRepository;
    TournamentService tournamentService;
    TournamentRepository tournamentRepository;

    @Autowired
    public AdminController(PlayerService playerService, PlayerRepository playerRepository, TournamentService tournamentService, TournamentRepository tournamentRepository) {
        this.playerService = playerService;
        this.playerRepository = playerRepository;
        this.tournamentService = tournamentService;
        this.tournamentRepository = tournamentRepository;
    }

    @GetMapping
    public String getAllPlayers(Model model) {
        model.addAttribute("players", playerService.findAll());
        return "admin/players";
    }

    @GetMapping("/players/{email}")
    public String showPlayerUpdateForm(RedirectAttributes model, @ModelAttribute("player") Player player) {
        playerService.saveOrUpdate(player);
        model.addAttribute("player", playerService.findByEmail(player.getEmail()));
        return "admin/portal";
    }

    @GetMapping("/tournaments")
    public String adminViewAllTournaments(Model model) {
        model.addAttribute("tournaments", tournamentService.findAll());
        return "admin/tournaments";
    }

    @GetMapping("/tournaments/{id}")
    public String deleteTournamentById(@PathVariable int id) {
        log.info("Deleting Tournament with ID: " + id);
        return "admin/portal";
    }

}
