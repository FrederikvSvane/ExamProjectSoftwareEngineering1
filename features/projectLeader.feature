#Lucas
Feature: A project leader is added to a project

  Background: The employeeDatabase has a set of employees
    When createEmployee is given "giig"
    When createEmployee is given "mate"
    When createEmployee is given "heya"
    Given a user is logged in
    When user creates project with name "Not cool project"

  Scenario: User successfully adds a project leader
    Given a user is logged in and have selected a project
    And a user has an employee with username "heya"
    When a user add the employee "heya" as project leader
    Then the employee is marked as project leader

  Scenario: User unsuccessfully adds a project leader
    Given a user is logged in and have selected a project
    When a user add the employee with username "12huba" as a project leader
    Then error message "User does not exist"

  Scenario: User unsuccessfully adds a project leader2
    Given a user is logged in and have selected a project, and project already have a projectleader
    And a user has an employee with username "heya"
    When a user adds the employee as a project leader
    Then error message "Project leader already assigned to project."

  Scenario: User removes a project leader from project
    Given a user is logged in and have selected a project, and project already have a projectleader
    When a user remove the projectleader from the project
    Then the employee is no longer marked as project leader