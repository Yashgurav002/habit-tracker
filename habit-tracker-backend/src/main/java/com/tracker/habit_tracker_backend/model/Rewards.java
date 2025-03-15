package com.tracker.habit_tracker_backend.model;

import jakarta.persistence.*;
@Entity
@Table(name="rewards")
public class Rewards {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long rewardsId;

    @OneToOne
    @JoinColumn(name = "user_id", unique = true, nullable = false) // Ensures one-to-one mapping
    private User user;

    private String badges;
    private int points;

    public long getRewardsId() {
        return rewardsId;
    }

    public void setRewardsId(long rewardsId) {
        this.rewardsId = rewardsId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getBadges() {
        return badges;
    }

    public void setBadges(String badges) {
        this.badges = badges;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
