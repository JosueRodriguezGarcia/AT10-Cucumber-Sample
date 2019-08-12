Feature: Create a gist
  Scenario: User creates a gist on GitHub
    Given alszla has credentials for GitHub
      And is authenticated at GitHub
    And has json data
    When a user makes a post request
    Then the create status code is 201
      And response includes the following
      | files.nombre.filename | nombre         |
      | files.nombre.content  | BDD + IntelliJ |
