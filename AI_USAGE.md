# AI Usage Documentation

## LLD Practice Platform

This document explains how AI assistance was used during the development of the LLD Practice Platform, including the engineering decisions influenced by AI suggestions and the reasoning behind the final choices.

---

## 1. Overview

AI assistance was used as a development and engineering support tool during the project.

The main areas where AI was used were:

- understanding the product requirements
- exploring possible LLD platform architectures
- designing the domain model
- designing the evaluation approach
- improving feedback quality
- reviewing implementation decisions
- generating and improving test cases
- identifying edge cases
- improving project documentation

AI suggestions were treated as engineering inputs rather than automatically accepted solutions. Important decisions were reviewed against the assignment requirements, MVP scope, implementation complexity, testability, and maintainability.

---

# 2. AI-Assisted Decision 1: Deterministic Evaluation vs AI Evaluation

## Problem

The platform needs to evaluate LLD submissions.

A possible approach was to send every submission to an LLM and ask it to evaluate the design.

## AI Suggestion

AI-assisted evaluation was considered because LLMs can understand natural-language design explanations and can potentially provide detailed feedback about:

- classes
- responsibilities
- relationships
- SOLID principles
- design patterns
- extensibility
- trade-offs

## Final Decision

A deterministic rule-based evaluator was selected for the MVP.

The evaluator checks evidence of important LLD concepts and produces a score and structured feedback.

The scoring model is:

| Criterion | Weight |
|---|---:|
| Design explanation | 30 |
| Code | 30 |
| Diagram | 20 |
| Classes / objects | 5 |
| Responsibilities | 5 |
| Relationships | 5 |
| Extensibility | 5 |
| Total | 100 |

## Why the Suggestion Was Not Fully Accepted

An AI-only evaluator would introduce:

- external API dependency
- non-deterministic results
- API cost
- variable evaluation latency
- additional failure cases
- more difficult automated testing

For a two-day MVP, deterministic evaluation provides better reliability and explainability.

## What Was Accepted

The idea of keeping AI evaluation as a future capability was accepted.

The application therefore uses an evaluator abstraction:

```text
Evaluator
    |
    └── RuleBasedEvaluator
