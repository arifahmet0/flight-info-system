package com.cmpe331.flight_roster.controller;

import com.cmpe331.flight_roster.entity.Attendant;
import com.cmpe331.flight_roster.service.AttendantService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/attendants")
public class AttendantController {

    private final AttendantService attendantService;

    public AttendantController(AttendantService attendantService) {
        this.attendantService = attendantService;
    }

    @GetMapping
    public List<Attendant> getAllAttendants() {
        return attendantService.getAllAttendants();
    }
}