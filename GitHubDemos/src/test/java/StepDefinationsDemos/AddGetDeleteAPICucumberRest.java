package StepDefinationsDemos;

import static io.restassured.RestAssured.given;

import java.io.FileNotFoundException;

import Resources.AddBookUtility;
import Resources.parameterizationEnamDemo;
import io.cucumber.java.en.*;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;

public class AddGetDeleteAPICucumberRest extends AddBookUtility{
	RequestSpecification req,get1,update;
	String response,msg,response2,response3;
	@Given("Open AddBook.php Page")
	public void open_add_book_php_page() throws Exception {
		req=given().log().all().spec(requestSpec()).body(addbook1());
	}

	@When("{string} values request {string} method")
	public void values_request_method(String resource, String method) {
		parameterizationEnamDemo parameter= parameterizationEnamDemo.valueOf(resource);
		if(method.equalsIgnoreCase("post")) {
			response=req.when().log().all().post(parameter.getRes()).then().spec(responseSpec())
					.extract().asString();
		}else if(method.equalsIgnoreCase("get")) {
			response=get1.when().log().all().get(parameter.getRes()).then().spec(responseSpec())
					.extract().asPrettyString();
		}else {
			response=update.when().log().all().delete(parameter.getRes()).then().spec(responseSpec())
					.extract().asPrettyString();
		}
		//System.out.println("Resonse Body:\n"+response);
	}

	@Then("extract the {string} and {string}")
	public void extract_the_and(String id, String msg) {
		/*
		JsonPath path=new JsonPath(response);
		if(id.equalsIgnoreCase("id")) {
			this.id=path.getString(id);
			System.out.println("Id is:\t"+this.id);
		}else {
			this.msg=path.getString(msg);
			System.out.println("Message is:\t"+path.get(this.msg));
		}*/
		
		//Extract id from AddBookUtility class
		String ids=getID(response,id);
		System.out.println("Id is:\t"+ids);
	}

	@Then("check Id is matching with {string} Query Params and method {string}")
	public void check_id_is_matching_with_query_params_and_method(String resource, String method) throws Exception {
		get1=given().log().all().queryParam("ID",getID(response,"ID")).spec(requestSpec());
		
		//Here we will call the same method for get and delete API's
		values_request_method(resource,method);
		System.out.println("Resonse Body:\n"+response);
		
		/*
		parameterizationEnamDemo parameter= parameterizationEnamDemo.valueOf(resource);
		if(method.equalsIgnoreCase("get")) {
			response2=get1.when().log().all().get(parameter.getRes()).then().spec(responseSpec())
					.extract().asPrettyString();

			System.out.println("Resonse Body\n"+response2);
		}*/
	}

	@Then("{string} delete the exisiting method author and method {string}")
	public void delete_the_exisiting_method_author_and_method(String resource, String method) throws Exception {
		update=given().log().all().spec(requestSpec()).body(deleteBook());
		values_request_method(resource,method);
		System.out.println("Resonse Body:\n"+response);
		
		/*
		parameterizationEnamDemo parameter= parameterizationEnamDemo.valueOf(resource);
		if(method.equalsIgnoreCase("delete")) {
			response3=update.when().log().all().delete(parameter.getRes()).then().spec(responseSpec())
					.extract().asPrettyString();

			System.out.println("Resonse Body\n"+response3);
		}*/
	}
}
