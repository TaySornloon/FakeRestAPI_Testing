Feature: Verify author endpoint to retrieve details of ID 141 and create the new User id



  Scenario: Retrieve data of ID 141 from fakerrestapiwebsites.net using a GET request

    Given the author endpoint is available
    When a GET request is sent to fakerrestapiwebsites.net with ID 141
    And Verify status code is 200
    Then the retrieved data should match the expected details for ID 141:
      """
      {
        "id": 141,
        "idBook": 45,
        "firstName": "First Name 141",
        "lastName": "Last Name 141"
      }
      """

  @wip5
  Scenario: User registration with valid information
    Given the user registration endpoint is available
    When a POST request is sent to the user registration endpoint with the following details:
    Then Verify status code 200
    And the response should contain the user's account details

