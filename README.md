# Automated UI Tests for DemoBlaze

This repository contains automated UI tests for the DemoBlaze website. The tests are built using **Selenium**, **TestNG**, **Cucumber**, and **Java** with a **Page Object Model (POM)** design pattern for clean, maintainable code.

## Project Structure

- **`src/main/java`**: Contains the Page Object classes that model the interactions with each web page.
- **`src/test/java`**: Contains step definitions, test hooks, and utility classes.
- **`src/test/resources/features`**: Stores feature files written in **Gherkin** syntax, detailing test scenarios for various functionalities of the website.

## Prerequisites

- **Java** JDK 17 installed.
- **Maven** or **Gradle** for dependency management.
- **TestNG** and **Cucumber** for running tests.
- Chrome or Firefox installed for browser testing.

## Getting Started

1. Clone this repository:
   ```bash
   git clone https://github.com/wetelleza/demoblaze.git

## Running the Tests

### Option 1: Run All Tests via TestNG XML

The primary way to execute all tests is by running the `testng.xml` file, which orchestrates the execution of all feature files and scenarios in parallel.

To execute all tests with **TestNG**, use:
```bash
./gradlew test --tests "runner.TestRunner"

