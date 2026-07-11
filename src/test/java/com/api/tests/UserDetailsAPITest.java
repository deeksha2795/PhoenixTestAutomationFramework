package com.api.tests;
import static io.restassured.RestAssured.*;

import java.io.IOException;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import static com.api.utils.AuthTokenProvider.*;

import static com.api.constants.Role.*;
import com.api.utils.ConfigManager;

import static com.api.utils.ConfigManagerOLD.*;

import io.restassured.http.ContentType;
import io.restassured.http.Header;

public class UserDetailsAPITest {

	//ConfigManager configManager = new ConfigManager();
	@Test
	public void userDetailsAPITest() throws IOException
	{
		Header authHeader = new Header("Authorization",getToken(FD));
		given()
			.baseUri(ConfigManager.getProperty("BASE_URI"))
			.and()
			.header(authHeader)
			.and()
			.accept(ContentType.JSON)
		.when()
			.get("userdetails")
		.then()
			.log().all()
			.statusCode(200)
			.time(Matchers.lessThan(3000L));
			
	}
}
