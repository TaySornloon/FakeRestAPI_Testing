
Feature: Create a new user with valid data and update the existing data
  @wip3
  Scenario: Create a new user with valid data and receive a new ID
    Given the FakeRESTApi.Web V1 API is available
    When I send a POST request to "/users" endpoint with the following JSON data:
      """
      {
        "id": 200,
        "title": "Activity 200",
        "dueDate": "2023-06-22",
        "completed": true
      }
      """
    Then the API should respond with a 200 Created status code
    And the response body should contain the newly created user details including the ID

  Scenario: Update a existing data
    Given the FakeRESTApi.Web V1 API is available
    When I send the patch request with the following JSON data:
    Then Verify if completed status updated to "false"