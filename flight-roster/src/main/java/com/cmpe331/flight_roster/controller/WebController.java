package com.cmpe331.flight_roster.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    // Kullanıcı ana adrese (localhost:8080) gelince Frontend'i aç
    @GetMapping({"/", "/index"})
    public String index() {
        return "forward:/index.html";
    }
}