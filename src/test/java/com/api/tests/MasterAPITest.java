package com.api.tests;
import static io.restassured.RestAssured.*;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.constants.Role;
import com.api.utils.AuthTokenProvider;
import com.api.utils.ConfigManager;

import io.restassured.module.jsv.JsonSchemaValidator;

//import static io.restassured.module.jsv.JsonSchemaValidator.*;

public class MasterAPITest {

	@Test
	public void masterAPITest() {
		
		given()
			.baseUri(ConfigManager.getProperty("BASE_URI"))
			.and()
			.headers("Authorization",AuthTokenProvider.getToken(Role.FD))
			.and()
			.contentType("")
			.log().all()
		.when()
			.post("/master")
		.then()
			.log().all()
			.statusCode(200)
			.time(Matchers.lessThan(1000L))
			.body("message",Matchers.equalTo("Success"))
			.body("data",Matchers.notNullValue())
			.body("data",Matchers.hasKey("mst_oem"))
			.body("data", Matchers.hasKey("mst_model"))
			.body("$",Matchers.hasKey("message"))
			.body("$", Matchers.hasKey("data"))
			.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("Response-Schema/MasterAPIResponseSchema-FD.json"));
			
	}
}
