package FakeRestAPI.step_def;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;

import static io.restassured.RestAssured.*;

public class GetRequest {

    Response response;

    @Given("the FakeRESTApi.Web V1 API is available")
    public void the_fake_rest_api_web_v1_api_is_available() {
        RestAssured.baseURI = "https://fakerestapi.azurewebsites.net";
    }
    @When("I send a GET request to {string} endpoint")
    public void i_send_a_get_request_to_endpoint(String string) {
       response = given().accept(ContentType.JSON).when().get("https://fakerestapi.azurewebsites.net/api/v1/activities")
                .prettyPeek().then().extract().response();
    }
    @Then("the API should respond with a {int} OK status code")
    public void the_api_should_respond_with_a_ok_status_code(Integer status) {
        System.out.println("response.statusCode() = " + response.statusCode());
        Assert.assertEquals(response.statusCode(),200);
    }

}
