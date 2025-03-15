package com.tracker.habit_tracker_backend.service;

import com.tracker.habit_tracker_backend.exceptions.ResourceNotFoundException;
import com.tracker.habit_tracker_backend.model.Rewards;
import com.tracker.habit_tracker_backend.model.User;
import com.tracker.habit_tracker_backend.repository.RewardsRepository;
import com.tracker.habit_tracker_backend.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class RewardsService {

    private final RewardsRepository rewardsRepository;
    private final UserRepository userRepository;

    public RewardsService(RewardsRepository rewardsRepository, UserRepository userRepository) {
        this.rewardsRepository = rewardsRepository;
        this.userRepository = userRepository;
    }

    public Rewards createReward(User user, int points) {
        if (rewardsRepository.findByUser(user) != null) {
            throw new IllegalStateException("User already has a reward assigned!");
        }
        Rewards rewards = new Rewards();
        rewards.setUser(user);
        rewards.setPoints(points);

        // Auto-assign badge based on points
        rewards.setBadges(assignBadge(points));

        return rewardsRepository.save(rewards);
    }


    public Rewards getRewardsByUser(User user) {
        Rewards reward = rewardsRepository.findByUser(user);
        if (reward == null) {
            throw new ResourceNotFoundException("Rewards not found for user ID: " + user.getUserId());
        }
        return reward;
    }

    public Rewards updateRewards(User user, int points) {
        Rewards rewards = getRewardsByUser(user);
        rewards.setPoints(rewards.getPoints() + points);
        rewards.setBadges(assignBadge(rewards.getPoints())); // Auto-assign badge
        return rewardsRepository.save(rewards);
    }
    private String assignBadge(int points) {
        if (points >= 300) {
            return "Master 🎖";
        } else if (points >= 200) {
            return "Advanced 🥇";
        } else if (points >= 100) {
            return "Intermediate 🥈";
        } else {
            return "Beginner 🥉";
        }
    }


}
