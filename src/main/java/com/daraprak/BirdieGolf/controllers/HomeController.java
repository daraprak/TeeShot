package com.daraprak.BirdieGolf.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
@Slf4j
public class HomeController {

    @GetMapping(value = {"/", "index"})
    public String homePage(Principal principal) {
        if (principal != null) log.info(principal.getName());
        return "index";
    }

}
