package com.cmpe331.flight_roster.controller;

import com.cmpe331.flight_roster.entity.Pilot;
import com.cmpe331.flight_roster.service.PilotService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pilots")
public class PilotController {

    private final PilotService pilotService;

    public PilotController(PilotService pilotService) {
        this.pilotService = pilotService;
    }

    @GetMapping
    public List<Pilot> getAllPilots() {
        return pilotService.getAllPilots();
    }
}