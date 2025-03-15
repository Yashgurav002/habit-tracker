package com.tracker.habit_tracker_backend.repository;

import com.tracker.habit_tracker_backend.model.Rewards;
import com.tracker.habit_tracker_backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RewardsRepository extends JpaRepository<Rewards, Long> {
    Rewards findByUser(User user);
}
