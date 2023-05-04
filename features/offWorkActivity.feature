Feature: Create an off-work activity.
  Background: constant activity.
    Given an off work activity with name "Influenza" exists
    When createEmployee is given "giig"
    When createEmployee is given "mate"
    When createEmployee is given "heya"

  Scenario: User successfully adds a new constant activity to a project.
    Given a user is logged in
    When a user adds an activity with activityName "Vacation"
    Then the activity with name "Vacation" is added to off-work activities

  Scenario: Add a project unsuccessfully
    Given a user is logged in
    When a user adds an activity with activityName "Influenza"
    Then error message "An off-work activity with the given name already exists"

  Scenario: Add a project unsuccessfully
    Given a user is not logged in
    When a user adds an activity with activityName "Vacation"
    Then error message "User must be logged in to create activity"