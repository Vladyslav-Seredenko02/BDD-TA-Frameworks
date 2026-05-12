## What Was Implemented

## Cucumber Implementation Details

# Background

The Background keyword is used to define preconditions that run before every scenario, opening the browser and logging
in.

# Scenario Outline + Examples

The draft test uses Scenario Outline with an Examples table to run the same scenario with different data sets.

# Dependency Injection (PicoContainer)

`TestContext` is shared between BaseSteps and `UkrnetEMailSteps` via PicoContainer.

## How to Run

1. Run all tests on Chrome:
   `mvn test -Dbrowser=chrome`

2. Run on specific browser:
   `mvn test -Dbrowser=firefox`
   `mvn test -Dbrowser=edge`

3. Run with specific TestNG suite:
   `mvn test -Dsurefire.suiteXmlFiles=src/test/resources/suits/cucumber.xml`

4. Run specific environment config:
   `mvn test -Denv=qa1`
   `mvn test -Denv=qa2`