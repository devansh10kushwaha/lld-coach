package com.lldcoach.repository;

import com.lldcoach.domain.evaluation.Evaluation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EvaluationRepository
        extends JpaRepository<Evaluation, Long> {

    Optional<Evaluation> findBySubmissionId(Long submissionId);

    List<Evaluation> findBySubmission_Attempt_IdOrderBySubmission_SubmittedAtDesc(
            Long attemptId
    );
}