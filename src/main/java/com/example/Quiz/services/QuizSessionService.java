package com.example.Quiz.services;

import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.Duration;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;

@Service
public class QuizSessionService {
    private final Map<String, LocalDateTime> activeSessions = new ConcurrentHashMap<>();
    private static final long TIME_LIMIT_MINUTES = 30;

    public void startSession(Long userId, Long quizId) {
        String key = userId + "_" + quizId;
        // Prevent restarting an active session
        if (activeSessions.containsKey(key)) {
            throw new RuntimeException("Quiz already started for this user.");
        }
        activeSessions.put(key, LocalDateTime.now());
    }

    public void validateAndEndSession(Long userId, Long quizId) {
        String key = userId + "_" + quizId;
        LocalDateTime startTime = activeSessions.remove(key);

        if (startTime == null) {
            throw new RuntimeException("Quiz session not found or already submitted.");
        }

        Duration duration = Duration.between(startTime, LocalDateTime.now());
        if (duration.toMinutes() > TIME_LIMIT_MINUTES) {
            throw new RuntimeException("Time limit exceeded. Submission blocked.");
        }
    }
}