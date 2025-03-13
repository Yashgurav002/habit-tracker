package com.tracker.habit_tracker_backend.controller;


import com.tracker.habit_tracker_backend.model.Habit;
import com.tracker.habit_tracker_backend.service.HabitService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/habits")
@CrossOrigin(origins = "http://localhost:3000")
public class HabitController {

    private HabitService habitService;

    public HabitController(HabitService habitService) {
        this.habitService = habitService;
    }

    //Create a new habit
    @PostMapping("create")
    public ResponseEntity<Habit> createHabit(@RequestBody Habit habit, @RequestParam Long userId) {
        Habit createdHabit = habitService.createHabit(habit, userId);
        return ResponseEntity.status(201).body(createdHabit);
    }


    //Get all habits
    @GetMapping("")
    public ResponseEntity<List<Habit>> getAllHabits(){
        List<Habit> habits= habitService.getAllHabits();
        return ResponseEntity.ok(habits);
    }

    @GetMapping("/{habitId}")
    public ResponseEntity<Habit> getHabitById(@PathVariable int habitId){
        Habit habit=habitService.getHabitById(habitId);
        return ResponseEntity.ok(habit);
    }

    //Update the habtt
    @PutMapping("/{habitId}")
    public ResponseEntity<Habit> updateHabit(@PathVariable int habitId, @RequestBody Habit updatedHabit){
        Habit habit=habitService.updateHabit(habitId,updatedHabit);
        return ResponseEntity.ok(habit);
    }

    //Delte a habit
    @DeleteMapping("/{habitId}")
    public ResponseEntity<String> deleteHabit(@PathVariable int habitId){
        habitService.deleteHabit(habitId);
        return ResponseEntity.ok("Habit with ID "+habitId+ " was successfully delted");
    }

    @GetMapping("/api/test")
    public String test(){
        return "Backend is working";
    }


}
