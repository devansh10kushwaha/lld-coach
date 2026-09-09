package com.lldcoach.evaluator;

import com.lldcoach.domain.submission.Submission;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RuleBasedEvaluator implements Evaluator {

    @Override
    public EvaluationResult evaluate(Submission submission) {

        int score = 0;

        List<String> strengths = new ArrayList<>();
        List<String> improvements = new ArrayList<>();

        String design = submission.getDesignExplanation();
        String code = submission.getCode();
        String diagram = submission.getDiagram();

        // 1. Design explanation
        if (design != null && !design.trim().isEmpty()) {
            score += 30;
            strengths.add("Design explanation is provided.");
        } else {
            improvements.add("Add a clear design explanation.");
        }

        // 2. Code
        if (code != null && !code.trim().isEmpty()) {
            score += 30;
            strengths.add("Code implementation is provided.");
        } else {
            improvements.add("Add code to demonstrate the design.");
        }

        // 3. Diagram
        if (diagram != null && !diagram.trim().isEmpty()) {
            score += 20;
            strengths.add("A design diagram is provided.");
        } else {
            improvements.add("Consider adding a class or relationship diagram.");
        }

        // 4. Design quality keywords
        if (design != null) {

            String lowerDesign = design.toLowerCase();

            if (lowerDesign.contains("class")) {
                score += 5;
                strengths.add("Classes are discussed.");
            } else {
                improvements.add("Clearly identify the important classes.");
            }

            if (lowerDesign.contains("responsibilit")) {
                score += 5;
                strengths.add("Responsibilities are discussed.");
            } else {
                improvements.add("Explain the responsibility of each major class.");
            }

            if (lowerDesign.contains("relationship")) {
                score += 5;
                strengths.add("Class relationships are considered.");
            } else {
                improvements.add("Explain relationships between classes.");
            }

            if (lowerDesign.contains("extensib")) {
                score += 5;
                strengths.add("Future extensibility is considered.");
            } else {
                improvements.add("Mention how the design can be extended in the future.");
            }
        }

        if (score > 100) {
            score = 100;
        }

        String overallFeedback;

        if (score >= 80) {
            overallFeedback =
                    "Strong submission. Your design covers the major areas. "
                    + "Focus next on improving design trade-offs and extensibility.";
        } else if (score >= 60) {
            overallFeedback =
                    "Good start. Your solution covers several important areas, "
                    + "but some design details should be explained more clearly.";
        } else if (score >= 40) {
            overallFeedback =
                    "Basic attempt completed. Add more detail about classes, "
                    + "responsibilities, relationships and implementation.";
        } else {
            overallFeedback =
                    "Your submission needs more detail. Start by identifying "
                    + "the main classes and their responsibilities.";
        }

        return new EvaluationResult(
                score,
                strengths,
                improvements,
                overallFeedback
        );
    }
}
