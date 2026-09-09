package com.lldcoach.controller;

import com.lldcoach.domain.attempt.Attempt;
import com.lldcoach.domain.evaluation.Evaluation;
import com.lldcoach.repository.EvaluationRepository;
import com.lldcoach.service.AttemptService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class AttemptController {

    private final AttemptService attemptService;
    private final EvaluationRepository evaluationRepository;

    public AttemptController(
            AttemptService attemptService,
            EvaluationRepository evaluationRepository) {

        this.attemptService = attemptService;
        this.evaluationRepository = evaluationRepository;
    }

    @GetMapping("/attempts/{attemptId}")
    public String showAttempt(
            @PathVariable Long attemptId,
            Model model) {

        Attempt attempt =
                attemptService.getAttemptById(attemptId);

        model.addAttribute("attempt", attempt);
        model.addAttribute("problem", attempt.getProblem());

        return "attempt";
    }

    @GetMapping("/attempts/{attemptId}/review")
    public String reviewAttempt(
            @PathVariable Long attemptId,
            Model model) {

        List<Evaluation> evaluations =
                evaluationRepository
                        .findBySubmission_Attempt_IdOrderBySubmission_SubmittedAtDesc(
                                attemptId
                        );

        if (evaluations.isEmpty()) {
            throw new RuntimeException(
                    "No evaluation found for this attempt"
            );
        }

        Evaluation latestEvaluation =
                evaluations.get(0);

        model.addAttribute(
                "evaluation",
                latestEvaluation
        );

        return "feedback";
    }

    @GetMapping("/history")
    public String showHistory(Model model) {

        List<Attempt> attempts =
                attemptService.getAllAttempts();

        Map<Long, Integer> attemptScores =
                new HashMap<>();

        for (Attempt attempt : attempts) {

            List<Evaluation> evaluations =
                    evaluationRepository
                            .findBySubmission_Attempt_IdOrderBySubmission_SubmittedAtDesc(
                                    attempt.getId()
                            );

            if (!evaluations.isEmpty()) {

                Evaluation latestEvaluation =
                        evaluations.get(0);

                attemptScores.put(
                        attempt.getId(),
                        latestEvaluation.getScore()
                );
            }
        }

        model.addAttribute("attempts", attempts);
        model.addAttribute("attemptScores", attemptScores);

        return "history";
    }
}