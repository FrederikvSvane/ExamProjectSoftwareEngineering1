Feature: Create Project

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

  Scenario: Create a project with duration, budgeted hours and start week
    Given a user is logged in
    And a project with name "project2" does not exist
    And the year is 2023
    And there has been created 0 projects in 2023
    When user creates project with name "project2", duration 10, budgeted hours 100 and start week 1
    Then a new project with the name "project2", duration 10, budgeted hours 100 and start week 1 and project ID 230001 is created

  Scenario: Create two projects with duration, budgeted hours and start week
    Given a user is logged in
    And a project with name "project2" does not exist
    And a project with name "project3" does not exist
    And the year is 2023
    And there has been created 0 projects in 2023
    When user creates project with name "project2", duration 10, budgeted hours 100 and start week 1
    When user creates project with name "project3", duration 10, budgeted hours 100 and start week 1
    Then a new project with the name "project3", duration 10, budgeted hours 100 and start week 1 and project ID 230002 is created

  Scenario: Create a project with wrong duration
    Given a user is logged in
    And a project with name "project2" does not exist
    And the year is 2023
    And there has been created 0 projects in 2023
    When user creates project with name "project2", duration 0, budgeted hours 100 and start week 1
    Then error message "Invalid timeframe"

  Scenario: Create a project with wrong budgeted hours
    Given a user is logged in
    And a project with name "project2" does not exist
    And the year is 2023
    And there has been created 0 projects in 2023
    When user creates project with name "project2", duration 10, budgeted hours -100 and start week 1
    Then error message "Invalid timeframe"

  Scenario: Create a project with wrong start week
    Given a user is logged in
    And a project with name "project2" does not exist
    And the year is 2023
    And there has been created 0 projects in 2023
    When user creates project with name "project2", duration 10, budgeted hours 100 and start week -5
    Then error message "Invalid timeframe"

  Scenario: Fail to create project
    Given a user is logged in
    And a project with name "project1" exists
    And the year is 2023
    And there has been created 0 projects in 2023
    When user creates project with name "project1"
    Then error message "Project already exists"

  Scenario: Fail to create project
    Given a user is not logged in
    And a project with name "project" does not exist
    And the year is 2023
    And there has been created 0 projects in 2023
    When user creates project with name "project"
    Then error message "User must be logged in to create project"




