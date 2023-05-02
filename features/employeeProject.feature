Feature: employeeProject

  Background: The employeeDatabase has a set of employees
    When createEmployee is given "giig"
    When createEmployee is given "mate"
    When createEmployee is given "heya"
    Given a user is logged in
    When user creates project with name "Awesome Project"

  Scenario: Add an employee to a project and the user is not already assigned to the project

    Given A user is logged in and has a project selected.
    And A user is created with the initials “lusj“.
    When An existing user adds a new employee to a project

  Scenario: Add an employee to a project and the user is already assigned to the project

    Given A user is logged in and has a project selected.
    And A user is created with the initials “lusj“.
    When An existing user adds a new employee to a project
    Then The system throws an error, because the employee is already assigned to the project