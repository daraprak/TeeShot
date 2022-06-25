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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequestMapping("players")
public class PlayerController {

    PlayerService playerService;
    PlayerRepository playerRepository;
    TournamentService tournamentService;
    TournamentRepository tournamentRepository;

    @Autowired
    public PlayerController(PlayerService playerService, PlayerRepository playerRepository, TournamentService tournamentService, TournamentRepository tournamentRepository) {
        this.playerService = playerService;
        this.playerRepository = playerRepository;
        this.tournamentService = tournamentService;
        this.tournamentRepository = tournamentRepository;
    }

    @GetMapping
    public String getAllWinners(Model model) {
        model.addAttribute("winners", playerService.findAllWinners());
        return "winners";
    }

    @GetMapping("/playerform")
    public String playerForm(Model model) {
        model.addAttribute("player", new Player());
        return "playerupdate";
    }

    @GetMapping("/saveupdateplayer/{email}")
    public String showPlayerUpdateForm(RedirectAttributes model, @ModelAttribute("player") Player player) {
        playerService.saveOrUpdate(player);
        model.addAttribute("player", playerService.findByEmail(player.getEmail()));
        return "playerupdate";
    }

    @PostMapping("/findplayerusername")
    public RedirectView findPlayerUserName(@RequestParam(required = false) String email, RedirectAttributes redirectAttributes) {
        log.warn("Email: " + email);
        try {
            redirectAttributes.addFlashAttribute("user", playerService.findByEmail(email));
        } catch (RuntimeException ex) {
            ex.printStackTrace();
            redirectAttributes.addFlashAttribute("user_not_found", String.format("Username: %s not found!", email));
            return new RedirectView("/players");
        }
        return new RedirectView("/players");
    }

}
