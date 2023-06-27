package FakeRestAPI.step_def;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;

import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class SendGetAndPostToAuthor {
    Response response;
    String url = "https://fakerestapi.azurewebsites.net/api/v1/Authors";
    Map<String, Object> postBody = new LinkedHashMap<>();

    @Given("the author endpoint is available")
    public void the_author_endpoint_is_available() {
        response = given().accept(ContentType.JSON).when().get
                        (url)
                .then().extract().response();
    }

    @When("a GET request is sent to fakerrestapiwebsites.net with ID {int}")
    public void a_get_request_is_sent_to_fakerrestapiwebsites_net_with_id(Integer int1) {
        response = given().accept(ContentType.JSON).pathParam("id", 141)
                .when().get("https://fakerestapi.azurewebsites.net/api/v1/Authors/{id}");
    }

    @And("Verify status code is {int}")
    public void verifyStatusCodeIs(int statusCode) {
        Assert.assertEquals(statusCode, response.statusCode());
    }

    @Then("the retrieved data should match the expected details for ID {int}:")
    public void the_retrieved_data_should_match_the_expected_details_for_id(Integer id, String docString) {
        JsonPath jsonPath = response.jsonPath();
        Map<String, Object> actualBody = jsonPath.get("");
        int idBook = jsonPath.getInt("idBook");
        System.out.println("idBook = " + idBook);

        Map<String, Object> expectedBody = new LinkedHashMap<>();
        expectedBody.put("id", 141);
        expectedBody.put("idBook", idBook);
        expectedBody.put("firstName", "First Name 141");
        expectedBody.put("lastName", "Last Name 141");

        System.out.println("stringBody = " + expectedBody);
        System.out.println("actualBody = " + actualBody);

        Assert.assertEquals(expectedBody, actualBody);


    }

    @Given("the user registration endpoint is available")
    public void theUserRegistrationEndpointIsAvailable() {
        given().accept(ContentType.JSON).when().get(url);
    }

    @When("a POST request is sent to the user registration endpoint with the following details:")
    public void aPOSTRequestIsSentToTheUserRegistrationEndpointWithTheFollowingDetails() {

        postBody.put("id", 650);
        postBody.put("idBook", 100);
        postBody.put("firstName", "First Name 650");
        postBody.put("lastName", "Last Name 650");

        response = given().body(postBody).accept(ContentType.JSON).contentType(ContentType.JSON)
                .when().post(url).prettyPeek();
        System.out.println("postBody = " + postBody);


    }

    @Then("Verify status code {int}")
    public void verifyStatusCode(int statusCode) {
        Assert.assertEquals(statusCode,response.statusCode());

    }

    @And("the response should contain the user's account details")
    public void theResponseShouldContainTheUserSAccountDetails() {
    Map<String,Object>expectedBody = new LinkedHashMap<>();
    expectedBody = postBody;
    JsonPath actual = response.jsonPath();
    Assert.assertEquals(expectedBody,actual.<Map<String, Object>>get(""));

    }



}
