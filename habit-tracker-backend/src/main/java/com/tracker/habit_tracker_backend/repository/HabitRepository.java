package com.tracker.habit_tracker_backend.repository;

import com.tracker.habit_tracker_backend.model.Habit;
import com.tracker.habit_tracker_backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface HabitRepository extends JpaRepository<Habit,Integer> {
    List<Habit> findByUser(User user);
}
