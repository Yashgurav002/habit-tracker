package com.tracker.habit_tracker_backend.service;

import com.tracker.habit_tracker_backend.exceptions.ResourceNotFoundException;
import com.tracker.habit_tracker_backend.model.Habit;
import com.tracker.habit_tracker_backend.model.User;
import com.tracker.habit_tracker_backend.repository.HabitRepository;
import com.tracker.habit_tracker_backend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HabitService {

    private  final HabitRepository habitRepository;
    private final UserRepository userRepository;

    public HabitService(HabitRepository habitRepository, UserRepository userRepository) {
        this.habitRepository = habitRepository;
        this.userRepository = userRepository;
    }

    public Habit createHabit(Habit habit, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + userId));
        habit.setUser(user); // Set the associated user
        return habitRepository.save(habit);
    }
    public List<Habit> getAllHabits(){
        return habitRepository.findAll();
    }

    public Habit getHabitById(int habitId) {
        return habitRepository.findById(habitId)
                .orElseThrow(()->new ResourceNotFoundException("Habit not found with ID : "+ habitId));
    }

    public Habit updateHabit(int habitId, Habit habitDetails){
        Habit existingHabit=getHabitById(habitId);
        existingHabit.setName(habitDetails.getName());
        existingHabit.setDescription(habitDetails.getDescription());
        existingHabit.setFrequency(habitDetails.getFrequency());
        existingHabit.setStartDate(habitDetails.getStartDate());
        return habitRepository.save(existingHabit);
    }

    public void deleteHabit(int habitId) {
        Habit existingHabit=getHabitById(habitId);
        habitRepository.delete(existingHabit);
    }
}
