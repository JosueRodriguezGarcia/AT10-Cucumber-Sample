Feature: Retrieve a gist
  Scenario: User calls GitHub api to get a gist created after a given time
    Given a gist exists since 2019-08-08T13:00:00Z
    When a user retrieves gist by time
    Then the status code is 200
