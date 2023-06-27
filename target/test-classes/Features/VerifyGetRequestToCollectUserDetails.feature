@fetchId
Feature: Fetch id
  As a user
  I want to ensure that the FakeRESTApi.Web V1 API returns the correct id details
  When I send a GET request to the "/users/{id}" endpoint with a valid user ID
  @wip2
  Scenario: Retrieve id details with a valid ID
    Given a valid user ID "12"
    When I send a GET request to "/users/12"
    Then the response status code should be 200
    Then the response body should contain the  details

@wip3
  Scenario: In a test, fail if the following id does not exist
    When retrieve detail of id 17 from the list
    Then the response status code should be 200
    And Verify if completed status is "false"
