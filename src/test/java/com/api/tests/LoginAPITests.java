package com.api.tests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

import java.io.IOException;

import org.testng.annotations.Test;

import com.api.pojo.UserCredentials;
import com.api.utils.ConfigManager;

import static com.api.utils.ConfigManagerOLD.*;

import io.restassured.http.ContentType;
public class LoginAPITests {

	 
	UserCredentials usercredentials = new UserCredentials("iamfd","password");
	
	@Test
	public void loginAPITest() throws IOException
	{
		given()
			.baseUri(ConfigManager.getProperty("BASE_URI"))
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
