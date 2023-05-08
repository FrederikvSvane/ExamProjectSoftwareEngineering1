#Frederik
Feature: White box tests of the method addHours from the class Activity

  Background:
    Given An activity "exampleActivity" exists
    Given employee "man" is added to the employeeBase

  Scenario: Test set A
    Given a user is not logged in
    When the method addHours is called with parameters employeeInitials "man" and hours 1
    Then error message "User must be logged in to add hours" is gotten

  Scenario: Test set B
    Given employee "man" is added to the employeeBase
    And a user "man" is logged in
    When the method addHours is called with parameters employeeInitials "man" and hours -1
    Then error message "Invalid number of hours" is gotten

  Scenario: Test set C
    Given a user "man" is logged in
    And employee "man" is added to the employeeHours
    When the method addHours is called with parameters employeeInitials "man" and hours 1
    Then the activity "exampleActivity" has 1 hours

  Scenario: Test set D
    And a user "man" is logged in
    And employee "man" does not exist in employeeHours
    When the method addHours is called with parameters employeeInitials "man" and hours 1
    Then ("man",1) is added to employeeHours
    And the activity "exampleActivity" has 1 hours
