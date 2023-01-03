package StepDefinationsDemos;

import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

import java.io.FileNotFoundException;

import Resources.Utility;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;

public class SecondCucumber extends Utility {
	RequestSpecification res;
	String response;
	
	@Given("open front-end test api")
	public void open_front_end_test_api() throws FileNotFoundException {
	    res=given().log().all().spec(requestSpecification()).body("{\r\n"
	    		+ "    \"name\": \"morpheus\",\r\n"
	    		+ "    \"job\": \"leader\"\r\n"
	    		+ "}");
	}

	@When("provide post method extract response")
	public void provide_post_method_extract_response() {
		response=res.when().post("/api/users").then().assertThat().statusCode(201).extract().response().asPrettyString();
	}

	@Then("check id status code")
	public void check_id_status_code() {
		JsonPath path=new JsonPath(response);
		int id=path.getInt("id");
		System.out.println("Id is:\t"+id);
	}
}
