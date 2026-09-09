package com.lldcoach.service;

import com.lldcoach.domain.evaluation.Evaluation;
import com.lldcoach.domain.submission.Submission;
import com.lldcoach.evaluator.EvaluationResult;
import com.lldcoach.evaluator.Evaluator;
import com.lldcoach.repository.EvaluationRepository;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class EvaluationService {

    private final Evaluator evaluator;
    private final EvaluationRepository evaluationRepository;

    public EvaluationService(
            Evaluator evaluator,
            EvaluationRepository evaluationRepository) {

        this.evaluator = evaluator;
        this.evaluationRepository = evaluationRepository;
    }

    public Evaluation evaluate(Submission submission) {

        EvaluationResult result =
                evaluator.evaluate(submission);

        String strengths = result.getStrengths()
                .stream()
                .map(item -> "• " + item)
                .collect(Collectors.joining("\n"));

        String improvements = result.getImprovements()
                .stream()
                .map(item -> "• " + item)
                .collect(Collectors.joining("\n"));

        Evaluation evaluation = new Evaluation(
                submission,
                result.getScore(),
                strengths,
                improvements,
                result.getOverallFeedback()
        );

        return evaluationRepository.save(evaluation);
    }
}
