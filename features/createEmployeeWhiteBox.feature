Feature: White box tests of the method createEmployee from the EmployeeBase class.

  Background:
    When createEmployee is given "giig"
    When createEmployee is given "mate"
    When createEmployee is given "peda"

  Scenario: Test set A
    When createEmployee is given "hhu12f"
    Then error message "Initials doesn't fit the restrictions, please input new initials."

  Scenario: Test set B
    When createEmployee is given "giig"
    Then error message "Employee with given initials already exist, please input new initials."

  Scenario: Test set C
    When createEmployee is given "huba"
    Then employee "huba" is added to the employeeDatabase