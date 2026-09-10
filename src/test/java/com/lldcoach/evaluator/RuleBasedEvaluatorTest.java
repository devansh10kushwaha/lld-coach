package com.lldcoach.evaluator;

import com.lldcoach.domain.submission.Submission;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

        assertFalse(result.getStrengths().isEmpty());

        assertFalse(result.getOverallFeedback().isEmpty());
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

        assertTrue(result.getStrengths().isEmpty());

        assertFalse(result.getImprovements().isEmpty());

        assertFalse(result.getOverallFeedback().isEmpty());
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

        assertFalse(result.getStrengths().isEmpty());

        assertFalse(result.getImprovements().isEmpty());
    }


    @Test
    void shouldIdentifyMissingRelationships() {

        RuleBasedEvaluator evaluator =
                new RuleBasedEvaluator();

        Submission submission = new Submission(
                null,
                "The design contains important classes and their responsibilities. "
                        + "The design is extensible for future requirements.",
                "class ParkingLot { }",
                ""
        );

        EvaluationResult result =
                evaluator.evaluate(submission);

        assertEquals(75, result.getScore());

        assertTrue(
                result.getImprovements()
                        .stream()
                        .anyMatch(message ->
                                message.toLowerCase()
                                        .contains("relationship"))
        );
    }


    @Test
    void shouldIdentifyMissingExtensibility() {

        RuleBasedEvaluator evaluator =
                new RuleBasedEvaluator();

        Submission submission = new Submission(
                null,
                "The design contains important classes and their responsibilities. "
                        + "The relationships between classes are clearly defined.",
                "class ParkingLot { }",
                "ParkingLot -> ParkingSpot"
        );

        EvaluationResult result =
                evaluator.evaluate(submission);

        assertEquals(95, result.getScore());

        assertTrue(
                result.getImprovements()
                        .stream()
                        .anyMatch(message ->
                                message.toLowerCase()
                                        .contains("extended"))
        );
    }


    @Test
    void shouldHandleNullSubmissionFields() {

        RuleBasedEvaluator evaluator =
                new RuleBasedEvaluator();

        Submission submission = new Submission(
                null,
                null,
                null,
                null
        );

        EvaluationResult result =
                evaluator.evaluate(submission);

        assertEquals(0, result.getScore());

        assertTrue(result.getStrengths().isEmpty());

        assertFalse(result.getImprovements().isEmpty());

        assertFalse(result.getOverallFeedback().isEmpty());
    }
}