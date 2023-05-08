Feature: White Box test of the addProjectActivity method from the Project class.
  Background: project activity.
    When createEmployee is given "giig"
    When createEmployee is given "mate"
    When createEmployee is given "heya"
    Given a user is logged in
    When user creates project with name "Awesome Project"

  Scenario: Test set A
    Given That a user is logged in and have selected an existing project.
    When user adds an activity with activityName "", with 100 budgeted hours, a start week 18 and duration of 2 weeks
    Then error message "The activity name is invalid" was given

  Scenario: Test set B
    Given That a user is logged in and have selected an existing project.
    When user adds an activity with activityName "Computer app3", with -5 budgeted hours, a start week 33 and duration of 12 weeks
    Then error message "The amount of hours is invalid" was given

  Scenario: Test set C
    Given That a user is logged in and have selected an existing project.
    When user adds an activity with activityName "Computer app3", with 5 budgeted hours, a start week 100 and duration of -5 weeks
    Then error message "The given timeframe is invalid" was given

  Scenario: Test set D
    Given That a user is logged in and have selected an existing project.
    When user adds an activity with activityName "Computer app", with 100 budgeted hours, a start week 18 and duration of 2 weeks
    When user adds an activity with activityName "Computer app", with 100 budgeted hours, a start week 18 and duration of 2 weeks
    Then error message "An activity with the given name already exists" was given

  Scenario: Test set E
    Given That a user is logged in and have selected an existing project.
    When user adds an activity with activityName "Computer app", with 100 budgeted hours, a start week 18 and duration of 2 weeks
    Then the activity with name "Computer app" is added to the project

  Scenario: Test set F
    Given That a user is logged in and have selected an existing project.
    And The user "mate" is the project leader of the project
    When user adds an activity with activityName "Computer app3", with 100 budgeted hours, a start week 18 and duration of 2 weeks
    Then error message "Activity can not be made when user is not the projectleader" was given



