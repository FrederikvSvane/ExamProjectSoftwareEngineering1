Feature: Add hours to project activity
  
  Background: An activity exists in a project
    When createEmployee is given "giig"
    When createEmployee is given "mate"
    Given a user is logged in
    When user creates project with name "Awesome Project"
    And An activity "exampleActivity" is added to project "Awesome Project"


  Scenario: A user adds work hours to activity
    Given a user "mate" is logged in
    And An activity "exampleActivity" exists in project "Awesome Project"
#    And The user has 8 unregistered work hours
    When The user adds 8 work hours to "exampleActivity" in project "Awesome Project"
    Then 8 work hours are added to "exampleActivity" in project "Awesome Project"


  Scenario: A user fails to add work hours to activity
    Given a user "mate" is logged in
    And An activity "exampleActivity" exists in project "Awesome Project"
#    And The user has 0 unregistered work hours
#    When The user adds 8 work hours to "exampleActivity" in project "Awesome Project"
#    Then No work hours are added to "exampleActivity" in project "Awesome Project"


  Scenario: A user fails to add work hours to activity
    Given a user "mate" is logged in
    And An activity "exampleActivity" does not exist in project "Awesome Project"
#    And The user has 8 unregistered work hours
#    When The user adds 8 work hours to "exampleActivity" in project "Awesome Project"
#    Then No work hours are added to "exampleActivity" in project "Awesome Project"