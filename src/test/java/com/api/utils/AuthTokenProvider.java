package com.api.utils;

import static io.restassured.RestAssured.*;

import org.hamcrest.Matchers;

import static com.api.constants.Role.*;

import com.api.constants.Role;
import com.api.pojo.UserCredentials;

import io.restassured.http.ContentType;
public class AuthTokenProvider {
	
	private AuthTokenProvider()
	{
		
	}

	public static String getToken(Role role) {
		// Make the login api request and extract the auth token from it
		UserCredentials userCredentials = null;
		if(role == FD)
		{
			userCredentials = new UserCredentials("iamfd","password");
		}
		else if(role == SUP)
		{
			userCredentials = new UserCredentials("iamsup","password");
		}
		else if(role == ENG)
		{
			userCredentials = new UserCredentials("iameng","password");
		}
		else if(role == QC)
		{
			userCredentials = new UserCredentials("iamqc","password");
		}
		 	String token =	given()
							.baseUri(ConfigManager.getProperty("BASE_URI"))
							.contentType(ContentType.JSON)
							.body(userCredentials)
							.when()
						.post("login")
							.then()
							.log().ifValidationFails()
							.statusCode(200)
							.body("message", Matchers.equalTo("Success"))
							.extract()
							.body()
							.jsonPath()
							.getString("data.token");
		 	
		 	System.out.println("---------------------------------------------------");
		 	System.out.println("Token :"+token);
		 	return token;
	}

}
