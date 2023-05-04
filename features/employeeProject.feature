Feature: Employee Project

  Background: The employeeDatabase has a set of employees
    When createEmployee is given "giig"
    When createEmployee is given "mate"
    When createEmployee is given "lusj"
    Given a user is logged in
    When user creates project with name "Awesome Project"

  Scenario: Add an employee to a project and the user is not already assigned to the project
    Given A user is logged in and has a project selected.
    And A user exists with the initials "lusj".
    When An existing user adds "lusj" to a project

  Scenario: Add an employee to a project and the user is already assigned to the project
    Given A user is logged in and has a project selected.
    And A user exists with the initials "lusj".
    When An existing user adds "lusj" to a project
    When An existing user adds "lusj" to a project
    Then error message "The user is already assigned to the project"

  Scenario: Add an employee to a project and the user doesn't exist
    Given A user is logged in and has a project selected.
    When An existing user adds "rsas" to a project
    Then error message "The user doesn't exist"