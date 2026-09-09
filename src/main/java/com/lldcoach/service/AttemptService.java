package com.lldcoach.service;

import com.lldcoach.domain.attempt.Attempt;
import com.lldcoach.domain.problem.Problem;
import com.lldcoach.repository.AttemptRepository;
import com.lldcoach.repository.ProblemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttemptService {

    private final AttemptRepository attemptRepository;
    private final ProblemRepository problemRepository;

    public AttemptService(
            AttemptRepository attemptRepository,
            ProblemRepository problemRepository) {

        this.attemptRepository = attemptRepository;
        this.problemRepository = problemRepository;
    }

    public Attempt startAttempt(Long problemId) {

        Problem problem = problemRepository.findById(problemId)
                .orElseThrow(() -> new RuntimeException("Problem not found"));

        Attempt attempt = new Attempt(problem);

        return attemptRepository.save(attempt);
    }

    public Attempt getAttemptById(Long attemptId) {

        return attemptRepository.findById(attemptId)
                .orElseThrow(() -> new RuntimeException("Attempt not found"));
    }

    public List<Attempt> getAllAttempts() {

        return attemptRepository.findAll();
    }
}
