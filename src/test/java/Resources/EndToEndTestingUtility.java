package Resources;

import io.restassured.path.json.JsonPath;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class EndToEndTestingUtility {
	String token,userId,productId;
	public static RequestSpecification request;
	public String login(String uname,String password) {
		return "{\r\n"
				+ "    \"userEmail\":\""+uname+"\",\r\n"
				+ "    \"userPassword\":\""+password+"\"\r\n"
				+ "}";
	}

	public RequestSpecification LoginHome() throws FileNotFoundException {
		if(request==null) {
			PrintStream print=new PrintStream(new FileOutputStream("EndToEndTesting.txt"));		
			request=new RequestSpecBuilder().setRelaxedHTTPSValidation()
					.setBaseUri("https://rahulshettyacademy.com")
					.addFilter(RequestLoggingFilter.logRequestTo(print))
					.addFilter(ResponseLoggingFilter.logResponseTo(print)).build();
			return request;
		}
		return request;
	}

	public String tokenGen(String response,String token) {
		JsonPath path=new JsonPath(response);
		this.token=path.getString(token);
		return this.token;
	}

	public String user_ID(String response,String id) {
		JsonPath path=new JsonPath(response);
		userId=path.get(id);
		return userId;
	}

	public String product_ID(String response,String id) {
		JsonPath path=new JsonPath(response);
		productId=path.getString(id);
		return productId;
	}
	
	public String orderProduct() {
		return "{\r\n"
				+ "    \"orders\":\r\n"
				+ "    [\r\n"
				+ "        {\r\n"
				+ "        \"country\":\"India\",\r\n"
				+ "        \"productOrderedId\":\""+productId+"\"\r\n"
				+ "        }\r\n"
				+ "    ]\r\n"
				+ "}";
	}
	
	public String orders(String response) {
		JsonPath path=new JsonPath(response);
		List<String> list=new ArrayList<String>();
		list.add(path.getString("orders"));
		String l=list.get(0).toString();
		l = l.substring(1, l.length() - 1);
		return l;
	}
	
	public String AddtoCart() {
		return "{\r\n"
				+ "    \"_id\": \""+userId+"\",\r\n"
				+ "    \"product\": {\r\n"
				+ "        \"_id\": \""+productId+"\",\r\n"
				+ "        \"productName\": \"qwerty\",\r\n"
				+ "        \"productCategory\": \"fashion\",\r\n"
				+ "        \"productSubCategory\": \"shirts\",\r\n"
				+ "        \"productPrice\": 11500,\r\n"
				+ "        \"productDescription\": \"Addias Originals\",\r\n"
				+ "        \"productImage\": \"https://rahulshettyacademy.com/api/ecom/uploads/productImage_1672328547479.png\",\r\n"
				+ "        \"productRating\": \"0\",\r\n"
				+ "        \"productTotalOrders\": \"0\",\r\n"
				+ "        \"productStatus\": true,\r\n"
				+ "        \"productFor\": \"women\",\r\n"
				+ "        \"productAddedBy\": \""+userId+"\",\r\n"
				+ "        \"__v\": 0\r\n"
				+ "    }\r\n"
				+ "}";
	}
}
