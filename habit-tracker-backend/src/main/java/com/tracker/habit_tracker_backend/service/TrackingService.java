package com.tracker.habit_tracker_backend.service;

import com.tracker.habit_tracker_backend.model.Tracking;
import org.springframework.stereotype.Service;

import javax.sound.midi.Track;
import java.time.LocalDate;
import java.util.List;


public interface TrackingService {


    Tracking createTracking(Tracking tracking);

    List<Tracking> getAllTrackingByHabit(int habitId);

    List<Tracking> getTrackingByHabitAndDateRange(int habitId, LocalDate startDate, LocalDate endDate);

    String deleteTracking(int trackingId);

}
