Feature: Add hours to project activity

  Background: An activity exists in a project
    When createEmployee is given "giig"
    When createEmployee is given "mate"
    When createEmployee is given "rass"
    Given a user is logged in
    When user creates project with name "Awesome Project"
    When user creates project with name "Awesome Project2"
    Given An activity "exampleActivity" is added to project "Awesome Project"
    Given An activity "exampleActivity2" is added to project "Awesome Project"
    Given An activity "exampleActivity5" is added to project "Awesome Project2"
    Given An activity "exampleActivity6" is added to project "Awesome Project2"



  Scenario: A user adds work hours to activity
    Given a user "mate" is logged in
    And An activity "exampleActivity" exists in project "Awesome Project"
    When The user adds 8 work hours to "exampleActivity" in project "Awesome Project"
    Then 8 work hours are added to "exampleActivity" in project "Awesome Project"

  Scenario: A user adds work hours to activity twice
    Given a user "rass" is logged in
    And An activity "exampleActivity2" exists in project "Awesome Project"
    When The user adds 8 work hours to "exampleActivity2" in project "Awesome Project"
    When The user adds 8 work hours to "exampleActivity2" in project "Awesome Project"
    Then 16 work hours are added to "exampleActivity2" in project "Awesome Project"

  Scenario: A user adds work hours to activity and can see the total work hours
    Given a user "rass" is logged in
    And An activity "exampleActivity5" exists in project "Awesome Project2"
    And An activity "exampleActivity6" exists in project "Awesome Project2"
    And "Awesome Project2" has 0 total work hours
    When The user adds 8 work hours to "exampleActivity5" in project "Awesome Project2"
    When The user adds 8 work hours to "exampleActivity6" in project "Awesome Project2"
    Then The project "Awesome Project2" has 16 total work hours


  Scenario: Not logged in user unsuccessfully adds hours to a project activity.
    Given a user is not logged in
    When The user adds 8 work hours to "exampleActivity" in project "Awesome Project"
    Then error message "User must be logged in to add hours"


  Scenario: User unsuccessfully adds negativ hours to a project activity twice.
    Given a user "mate" is logged in
    When The user adds -34 work hours to "exampleActivity" in project "Awesome Project"
    Then error message "Invalid number of hours"


  Scenario: A user fails to add work hours to activity
    Given a user "mate" is logged in
    And An activity "exampleActivity4" does not exist in project "Awesome Project"
    When The user adds 8 work hours to "exampleActivity4" in project "Awesome Project"
    Then error message "The activity does not exist in the project"


  Scenario: User makes a start and stopdate for a specific activity
    Given a user is logged in
    And An activity "exampleActivity" exists in project "Awesome Project"
    When the user adds the week 3 as start week and the length of the activity is 7 to activity "exampleActivity" in project "Awesome Project"
    Then The user have added a start week 3 and stopdate 10 to the activity "exampleActivity" in project "Awesome Project"

  Scenario: User makes a start and stopdate for a specific activity seprate
    Given a user is logged in
    And An activity "exampleActivity" exists in project "Awesome Project"
    When the user adds the week 3 as start week to activity "exampleActivity" in project "Awesome Project"
    When the user adds the length of the activity is 7 to activity "exampleActivity" in project "Awesome Project"
    Then The user have added a start week 3 and stopdate 10 to the activity "exampleActivity" in project "Awesome Project"

  Scenario: User makes a start and stopdate for a specific activity seprate
    Given a user is logged in
    And An activity "exampleActivity" exists in project "Awesome Project"
    When the user adds the week -3 as start week to activity "exampleActivity" in project "Awesome Project"
    Then error message "The given time is invalid"

  Scenario: User makes a start and stopdate for a specific activity seprate
    Given a user is logged in
    And An activity "exampleActivity" exists in project "Awesome Project"
    When the user adds 8 budgeted hours to activity "exampleActivity" in project "Awesome Project"
    Then the activity "exampleActivity" in project "Awesome Project" have 8 budgeted hours


  Scenario: User makes a start and stopdate for a specific activity seprate
    Given a user is logged in
    And An activity "exampleActivity" exists in project "Awesome Project"
    When the user adds the length of the activity is -7 to activity "exampleActivity" in project "Awesome Project"
    Then error message "The given time is invalid"

  Scenario: The user can not make a start and stopdate for a specific activity, because the activity does not exist
    Given a user is logged in
    And An activity "exampleActivity4" does not exist in project "Awesome Project"
    When the user adds the week 3 as start week and the length of the activity is 7 to activity "exampleActivity4" in project "Awesome Project"
    Then error message "The activity does not exist in the project"

  Scenario: User makes a start and stopdate for a specific activity
    Given a user is logged in
    And An activity "exampleActivity" exists in project "Awesome Project"
    When the user adds the week -3 as start week and the length of the activity is 7 to activity "exampleActivity" in project "Awesome Project"
    Then error message "The given time is invalid"

