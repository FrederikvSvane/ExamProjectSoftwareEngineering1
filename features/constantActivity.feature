Feature: Create a constant activity.
  Background: constant activity.
    When createEmployee is given "giig"
    When createEmployee is given "mate"
    When createEmployee is given "heya"
    Given a user is logged in
    When user creates project with name "Awesome Project"

  Scenario: User successfully adds a new constant activity to a project.
    Given That a user is logged in and have selected an existing project.
    When a user adds an activity with activityName "Vacation", with Hours 60, and employee initials "mate"
    Then the activity with name "Computer app" is added to the project

  Scenario: Add a project unsuccessfully
    Given That a user is logged in and have selected an existing project.
    When a user adds an activity with activityName "Vacation", with Hours 60, and employee initials "mate"
    When a user adds an activity with activityName "Vacation", with Hours 60, and employee initials "mate"
    Then error message "An constant activity with the given name already exists"

  Scenario: Add a project unsuccessfully
    Given That a user is logged in and have selected an existing project.
    When a user adds an activity with activityName "Vacation", with Hours 60, and employee initials "mat8"
    Then error message "An constant activity cant be made without a real employee"