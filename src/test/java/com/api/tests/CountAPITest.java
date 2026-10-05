package com.api.tests;

import static org.hamcrest.Matchers.*;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.utils.SpecUtil;

import static com.api.constants.Role.*;
import static com.api.utils.AuthTokenProvider.*;
import static com.api.utils.ConfigManager.*;

import static io.restassured.module.jsv.JsonSchemaValidator.*;

import static io.restassured.RestAssured.*;

public class CountAPITest {

	@Test
	public void verifyCountAPIResponse()
	{
		given()
			.spec(SpecUtil.requestSpecWithAuth(FD))
		.when()
			.get("/dashboard/count")
		.then()
			.spec(SpecUtil.responseSpec_OK())
			.body("message",equalTo("Success"))
			.body("data",Matchers.notNullValue())
			.body("data.size()",Matchers.equalTo(3))
			.body("data.count",Matchers.everyItem(Matchers.greaterThanOrEqualTo(0)))
			.body("data.label",Matchers.everyItem(Matchers.not(Matchers.blankOrNullString())))
			.body("data.key",Matchers.containsInAnyOrder("pending_for_delivery","created_today","pending_fst_assignment"))
			.body(matchesJsonSchemaInClasspath("Response-Schema/CountAPIResponseSchema-FD.json"));
	}	
	
	@Test
	public void countAPI_MissingAuthToken() {
		
		given()
		.spec(SpecUtil.requestSpec())
	.when()
		.get("/dashboard/count")
	.then()
		.spec(SpecUtil.responseSpec_TEXT(401));
	
	}
}