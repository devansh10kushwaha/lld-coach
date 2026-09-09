package com.lldcoach.domain.evaluation;

import com.lldcoach.domain.submission.Submission;
import jakarta.persistence.*;

@Entity
public class Evaluation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Submission submission;

    private int score;

    @Column(length = 3000)
    private String strengths;

    @Column(length = 3000)
    private String improvements;

    @Column(length = 3000)
    private String overallFeedback;

    public Evaluation() {
    }

    public Evaluation(
            Submission submission,
            int score,
            String strengths,
            String improvements,
            String overallFeedback) {

        this.submission = submission;
        this.score = score;
        this.strengths = strengths;
        this.improvements = improvements;
        this.overallFeedback = overallFeedback;
    }

    public Long getId() {
        return id;
    }

    public Submission getSubmission() {
        return submission;
    }

    public int getScore() {
        return score;
    }

    public String getStrengths() {
        return strengths;
    }

    public String getImprovements() {
        return improvements;
    }

    public String getOverallFeedback() {
        return overallFeedback;
    }
}