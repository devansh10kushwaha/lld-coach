# LLD Practice Platform

A focused Low-Level Design practice platform that allows learners to choose an LLD problem, create an attempt, submit their design, receive explainable feedback, review their history, and try again.

## 1. Project Overview

Low-Level Design problems are open-ended and can have multiple valid solutions. The purpose of this project is to provide a simple practice environment where learners can work on real-world design problems and receive structured feedback.

The platform follows the practice loop:

Choose Problem → Think/Design → Submit → Get Feedback → Review → Try Again

## 2. Features

- View a list of LLD practice problems
- View problem description and difficulty
- Start a new attempt
- Provide a design explanation
- Submit representative code
- Provide a textual class/relationship diagram
- Submit an attempt for evaluation
- Receive a score out of 100
- View strengths
- View areas for improvement
- View overall feedback
- Review previous submissions
- Try the problem again

## 3. Current Problems

The MVP contains:

1. Parking Lot
2. Elevator System
3. Vending Machine

The problem set is intentionally small so that the complete practice and feedback loop can be demonstrated clearly.

## 4. Technology Stack

### Backend

- Java
- Spring Boot
- Spring MVC
- Spring Data JPA

### Frontend

- Thymeleaf
- HTML
- CSS

### Database

- H2

### Testing

- JUnit
- Spring Boot Test

## 5. Architecture

The application follows a simple layered monolithic architecture.

```text
Web Browser
     |
     v
Controllers
     |
     v
Services
     |
     +------------------+
     |                  |
     v                  v
Repositories       Evaluator
     |                  |
     v                  v
    H2          RuleBasedEvaluator
                        |
                        v
                 EvaluationResult
                        |
                        v
                    Feedback
