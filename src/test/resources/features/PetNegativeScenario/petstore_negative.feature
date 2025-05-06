Feature: Pet API Negative Scenarios

  Scenario: Create pet with invalid data
    When I send a POST request to "/pet" with invalid body
    Then the response status should be 200

  Scenario: Get pet with non-existent ID
    When I send a GET request to "/pet/999999"
    Then the response status should be 404

  Scenario: Update pet without ID
    When I send a PUT request to "/pet" with incomplete body
    Then the response status should be 500

  Scenario: Delete pet with invalid ID
    When I send a DELETE request to "/pet/999999"
    Then the response status should be 404
