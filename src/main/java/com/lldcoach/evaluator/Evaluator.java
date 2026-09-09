package com.lldcoach.evaluator;

import com.lldcoach.domain.submission.Submission;

public interface Evaluator {

    EvaluationResult evaluate(Submission submission);
}
