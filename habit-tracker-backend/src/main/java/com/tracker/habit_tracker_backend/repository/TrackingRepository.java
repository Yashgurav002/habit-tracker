package com.tracker.habit_tracker_backend.repository;

import com.tracker.habit_tracker_backend.model.Habit;
import com.tracker.habit_tracker_backend.model.Tracking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TrackingRepository extends JpaRepository<Tracking, Integer> {
    List<Tracking> findByHabit(Habit Habit);

    List<Tracking> findByHabitAndCheckinDateBetween(Habit habit, LocalDate startDate, LocalDate endDate);

}
