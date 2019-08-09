Feature: Retrieve a gist
  Scenario: User creates a gist on GitHub
  Given a user is registered at GitHub
  And user has content
  When a user makes a post request
  Then the create status code is 201
  #And response includes the following in any order
  #| owner.login           | alszla            |
  #| owner.type            | User              |