package com.lldcoach.evaluator;

import com.lldcoach.domain.submission.Submission;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RuleBasedEvaluatorTest {

    @Test
    void shouldGiveFullScoreForCompleteSubmission() {

        RuleBasedEvaluator evaluator =
                new RuleBasedEvaluator();

        Submission submission = new Submission(
                null,
                "The design contains important classes and their responsibilities. "
                        + "The relationships between classes are clearly defined. "
                        + "The design is extensible for future requirements.",
                "class ParkingLot { }",
                "ParkingLot -> ParkingSpot -> Vehicle"
        );

        EvaluationResult result =
                evaluator.evaluate(submission);

        assertEquals(100, result.getScore());
    }


    @Test
    void shouldGiveZeroForEmptySubmission() {

        RuleBasedEvaluator evaluator =
                new RuleBasedEvaluator();

        Submission submission = new Submission(
                null,
                "",
                "",
                ""
        );

        EvaluationResult result =
                evaluator.evaluate(submission);

        assertEquals(0, result.getScore());
    }


    @Test
    void shouldRewardDesignExplanation() {

        RuleBasedEvaluator evaluator =
                new RuleBasedEvaluator();

        Submission submission = new Submission(
                null,
                "The class ParkingLot has responsibility for managing parking.",
                "",
                ""
        );

        EvaluationResult result =
                evaluator.evaluate(submission);

        assertEquals(40, result.getScore());
    }
}