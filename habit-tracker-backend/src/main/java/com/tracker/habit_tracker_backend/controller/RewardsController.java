package com.tracker.habit_tracker_backend.controller;

import com.tracker.habit_tracker_backend.exceptions.ResourceNotFoundException;
import com.tracker.habit_tracker_backend.model.Rewards;
import com.tracker.habit_tracker_backend.model.User;
import com.tracker.habit_tracker_backend.service.RewardsService;
import com.tracker.habit_tracker_backend.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rewards")
public class RewardsController {

    private final RewardsService rewardsService;
    private final UserService userService;

    public RewardsController(RewardsService rewardsService, UserService userService) {
        this.rewardsService = rewardsService;
        this.userService = userService;
    }

    // ✅ CREATE REWARD (Prevents duplicate rewards)
    @PostMapping("/create/{userId}")
    public ResponseEntity<?> assignReward(@PathVariable Long userId, @RequestBody Rewards rewards) {
        try {
            User user = userService.getUserById(userId);
            Rewards reward = rewardsService.createReward(user, rewards.getPoints());
            return ResponseEntity.status(HttpStatus.CREATED).body(reward);
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage()); // 409 Conflict
        }
    }

    // ✅ GET USER REWARDS
    @GetMapping("/{userId}")
    public ResponseEntity<?> getRewards(@PathVariable Long userId) {
        try {
            User user = userService.getUserById(userId);
            Rewards reward = rewardsService.getRewardsByUser(user);
            return ResponseEntity.ok(reward);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage()); // 404 Not Found
        }
    }

    // ✅ UPDATE REWARD POINTS (Auto-updates badges)
    @PutMapping("/update/{userId}")
    public ResponseEntity<?> updateRewards(@PathVariable Long userId, @RequestBody Rewards rewardsUpdate) {
        try {
            User user = userService.getUserById(userId);
            Rewards updatedReward = rewardsService.updateRewards(user, rewardsUpdate.getPoints());
            return ResponseEntity.ok(updatedReward);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage()); // 404 Not Found
        }
    }
}
