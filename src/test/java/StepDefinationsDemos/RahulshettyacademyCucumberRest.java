package StepDefinationsDemos;

import io.cucumber.java.en.*;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;

import Resources.FirstCucumberUtility;
public class RahulshettyacademyCucumberRest extends FirstCucumberUtility{
	RequestSpecification req;
	String response;
	@Given("Add Place Payload")
	public void add_place_payload() {
		req=given().log().all().spec(requestvalidation()).body(addplace());
	}

	@When("Add post request method and take response")
	public void add_post_request_method_and_take_response() {
		response=req.when().log().all().post("/maps/api/place/add/json").then().extract().response().asString();
	}

	@Then("Check place_id and status")
	public void check_place_id_and_status() {
		JsonPath path=new JsonPath(response);
		String place=path.getString("place_id");
		System.out.println("Place_id is:\t"+place);
	}
}
