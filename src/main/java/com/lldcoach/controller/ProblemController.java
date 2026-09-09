package com.lldcoach.controller;

import com.lldcoach.domain.attempt.Attempt;
import com.lldcoach.domain.problem.Problem;
import com.lldcoach.service.AttemptService;
import com.lldcoach.service.ProblemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class ProblemController {

    private final ProblemService problemService;
    private final AttemptService attemptService;

    public ProblemController(
            ProblemService problemService,
            AttemptService attemptService) {

        this.problemService = problemService;
        this.attemptService = attemptService;
    }

    @GetMapping("/problems")
    public String showProblems(Model model) {

        List<Problem> problems = problemService.getAllProblems();

        model.addAttribute("problems", problems);

        return "problems";
    }

    @GetMapping("/problems/{problemId}/attempt")
    public String startAttempt(
            @PathVariable Long problemId) {

        Attempt attempt = attemptService.startAttempt(problemId);

        return "redirect:/attempts/" + attempt.getId();
    }
}