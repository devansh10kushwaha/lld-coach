package com.lldcoach.service;

import com.lldcoach.domain.attempt.Attempt;
import com.lldcoach.domain.submission.Submission;
import com.lldcoach.repository.AttemptRepository;
import com.lldcoach.repository.SubmissionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class SubmissionService {

    private final SubmissionRepository submissionRepository;
    private final AttemptRepository attemptRepository;
    private final EvaluationService evaluationService;

    public SubmissionService(
            SubmissionRepository submissionRepository,
            AttemptRepository attemptRepository,
            EvaluationService evaluationService) {

        this.submissionRepository = submissionRepository;
        this.attemptRepository = attemptRepository;
        this.evaluationService = evaluationService;
    }

    @Transactional
    public Submission submit(
            Long attemptId,
            String designExplanation,
            String code,
            String diagram) {

        Attempt attempt = attemptRepository.findById(attemptId)
                .orElseThrow(() ->
                        new RuntimeException("Attempt not found"));

        Submission submission = new Submission(
                attempt,
                designExplanation,
                code,
                diagram
        );

        Submission savedSubmission =
                submissionRepository.save(submission);

        EvaluationService evaluationServiceRef =
                this.evaluationService;

        evaluationServiceRef.evaluate(savedSubmission);

        attempt.setStatus("SUBMITTED");
        attempt.setSubmittedAt(LocalDateTime.now());

        attemptRepository.save(attempt);

        return savedSubmission;
    }
}