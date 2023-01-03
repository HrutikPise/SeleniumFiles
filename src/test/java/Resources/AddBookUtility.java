package Resources;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

import PojoClass.AddBook;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class AddBookUtility {
	private static RequestSpecification request;
	String ids=null;
	public RequestSpecification requestSpec() throws FileNotFoundException {
		if(request==null) {
			PrintStream print=new PrintStream(new FileOutputStream("log1.txt"));
			request=new RequestSpecBuilder().setRelaxedHTTPSValidation()
					.setBaseUri("http://216.10.245.166")
					.addFilter(RequestLoggingFilter.logRequestTo(print))
					.addFilter(ResponseLoggingFilter.logResponseTo(print))
					.setContentType(ContentType.JSON).build();
			
			return request;
		}
		return request;
	}
	
	public ResponseSpecification responseSpec() {
		ResponseSpecification response=new ResponseSpecBuilder().expectStatusCode(200)
				.expectContentType(ContentType.JSON).build();
		return response;
	}
	
	public AddBook addBook(String name,String isbn,String aisle) {
		AddBook add=new AddBook();
		add.setName(name);
		add.setIsbn(isbn);
		add.setAisle(aisle);
		add.setAuthor("John foe");
		
		return add;
	}
	
	public AddBook addbook1() {
		AddBook add=new AddBook();
		add.setName("Learn Appium Automation with Java");
		add.setIsbn("Pizza");
		add.setAisle("077");
		add.setAuthor("Virat");
		
		return add;
	}
	
	public String getID(String response,String id) {
		JsonPath path=new JsonPath(response);
		ids=path.getString(id);
		return ids;
	}
	
	public String deleteBook() {
		return "{\r\n"
				+ " \r\n"
				+ "\"ID\" : \""+ids+"\"\r\n"
				+ " \r\n"
				+ "} ";
	}
}
