package com.daraprak.TeeShot.controllers;

import com.daraprak.TeeShot.models.Player;
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
import org.springframework.web.servlet.view.RedirectView;

import java.util.HashSet;
import java.util.Set;

@Controller
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequestMapping("players")
public class PlayerController {

    PlayerService playerService;
    TournamentService tournamentService;

    @Autowired
    public PlayerController(PlayerService playerService, TournamentService tournamentService) {
        this.playerService = playerService;
        this.tournamentService = tournamentService;
    }

    @GetMapping
    public String getAllPlayers(Model model) {
        model.addAttribute("players", playerService.findAllPlayers());
        return "players";
    }

    @GetMapping(value = "/playerform")
    public String playerForm(Model model) {
        model.addAttribute("player", new Player());
        return "playerupdate";
    }

    @PostMapping("/saveupdateplayer")
    public String saveUpdatePlayer(RedirectAttributes model, @ModelAttribute("player") Player player) {
        log.warn("Model player: " + player);
        playerService.isWinner(player);
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
