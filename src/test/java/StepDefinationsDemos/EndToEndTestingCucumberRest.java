package StepDefinationsDemos;

import io.cucumber.java.en.*;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;

import Resources.EndToEndTestingEnumCucumber;
import Resources.EndToEndTestingUtility;

public class EndToEndTestingCucumberRest extends EndToEndTestingUtility{
	String response,response2,response3,response4,response5,response6,response7,response8;
	
	RequestSpecification request;
	File file = new File("C:\\Users\\hrutik.prakash\\Pictures\\Screenshots\\Screenshot (56).png");
	//BeforeClass tags
	@Given("User in Login Home Page")
	public void user_in_login_home_page() throws FileNotFoundException {
		request=given().log().all().spec(LoginHome()).contentType(ContentType.JSON);
	}

	@When("enter {string} and {string}")
	public void enter_and(String uname, String password) {
		EndToEndTestingEnumCucumber end=EndToEndTestingEnumCucumber.valueOf("Login");
		response=request.body(login(uname, password)).when().log().all().post(end.getOrder()).then().assertThat()
				.statusCode(200).extract().response().asPrettyString();
		String token=tokenGen(response,"token");
		System.out.println("Token is:\t"+token);

	}

	@Then("user appear in home page")
	public void user_appear_in_home_page() {
		JsonPath path=new JsonPath(response);
		String message=path.getString("message");
		System.out.println(message);
	}

	@Then("{string} to home Page")
	public void to_home_page(String addProduct) throws FileNotFoundException {
		EndToEndTestingEnumCucumber end=EndToEndTestingEnumCucumber.valueOf(addProduct);
		response2=given().log().all().spec(LoginHome())
				.urlEncodingEnabled(false).contentType("multipart/form-data")
				.param("productName","qwerty").param("productAddedBy",user_ID(response,"userId"))
				.param("productCategory","fashion").param("productSubCategory", "shirts")
				.param("productPrice",1150).param("productDescription","Addias Originals")
				.param("productFor", "women")
				.multiPart("productImage",file)
				.header("Authorization",tokenGen(response,"token"))
				.when().log().all().post(end.getOrder()).then().extract().response()
				.asPrettyString();

		String proId=product_ID(response2,"productId");
		System.out.println("Product ID is:\t"+proId);
	}

	@When("{string} it must be appear in order list")
	public void it_must_be_appear_in_order_list(String orderProduct) throws FileNotFoundException {

		EndToEndTestingEnumCucumber end=EndToEndTestingEnumCucumber.valueOf(orderProduct);
		response3=given().log().all().spec(LoginHome()).body(orderProduct())
				.contentType(ContentType.JSON)
				.header("Authorization",tokenGen(response,"token"))
				.when().log().all().post(end.getOrder()).then().assertThat().statusCode(201)
				.extract().response().asPrettyString();
		String orderId=orders(response3);
		System.out.println("Response3 Body"+response3);
		System.out.println("Order_ID is:\t"+orderId);
	}

	@When("{string} it must be in My Cart list")
	public void it_must_be_in_my_cart_list(String viewProduct) throws FileNotFoundException {
		EndToEndTestingEnumCucumber end=EndToEndTestingEnumCucumber.valueOf(viewProduct);
		response4=given().log().all().spec(LoginHome())
				.urlEncodingEnabled(false).queryParam("id",orders(response3))
				.header("Authorization",tokenGen(response,"token"))
				.when().log().all().get(end.getOrder()).then().assertThat().statusCode(200)
				.contentType(ContentType.JSON).extract().response()
				.asPrettyString();

		System.out.println("Order Detail's\n"+response4);
	}

	@When("{string} after it must be delete")
	public void after_it_must_be_delete(String deleteOrder) throws FileNotFoundException {
		EndToEndTestingEnumCucumber end=EndToEndTestingEnumCucumber.valueOf(deleteOrder);
		response5=given().log().all().spec(LoginHome()).header("Authorization",tokenGen(response,"token"))
		.pathParam("order",orders(response3)).when().log().all()
		.delete(end.getOrder())
		.then().assertThat().statusCode(200).extract().response().asPrettyString();
		System.out.println(response5);
	}

	@When("{string} from webSite")
	public void from_web_site(String deleteProduct) throws FileNotFoundException {
		EndToEndTestingEnumCucumber end=EndToEndTestingEnumCucumber.valueOf(deleteProduct);
		response6=given().log().all().spec(LoginHome()).header("Authorization",tokenGen(response,"token"))
		.pathParam("product_Id",product_ID(response2,"productId")).when().log().all()
		.delete(end.getOrder()).then().assertThat().statusCode(200)
		.extract().response().asPrettyString();
		
		System.out.println(response6);
	}
	
	@When("{string} check its add to the cart or not")
	public void check_its_add_to_the_cart_or_not(String addToCart) throws FileNotFoundException {
		EndToEndTestingEnumCucumber end=EndToEndTestingEnumCucumber.valueOf(addToCart);
		response7=given().log().all().spec(LoginHome()).contentType(ContentType.JSON)
				.header("Authorization",tokenGen(response,"token"))
		.queryParam("productId",product_ID(response2,"productId")).body(AddtoCart()).when().log().all()
		.post(end.getOrder()).then().assertThat().statusCode(200).extract().response().asPrettyString();
		
		System.out.println(response7);
	}
	
	@When("{string} product from cart")
	public void product_from_cart(String deleteProduct) throws FileNotFoundException {
		EndToEndTestingEnumCucumber end=EndToEndTestingEnumCucumber.valueOf(deleteProduct);
		response8=given().log().all().spec(LoginHome())
		.header("Authorization",tokenGen(response,"token"))
		.pathParam("userId",user_ID(response,"userId"))
		.pathParam("productId",product_ID(response2,"productId"))
		.when().log().all().delete(end.getOrder()).then().assertThat().statusCode(200)
		.extract().response().asPrettyString();
		
		System.out.println(response8);
	}
	/*
	//AfterClass tags
	String after;
	@Given("Add the Product to Home_Page of rahulSheety academy")
	public void Add_the_Product_to_Home_Page_of_rahulSheety_academy() {
		System.out.println("After class");
		JsonPath path=new JsonPath(response);
		String message=path.getString("message");
		System.out.println(message);
	}
	
	@When("{string} to rahulsheety_academy")
	public void to_rahulsheety_academy(String addProduct) throws FileNotFoundException {
		System.out.println("After class");
		EndToEndTestingEnumCucumber end=EndToEndTestingEnumCucumber.valueOf(addProduct);
		after=given().log().all().spec(LoginHome())
				.urlEncodingEnabled(false).contentType("multipart/form-data")
				.param("productName","qwerty").param("productAddedBy",user_ID(response,"userId"))
				.param("productCategory","fashion").param("productSubCategory", "shirts")
				.param("productPrice",1150).param("productDescription","Addias Originals")
				.param("productFor", "women")
				.multiPart("productImage",file)
				.header("Authorization",tokenGen(response,"token"))
				.when().log().all().post(end.getOrder()).then().extract().response()
				.asPrettyString();
		System.out.println("After \n"+after);

	}
	@Then("Check {string} is valid or not")
	public void check_is(String productId) {
		System.out.println("After class");
	    JsonPath path=new JsonPath(after);
	    String id1=path.getString(productId);
	    assertEquals(id1,product_ID(response2,"productId"));
	    
	}*/
}
