package StepDefinationsDemos;

import static io.restassured.RestAssured.given;

import Resources.AddBookUtility;
import Resources.parameterizationEnamDemo;
import io.cucumber.java.en.*;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;

public class parameterizationCucumberRest extends AddBookUtility {
	RequestSpecification req;
	String response;
	@Given("Open AddBook.php")
	public void open_add_book_php() throws Exception {
		//Add Place API
		req=given().log().all().spec(requestSpec()).body(addbook1());
	}

	@When("{string} values request and {string} method")
	public void values_request_and_method(String resource, String method) {
		parameterizationEnamDemo parameter= parameterizationEnamDemo.valueOf(resource);
		if(method.equalsIgnoreCase("post")) {
			response=req.when().log().all().post(parameter.getRes()).then().spec(responseSpec())
					.extract().asString();
		}
		
		//System.out.println("parameter is:\t"+parameter.getRes());
	}

	@Then("extract the {string}")
	public void extract_the(String output) {
		JsonPath path=new JsonPath(response);
		if(output.equalsIgnoreCase("id")) {
			System.out.println("Id is:\t"+path.get(output));
		}else {
			System.out.println("Message is:\t"+path.get(output));
		}
	}
}
