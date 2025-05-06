Feature: Pet Store API Tests

  Scenario: Create a new pet
    Given I have a valid pet payload with name "doggie" and status "available"
    Then the pet should be created successfully

  Scenario: Retrieve pet details
    Given I have a valid pet payload with name "doggie" and status "available"
    When I send a GET request to the pet endpoint
    Then the pet details should be returned successfully

  Scenario: Update pet details
    Given I have a valid pet payload with name "doggie" and status "available"
    When I update the pet status to "sold"
    Then the pet details should be updated successfully

  Scenario: Delete a pet
    Given I have a valid pet payload with name "doggie" and status "available"
    When I delete the pet
    Then the pet should be successfully deleted
