#Rasmus
Feature: Employee Project White Box

  Background: The employeeDatabase has a set of employees
    When createEmployee is given "giig"
    When createEmployee is given "mate"
    When createEmployee is given "lusj"
    Given a user is logged in
    When user creates project with name "Awesome Project"

  Scenario: Add an employee to a project and the user is not already assigned to the project
    Given A user is logged in and has a project selected.
    And A user exists with the initials "lusj".
    When An existing user adds "lusj" to a project. WhiteBox
    Then user "lusj" is added to the project

  Scenario: Add an employee to a project and the user is already assigned to the project
    Given A user is logged in and has a project selected.
    And A user exists with the initials "lusj".
    When An existing user adds "lusj" to a project. WhiteBox
    When An existing user adds "lusj" to a project. WhiteBox
    Then error message "The user is already assigned to the project". WhiteBox

  Scenario: Add an employee to a project and the user doesn't exist
    Given A user is logged in and has a project selected.
    When An existing user adds "rsas" to a project. WhiteBox
    Then error message "The user doesn't exist". WhiteBox

  Scenario: Add an employee to a project and the project is added to the user
    Given A user is logged in and has a project selected.
    And A user exists with the initials "lusj".
    When An existing user adds "lusj" to a project. WhiteBox
    Then project "Awesome Project" is added to user "lusj"