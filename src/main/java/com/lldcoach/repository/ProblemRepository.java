package com.lldcoach.repository;

import com.lldcoach.domain.problem.Problem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProblemRepository extends JpaRepository<Problem, Long> {
}