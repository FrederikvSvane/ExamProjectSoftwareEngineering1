Feature: Create Project

  Background: The employeeDatabase has a set of employees
    When createEmployee is given "giig"
    When createEmployee is given "mate"

  Scenario: Test set A
    Given a user is logged in
    And a project with name "project2" does not exist
    And the year is 2023
    And there has been created 0 projects in 2023
    When a user creates project with name "project2", duration 10, budgeted hours 100 and start week 1
    Then a new project with the name "project2", duration 10, budgeted hours 100 and start week 1 and project ID 230001 is created

  Scenario: Test set B
    Given a user is logged in
    And a project with name "project2" does not exist
    And a project with name "project3" does not exist
    And the year is 2023
    And there has been created 0 projects in 2023
    When a user creates project with name "project2", duration 10, budgeted hours 100 and start week 1
    When a user creates project with name "project3", duration 10, budgeted hours 100 and start week 1
    Then a new project with the name "project3", duration 10, budgeted hours 100 and start week 1 and project ID 230002 is created

  Scenario: Test set C
    Given a user is logged in
    And a project with name "project2" does not exist
    And the year is 2023
    And there has been created 0 projects in 2023
    When a user creates project with name "project2", duration 10, budgeted hours 100 and start week -5
    Then error message "Invalid timeframe" is shown

  Scenario: Test set D
  Given a user is logged in
  And a project with name "project1" exists
  And the year is 2023
  And there has been created 0 projects in 2023
  When a user creates project with name "project1"
  Then error message "Project already exists" is shown

  Scenario: Test set E
  Given a user is not logged in
  And a project with name "project" does not exist
  And the year is 2023
  And there has been created 0 projects in 2023
  When a user creates project with name "project"
  Then error message "User must be logged in to create project" is shown
