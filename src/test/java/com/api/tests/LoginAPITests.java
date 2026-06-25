package com.api.tests;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;
import com.api.pojo.UserCredentials;

import io.restassured.http.ContentType;
public class LoginAPITests {

	UserCredentials usercredentials = new UserCredentials("iamfd","password");
	
	@Test
	public void loginAPITest()
	{
		given()
			.baseUri("http://64.227.160.186:9000/v1")
			.and()
			.contentType(ContentType.JSON)
			.and()
			.accept(ContentType.JSON)
			.and()
			.body(usercredentials)
			.log().uri()
			.log().method()
			.log().headers()
			.log().body()
		.when()
			.post("login")
		.then()
		.log().all()
			.statusCode(200)
			.time(lessThan(5000L))
			.and()
			.body("message", equalTo("Success"));
			
		
	}
}
