Feature: Create a project activity.
  Background: project activity.
    When createEmployee is given "giig"
    When createEmployee is given "mate"
    When createEmployee is given "heya"
    Given a user is logged in
    When user creates project with name "Awesome Project"

  Scenario: User successfully adds a new activity to a project.
    Given That a user is logged in and have selected an existing project.
    When a user adds an activity with activityName "Computer app", with 100 budgeted hours, a start week 18 and duration of 2 weeks
    Then the activity with name "Computer app" is added to the project

  Scenario: Add an activity unsuccessfully
    Given That a user is logged in and have selected an existing project.
    When a user adds an activity with activityName "Computer app", with 100 budgeted hours, a start week 18 and duration of 2 weeks
    When a user adds an activity with activityName "Computer app", with 100 budgeted hours, a start week 18 and duration of 2 weeks
    Then error message "An activity with the given name already exists"

  Scenario: Projectleader successfully adds a new activity to a project.
    Given That a user is logged in and have selected an existing project.
    And The user "giig" is the project leader of the project
    When a user adds an activity with activityName "Computer app", with 100 budgeted hours, a start week 18 and duration of 2 weeks
    Then the activity with name "Computer app" is added to the project

  Scenario: User unsuccessfully adds a new activity to a project, because there is a projectleader.
    Given That a user is logged in and have selected an existing project.
    And The user "mate" is the project leader of the project
    When a user adds an activity with activityName "Computer app3", with 100 budgeted hours, a start week 18 and duration of 2 weeks
    Then error message "Activity can not be made when user is not the projectleader"