Feature: Verify GET request on /users endpoint
@wip1
  Scenario: Verify GET request on /users endpoint returns a 200 OK response
    Given the FakeRESTApi.Web V1 API is available
    When I send a GET request to "/api/v1/activities" endpoint
    Then the API should respond with a 200 OK status code