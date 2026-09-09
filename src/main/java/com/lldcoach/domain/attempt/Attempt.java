package com.lldcoach.domain.attempt;

import com.lldcoach.domain.problem.Problem;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.time.LocalDateTime;

@Entity
public class Attempt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Problem problem;

    private String status;

    private LocalDateTime startedAt;

    private LocalDateTime submittedAt;

    public Attempt() {
    }

    public Attempt(Problem problem) {
        this.problem = problem;
        this.status = "IN_PROGRESS";
        this.startedAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public Problem getProblem() {
        return problem;
    }

    public String getStatus() {
        return status;
    }

    public LocalDateTime getStartedAt() {
        return startedAt;
    }

    public LocalDateTime getSubmittedAt() {
        return submittedAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setProblem(Problem problem) {
        this.problem = problem;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setStartedAt(LocalDateTime startedAt) {
        this.startedAt = startedAt;
    }

    public void setSubmittedAt(LocalDateTime submittedAt) {
        this.submittedAt = submittedAt;
    }
}