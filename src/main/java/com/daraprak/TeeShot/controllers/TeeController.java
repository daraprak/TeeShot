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

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Controller
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequestMapping("tees")
public class TeeController {

    PlayerService playerService;
    TeeService teeService;

    LocalDate today = LocalDate.now();


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

        List<String> courseList = Arrays.asList("Cave Creek Golf Course", "Desert Mirage Golf Course", "Encanto Golf Course", "Peoria Pines Golf Course");
        model.addAttribute("courseList", courseList);

        List<LocalDate> dateList = Arrays.asList(today, today.plusDays(1), today.plusDays(2), today.plusDays(3), today.plusDays(4), today.plusDays(5), today.plusDays(6));
        model.addAttribute("dateList", dateList);

        List<Integer> golferList = Arrays.asList(1, 2, 3, 4);
        model.addAttribute("golferList", golferList);

        return "teeupdate";
    }

    @PostMapping("/saveupdatetee")
    public String saveUpdateTeeTime(RedirectAttributes model, @ModelAttribute("tee") Tee tee) {
        log.warn("Model teetime: " + tee);
        teeService.saveOrUpdate(tee);
        model.addFlashAttribute("tee", teeService.findById(tee.getId()));
        return "teeupdate";
    }

    @GetMapping("/deletetee/{id}")
    public String deleteTee(@PathVariable(value = "id") int id) {
        teeService.deleteTee(id);
        return "redirect:/tees";
    }

}
