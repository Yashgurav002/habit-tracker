package com.tracker.habit_tracker_backend.controller;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HabitController {
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/api/test")
    public String test(){
        return "Backend is working";
    }
}
