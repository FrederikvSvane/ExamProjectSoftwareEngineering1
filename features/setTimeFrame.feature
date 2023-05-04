Feature: Set time frame for project

  Background: A logged in user has created a project with name "Awesome Project"
    Given a user is logged in
    When user creates project with name "Awesome Project"


  Scenario: Time frame is set
    Given a project is selected
    When the project is a start week number of 2 and 3 weeks duration
    Then project start date is set to 2
    And project end date is set to 5


  Scenario: The frame is set unsuccessfully
    Given a project is selected
    When the project is a start week number of 0 and 4 weeks duration
    Then error message "The given timeframe is invalid"


  Scenario: The frame is set unsuccessfully
    Given a project is selected
    When the project is a start week number of 10 and -1 weeks duration
    Then error message "The given timeframe is invalid"

