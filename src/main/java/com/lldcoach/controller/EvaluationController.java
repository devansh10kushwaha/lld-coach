package com.lldcoach.controller;

import com.lldcoach.domain.evaluation.Evaluation;
import com.lldcoach.repository.EvaluationRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class EvaluationController {

    private final EvaluationRepository evaluationRepository;

    public EvaluationController(
            EvaluationRepository evaluationRepository) {

        this.evaluationRepository = evaluationRepository;
    }

    @GetMapping("/evaluations/submission/{submissionId}")
    public String showEvaluation(
            @PathVariable Long submissionId,
            Model model) {

        Evaluation evaluation = evaluationRepository
                .findBySubmissionId(submissionId)
                .orElseThrow(() ->
                        new RuntimeException("Evaluation not found"));

        model.addAttribute("evaluation", evaluation);

        return "feedback";
    }
}
