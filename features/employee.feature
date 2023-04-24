Feature: Create employee

  Background: The employeeDatabase has a set of employees
    When createEmployee is given "giig"
    When createEmployee is given "mate"
    When createEmployee is given "peda"

  Scenario: The user inputted "huba"
    When createEmployee is given "huba"
    Then employee "huba" is added to the employeeDatabase

  Scenario: The user inputted "hhu12f"
    When createEmployee is given "hhu12f"
    Then error message "Initials doesn’t fit the restrictions, please input new initials."

  Scenario: The user inputted "giig"
    When createEmployee is given "giig"
    Then error message "Employee with given initials already exist, please input new initials."