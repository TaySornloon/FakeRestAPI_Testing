package FakeRestAPI.step_def;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;

import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class CreateNewIDAndUpdate {

    Response res;
    Map<String, Object> allDetail;

    @When("I send a POST request to {string} endpoint with the following JSON data:")
    public void i_send_a_post_request_to_endpoint_with_the_following_json_data(String string, String docString) {

        String body = "{\n" +
                "  \"id\": 200,\n" +
                "  \"title\": \"string\",\n" +
                "  \"dueDate\": \"2023-06-22T16:12:54.180Z\",\n" +
                "  \"completed\": true\n" +
                "}";

        res = given().body(body).accept(ContentType.JSON).contentType(ContentType.JSON)
                .when().post("https://fakerestapi.azurewebsites.net/api/v1/Activities")
                .then().extract().response();


    }

    @Then("the API should respond with a {int} Created status code")
    public void the_api_should_respond_with_a_created_status_code(int int1) {
        Assert.assertEquals(int1, res.statusCode());

    }

    @Then("the response body should contain the newly created user details including the ID")
    public void the_response_body_should_contain_the_newly_created_user_details_including_the_id() {

        allDetail = res.path("");
        System.out.println("allDetail = " + allDetail);

        Assert.assertEquals(allDetail.containsValue(200), true);
        Assert.assertEquals(allDetail.containsValue("string"), true);

    }


    @When("I send the patch request with the following JSON data:")
    public void iSendThePatchRequestWithTheFollowingJSONData() {
        Map<String, Object> bodyUpdated = new LinkedHashMap<>();
        String body = "{\n" +
                "  \"id\": 200,\n" +
                "  \"title\": \"active 200\",\n" +
                "  \"dueDate\": \"2023-06-23T14:50:41.895Z\",\n" +
                "  \"completed\": false\n" +
                "}";

        res = given().body(body).contentType(ContentType.JSON).pathParam("id", 200)
                .when().put("https://fakerestapi.azurewebsites.net/api/v1/Activities/{id}");


    }

    @Then("Verify if completed status updated to {string}")
    public void verifyIfCompletedStatusUpdatedTo(String expected) {
        allDetail = res.path("");
        System.out.println("allDetail = " + allDetail);
        Assert.assertEquals(allDetail.containsValue(false), true);

    }


}
