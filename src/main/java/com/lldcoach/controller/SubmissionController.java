package com.lldcoach.controller;


import com.lldcoach.domain.submission.Submission;
import com.lldcoach.service.SubmissionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SubmissionController {

    private final SubmissionService submissionService;

    public SubmissionController(SubmissionService submissionService) {
        this.submissionService = submissionService;
    }

    @PostMapping("/submissions")
    public String submitSolution(
            @RequestParam Long attemptId,
            @RequestParam String designExplanation,
            @RequestParam(required = false) String code,
            @RequestParam(required = false) String diagram) {

        Submission submission = submissionService.submit(
        attemptId,
        designExplanation,
        code,
        diagram
);


        return "redirect:/evaluations/submission/" 
        + submission.getId();
    }
}
