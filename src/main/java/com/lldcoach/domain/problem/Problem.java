package com.lldcoach.domain.problem;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Problem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private String difficulty;

    public Problem() {
    }

    public Problem(String title, String description, String difficulty) {
        this.title = title;
        this.description = description;
        this.difficulty = difficulty;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }
}
