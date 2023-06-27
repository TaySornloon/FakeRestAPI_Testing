package FakeRestAPI.step_def;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;

import java.util.*;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class Fetch_Id {
    Response response;
    static Map<String, Object> idInfo = new HashMap<>();
    String url = "https://fakerestapi.azurewebsites.net/api/v1/activities";
    static List<Map<String, Object>> getTableMap;
    static String checkId = "";

    @Given("a valid user ID {string}")
    public void a_valid_user_id(String string) {
        RestAssured.baseURI = "https://fakerestapi.azurewebsites.net";
    }

    @When("I send a GET request to {string}")
    public void i_send_a_get_request_to(String string) {
        response = given().accept(ContentType.JSON).pathParam("id", "12").when()
                .get(url + "/{id}").prettyPeek().then().extract().response();

    }

    @Then("the response status code should be {int}")
    public void the_response_status_code_should_be(Integer int1) {

        Assert.assertEquals(response.statusCode(), 200);
    }

    @Then("the response body should contain the  details")
    public void the_response_body_should_contain_the_details() {

        JsonPath jsonPath = response.jsonPath();
        idInfo = jsonPath.get("");
        System.out.println("idInfo = " + idInfo);
        Assert.assertEquals(idInfo.containsValue("Activity 12"),true);

    }

    @When("retrieve detail of id {int} from the list")
    public void retrieveDetailOfIdFromTheList(int id) {

        response = given().accept(ContentType.JSON).when().get(url);
        JsonPath jsonPath = response.jsonPath();
        getTableMap = jsonPath.getList("");
        System.out.println("getTable = " + getTableMap);

        for (int i = 0; i< getTableMap.size()-1; i++){
            if (getTableMap.get(i).get("id").equals(17)){
                checkId = getTableMap.get(i).toString();
            }
        }
        System.out.println("checkId = " + checkId);

    }
    @Then("the response status code should be {string}")
    public void theResponseStatusCodeShouldBe() {
        Assert.assertEquals(200,response.statusCode());
    }
    @And("Verify if completed status is {string}")
    public void verifyIfCompletedStatusIs(String completed) {

       Assert.assertEquals(checkId.contains("false"),true);
    }
}
