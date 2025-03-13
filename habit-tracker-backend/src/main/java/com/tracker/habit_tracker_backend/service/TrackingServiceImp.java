package com.tracker.habit_tracker_backend.service;

import com.tracker.habit_tracker_backend.exceptions.ResourceNotFoundException;
import com.tracker.habit_tracker_backend.model.Habit;
import com.tracker.habit_tracker_backend.model.Tracking;
import com.tracker.habit_tracker_backend.repository.HabitRepository;
import com.tracker.habit_tracker_backend.repository.TrackingRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TrackingServiceImp implements TrackingService {
    private final TrackingRepository trackingRepository;
    private final HabitRepository habitRepository;

    public TrackingServiceImp(TrackingRepository trackingRepository, HabitRepository habitRepository) {
        this.trackingRepository = trackingRepository;
        this.habitRepository = habitRepository;
    }

    @Override
    public Tracking createTracking(Tracking tracking) {
        // Validate habitId
        int habitId = tracking.getHabit().getHabitId();
        Habit habit = habitRepository.findById(habitId)
                .orElseThrow(() -> new ResourceNotFoundException("Habit not found with ID: " + habitId));

        // Set the habit in tracking
        tracking.setHabit(habit);

        // Save and return the tracking entity
        return trackingRepository.save(tracking);
    }

    @Override
    public List<Tracking> getAllTrackingByHabit(int habitId) {
        // Fetch habit
        Habit habit = habitRepository.findById(habitId)
                .orElseThrow(() -> new ResourceNotFoundException("Habit not found with ID: " + habitId));

        // Retrieve all tracking records for the given habit
        return trackingRepository.findByHabit(habit);
    }

    @Override
    public List<Tracking> getTrackingByHabitAndDateRange(int habitId, LocalDate startDate, LocalDate endDate) {
        // Fetch habit
        Habit habit = habitRepository.findById(habitId)
                .orElseThrow(() -> new ResourceNotFoundException("Habit not found with ID: " + habitId));

        // Retrieve tracking records for the habit within the date range
        return trackingRepository.findByHabitAndCheckinDateBetween(habit, startDate, endDate);
    }

    @Override
    public String deleteTracking(int trackingId) {
        // Find tracking record
        Tracking tracking = trackingRepository.findById(trackingId)
                .orElseThrow(() -> new ResourceNotFoundException("Tracking record not found with ID: " + trackingId));

        // Delete the tracking record
        trackingRepository.delete(tracking);

        return "Tracking record with ID " + trackingId + " successfully deleted.";
    }
}