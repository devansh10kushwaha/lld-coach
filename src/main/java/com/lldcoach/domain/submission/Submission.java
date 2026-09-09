package com.lldcoach.domain.submission;

import com.lldcoach.domain.attempt.Attempt;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.time.LocalDateTime;

@Entity
public class Submission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Attempt attempt;

    @Column(length = 5000)
    private String designExplanation;

    @Column(length = 10000)
    private String code;

    @Column(length = 5000)
    private String diagram;

    private LocalDateTime submittedAt;

    public Submission() {
    }

    public Submission(
            Attempt attempt,
            String designExplanation,
            String code,
            String diagram
    ) {
        this.attempt = attempt;
        this.designExplanation = designExplanation;
        this.code = code;
        this.diagram = diagram;
        this.submittedAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public Attempt getAttempt() {
        return attempt;
    }

    public String getDesignExplanation() {
        return designExplanation;
    }

    public String getCode() {
        return code;
    }

    public String getDiagram() {
        return diagram;
    }

    public LocalDateTime getSubmittedAt() {
        return submittedAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAttempt(Attempt attempt) {
        this.attempt = attempt;
    }

    public void setDesignExplanation(String designExplanation) {
        this.designExplanation = designExplanation;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setDiagram(String diagram) {
        this.diagram = diagram;
    }

    public void setSubmittedAt(LocalDateTime submittedAt) {
        this.submittedAt = submittedAt;
    }
}
