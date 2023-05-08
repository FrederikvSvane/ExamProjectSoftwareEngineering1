Feature: White box tests of the method createEmployee from the EmployeeBase class.

  Background:
    When createEmployeeWhiteBox is given "giig"
    When createEmployeeWhiteBox is given "mate"
    When createEmployeeWhiteBox is given "peda"

  Scenario: Test set A
    When createEmployeeWhiteBox is given "hhu12f"
    Then error message "Initials doesn't fit the restrictions, please input new initials." is given

  Scenario: Test set B
    When createEmployeeWhiteBox is given "giig"
    Then error message "Employee with given initials already exist, please input new initials." is given

  Scenario: Test set C
    When createEmployeeWhiteBox is given "huba"
    Then employee "huba" is added to the employeeDatabase