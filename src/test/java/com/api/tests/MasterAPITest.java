package com.api.tests;

import static io.restassured.RestAssured.*;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;
import com.api.constants.Role;
import com.api.utils.AuthTokenProvider;
import com.api.utils.ConfigManager;
import com.api.utils.SpecUtil;

import static com.api.utils.SpecUtil.*;
import static io.restassured.module.jsv.JsonSchemaValidator.*;

//import static io.restassured.module.jsv.JsonSchemaValidator.*;

public class MasterAPITest {

	@Test(description = "Verify that the Master API is giving correct response",groups= {"api","regression","smoke"})
	public void masterAPITest() {
		
		given()
			.spec(requestSpecWithAuth(Role.FD))
		.when()
			.post("/master")
		.then()
			.spec(responseSpec_OK())
			.body("message",Matchers.equalTo("Success"))
			.body("data",Matchers.notNullValue())
			.body("data",Matchers.hasKey("mst_oem"))
			.body("data", Matchers.hasKey("mst_model"))
			.body("$",Matchers.hasKey("message"))
			.body("$", Matchers.hasKey("data"))
			.body(matchesJsonSchemaInClasspath("Response-Schema/MasterAPIResponseSchema-FD.json"));
			
	}
	
	@Test(description="Verify that the Master API is giving correct status code for invalid token",groups= {"api","negative","regression","smoke"})
	public void invalidTokenMasterAPITest()
	{
		given()
			.spec(requestSpec())
			.log().all()
		.when()
			.post("master")
		.then()
			.spec(responseSpec_TEXT(401));
	}
}
