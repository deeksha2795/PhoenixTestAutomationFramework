package com.api.tests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

import java.io.IOException;

import org.testng.annotations.Test;

import com.api.pojo.UserCredentials;
import com.api.utils.ConfigManager;
import com.api.utils.SpecUtil;

import static com.api.utils.ConfigManagerOLD.*;

import io.restassured.http.ContentType;
public class LoginAPITests {

	 
	UserCredentials usercredentials = new UserCredentials("iamfd","password");
	
	@Test
	public void loginAPITest() throws IOException
	{
		given()
			.spec(SpecUtil.requestSpec(usercredentials))//here .spec applies the pre-built RequestSpecification that bundles up reusable settings so that we dont have to call them again and again every test
		.when()
			.post("login")
		.then()
			.spec(SpecUtil.responseSpec_OK())
			.body("message", equalTo("Success"));
			
		
	}
}
