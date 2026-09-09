package com.lldcoach.evaluator;

import java.util.List;

public class EvaluationResult {

    private int score;
    private List<String> strengths;
    private List<String> improvements;
    private String overallFeedback;

    public EvaluationResult(
            int score,
            List<String> strengths,
            List<String> improvements,
            String overallFeedback) {

        this.score = score;
        this.strengths = strengths;
        this.improvements = improvements;
        this.overallFeedback = overallFeedback;
    }

    public int getScore() {
        return score;
    }

    public List<String> getStrengths() {
        return strengths;
    }

    public List<String> getImprovements() {
        return improvements;
    }

    public String getOverallFeedback() {
        return overallFeedback;
    }
}