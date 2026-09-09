package com.lldcoach.repository;

import com.lldcoach.domain.attempt.Attempt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttemptRepository extends JpaRepository<Attempt, Long> {
}
