package Resources;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class Utility {
	RequestSpecification request;
	public RequestSpecification requestSpecification() throws FileNotFoundException {
		PrintStream log=new PrintStream(new FileOutputStream("log.txt"));
		request=new RequestSpecBuilder().setBaseUri("https://reqres.in/").setContentType(ContentType.JSON)
				.addFilter(RequestLoggingFilter.logRequestTo(log))
				.addFilter(ResponseLoggingFilter.logResponseTo(log)).build();
		return request;
	}
}
