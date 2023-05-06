Feature: Add hours to off-work activity

  Background: The employeeDatabase has a set of employees
    When createEmployee is given "giig"
    When createEmployee is given "mate"
    Given a user is logged in
    Given an off work activity with name "Vacation" exists
    Given a user is not logged in


  Scenario: User successfully adds hours to a off-work activity.
    Given a user "mate" is logged in
    When a user adds 34 hours to off-work activity "Vacation"
    Then the activity "Vacation" has 34 hours on user


  Scenario: User successfully adds hours to a off-work activity.
    Given a user "mate" is logged in
    When a user adds 34 hours to off-work activity "Vacation"
    When a user adds 34 hours to off-work activity "Vacation"
    Then the activity "Vacation" has 68 hours on user

  Scenario: Not logged in user unsuccessfully adds hours to a off-work activity twice.
    When a user adds 34 hours to off-work activity "Vacation"
    Then error message "User must be logged in to add hours"


  Scenario: User unsuccessfully adds hours to a off-work activity twice.
    Given a user "mate" is logged in
    When a user adds -34 hours to off-work activity "Vacation"
    Then error message "Invalid number of hours"

