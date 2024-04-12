# Task Management System

## Overview
This project is a Task Management System designed to help manage developers and tasks within a software development team. It provides functionalities to create, update, and delete developers and tasks, along with associating tasks with developers. The system also includes features like automatic creation of system developers and a debug mode for testing.

## Features
- **Developer Management:** Create, update, and delete developers.
- **Task Management:** Create, update, and delete tasks.
- **Associate Tasks:** Associate tasks with developers.
- **System Developer:** Automatically create a system developer on application startup (configurable).
- **Debug Mode:** Enable debug mode to facilitate testing (configurable).

## Technologies Used
- **Java:** Core programming language.
- **Spring Boot:** Framework for building robust Java applications.
- **Spring Data JPA:** Simplifies data access and manipulation.
- **Spring MVC:** Framework for building web applications.
- **Mockito:** Mocking framework for unit testing.
- **JUnit:** Testing framework for unit and integration testing.

## Setup
1. **Clone the repository:** `git clone https://github.com/StepanRudenskyi/TaskManagement.git`
2. **Build the project:** `./mvnw clean install`
3. **Run the application:** `./mvnw spring-boot:run`
4. **Access the application:** Open a web browser and go to `http://localhost:8080`

## Configuration
- **System Developer:** Set the system developer name and email in `application.properties`.
- **Debug Mode:** Enable or disable debug mode in `application.properties`.

## Usage
1. **Creating Developers:**
    - Send a POST request to `/developers` with JSON payload containing developer details.
2. **Creating Tasks:**
    - Send a POST request to `/tasks` with JSON payload containing task details.
3. **Associating Tasks with Developers:**
    - Send a POST request to `/developers/{developerId}/tasks` with JSON payload containing task details.
4. **Viewing Developers and Tasks:**
    - Send GET requests to `/developers` and `/tasks` to view all developers and tasks respectively.

## Testing
- **Unit Tests:** Run `./mvnw test` to execute unit tests.
- **Integration Tests:** Run `./mvnw integration-test` to execute integration tests.
