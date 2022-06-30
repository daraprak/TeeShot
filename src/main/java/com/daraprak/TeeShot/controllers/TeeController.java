package com.daraprak.TeeShot.controllers;

import com.daraprak.TeeShot.models.Tee;
import com.daraprak.TeeShot.services.PlayerService;
import com.daraprak.TeeShot.services.TeeService;
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
@RequestMapping("tees")
public class TeeController {

    PlayerService playerService;
    TeeService teeService;


    @Autowired
    public TeeController(PlayerService playerService, TeeService teeService) {
        this.playerService = playerService;
        this.teeService = teeService;
    }

    @GetMapping
    String getAllTeeTimes(Model model) {
        model.addAttribute("tees", teeService.findAll());
        return "tees";
    }

    @GetMapping(value = "/teeform")
    public String teeTimeForm(Model model) {
        model.addAttribute("tee", new Tee());
        return "teeupdate";
    }

    @PostMapping("/saveupdatetee")
    public String saveUpdateTeeTime(RedirectAttributes model, @ModelAttribute("tee") Tee tee) {
        log.warn("Model teetime: " + tee);
        teeService.saveOrUpdate(tee);
        model.addFlashAttribute("tee", teeService.findById(tee.getId()));
        return "teeupdate";
    }

}
