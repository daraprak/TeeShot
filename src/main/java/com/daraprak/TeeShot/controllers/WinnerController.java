package com.daraprak.TeeShot.controllers;

import com.daraprak.TeeShot.dao.WinnerRepository;
import com.daraprak.TeeShot.dao.TournamentRepository;
import com.daraprak.TeeShot.models.Winner;
import com.daraprak.TeeShot.services.WinnerService;
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
@RequestMapping("winners")
public class WinnerController {

    WinnerService winnerService;
    WinnerRepository winnerRepository;
    TournamentService tournamentService;
    TournamentRepository tournamentRepository;

    @Autowired
    public WinnerController(WinnerService winnerService, WinnerRepository winnerRepository, TournamentService tournamentService, TournamentRepository tournamentRepository) {
        this.winnerService = winnerService;
        this.winnerRepository = winnerRepository;
        this.tournamentService = tournamentService;
        this.tournamentRepository = tournamentRepository;
    }

    @GetMapping
    public String getAllWinners(Model model) {
        model.addAttribute("winners", winnerService.findAllWinners());
        return "winners";
    }

    @GetMapping(value = "/winnerform")
    public String winnerForm(Model model) {
        model.addAttribute("winner", new Winner());
        return "/winnerupdate";
    }

    @PostMapping("/saveupdatewinner")
    public String saveUpdateWinner(RedirectAttributes model, @ModelAttribute("winner") Winner winner) {
        log.warn("Model winner: " + winner);
        winnerService.isWinner(winner);
        winnerService.saveOrUpdate(winner);
        model.addAttribute("winner", winnerService.findByEmail(winner.getEmail()));
        return "winnerupdate";
    }

    @PostMapping("/findwinnerusername")
    public RedirectView findWinnerUserName(@RequestParam(required = false) String email, RedirectAttributes redirectAttributes) {
        log.warn("Email: " + email);
        try {
            redirectAttributes.addFlashAttribute("user", winnerService.findByEmail(email));
        } catch (RuntimeException ex) {
            ex.printStackTrace();
            redirectAttributes.addFlashAttribute("user_not_found", String.format("Username: %s not found!", email));
            return new RedirectView("/winners");
        }
        return new RedirectView("/winners");
    }

}
