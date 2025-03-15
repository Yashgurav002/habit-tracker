package com.tracker.habit_tracker_backend.repository;

import com.tracker.habit_tracker_backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    //Additional custom queries
}
