Feature: User - Account management

  Scenario: Create a new user
    Given I have a valid user payload
    When I send a POST request to "/user"

  Scenario: Retrieve user by username
    When I send a GET request to "/user/{username}"

  Scenario: Update user details
    When I send a PUT request to "/user/{username}"

  Scenario: Delete the user
    When I send a DELETE request to "/user/{username}"
