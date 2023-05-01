Feature: Project

  Scenario: Create a project
    Given a user is logged in
    And a project with name “project1” does not exist
    And the year is 2023
    And there has been created 0 projects in 2023
    When user creates project with name “project1”
    Then a new project with the name “project1” and project ID “23001” is created

  Scenario: Fail to create project
    Given a user is logged in
    And a project with name “project1” exists
    And the year is 2023
    And there has been created 0 projects in 2023
    When user creates project with name “project1”
    Then a new project is not created