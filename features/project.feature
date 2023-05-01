Feature: Project

  Background: The employeeDatabase has a set of employees
    When createEmployee is given "giig"
    When createEmployee is given "mate"

  Scenario: Create a project
    Given a user is logged in
    And a project with name "project1" does not exist
    And the year is 2023
    And there has been created 0 projects in 2023
    When user creates project with name "project1"
    Then a new project with the name "project1" and project ID 230001 is created

  Scenario: Fail to create project
    Given a user is logged in
    And a project with name "project1" exists
    And the year is 2023
    And there has been created 0 projects in 2023
    When user creates project with name "project1"
    Then error message "Project already exists"

  Scenario: Fail to create project
    Given a user is not logged in
    And a project with name "project1" does not exist
    And the year is 2023
    And there has been created 0 projects in 2023
    When user creates project with name "project1"
    Then error message "User must be logged in to create project"