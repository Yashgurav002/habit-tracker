package com.tracker.habit_tracker_backend.controller;

import com.tracker.habit_tracker_backend.model.Tracking;
import com.tracker.habit_tracker_backend.service.TrackingService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping("/api/tracking")
public class TrackingController {
    private TrackingService trackingService;

    public TrackingController(TrackingService trackingService) {
        this.trackingService = trackingService;
    }

    @PostMapping("create")
    public ResponseEntity<Tracking> createTracking(@RequestBody Tracking tracking){
        return ResponseEntity.ok(trackingService.createTracking(tracking));
    }

    @GetMapping("habit/{habitId}")
    public ResponseEntity<List<Tracking>> getAllTracking(@PathVariable int habitId){
        List<Tracking> trackingList=trackingService.getAllTrackingByHabit(habitId);

        if(trackingList.isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(trackingList);

    }
    @GetMapping("/habit/{habitId}/date-range")
    public ResponseEntity<List<Tracking>> getTrackingByHabitAndDateRange(
            @PathVariable int habitId,
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate) {
        List<Tracking> trackingList = trackingService.getTrackingByHabitAndDateRange(habitId, startDate, endDate);
        return ResponseEntity.ok(trackingList);
    }

    @DeleteMapping("/{trackingId}")
    public ResponseEntity<String> deleteTracking(@PathVariable int trackingId) {
        String response = trackingService.deleteTracking(trackingId);
        return ResponseEntity.ok(response);
    }



}
