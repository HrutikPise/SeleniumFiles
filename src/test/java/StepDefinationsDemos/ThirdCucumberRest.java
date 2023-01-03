package StepDefinationsDemos;

import io.cucumber.java.en.*;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;

import java.io.FileNotFoundException;

import Resources.AddBookUtility;
public class ThirdCucumberRest extends AddBookUtility {
	
	RequestSpecification req;
	String response;
	
	@Given("^Open AddBook.php (.*) (.*) (.*)$")
	public void open_add_book_php_name_isbn_aisle(String name,String isbn,String aisle) throws FileNotFoundException {
		req=given().log().all().spec(requestSpec()).body(addBook(name,isbn,aisle));
	}

	@When("Add values request")
	public void add_values_request_name_isbn_aisle() {
		response=req.when().log().all().post("/Library/Addbook.php")
				.then().spec(responseSpec()).extract().response().asString();
		
	}

	@Then("extract the Msg and ID")
	public void extract_the_msg_and_id() {
		JsonPath path=new JsonPath(response);
		String msg=path.getString("Msg");
		String id=path.getString("ID");
		
		System.out.println("Message is:\t"+msg);
		System.out.println("Id is:\t"+id);
	}
}
