package Resources;

import java.util.ArrayList;
import java.util.List;

import PojoClass.AddPlace;
import PojoClass.Sub_location;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class FirstCucumberUtility {
	
	public AddPlace addplace() {
		AddPlace add=new AddPlace();
		Sub_location sub=new Sub_location();
		
		sub.setLat(-38.383494);
		sub.setLng(33.427362);
		add.setLocation(sub);
		
		add.setAccuracy(50);
		add.setName("Frontline house");
		add.setPhone_number("(+91) 983 893 3937");
		add.setAddress("29, side layout, cohen 09");
		List<String> list=new ArrayList<String>();
		list.add("shoe park");
		list.add("shop");
		add.setTypes(list);
		add.setWebsite("http://google.com");
		add.setLanguage("French-IN");
		
		return add;
	}
	
	public RequestSpecification requestvalidation() {
		RequestSpecification request=new RequestSpecBuilder().setRelaxedHTTPSValidation().setContentType(ContentType.JSON)
				.setBasePath("https://rahulshettyacademy.com").addQueryParam("key","qaclick123").build();
		return request;
	}
	
	public ResponseSpecification responseValid() {
		ResponseSpecification response=new ResponseSpecBuilder().expectContentType(ContentType.JSON)
				.expectStatusCode(200).build();
		return response;
	}
}
