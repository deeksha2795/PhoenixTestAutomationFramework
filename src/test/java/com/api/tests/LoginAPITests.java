package com.api.tests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;
import java.io.IOException;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.api.requestmodel.UserCredentials;
import com.api.utils.ConfigManager;
import static com.api.utils.SpecUtil.*;
import static com.api.utils.ConfigManagerOLD.*;
import io.restassured.http.ContentType;

public class LoginAPITests {

	private UserCredentials usercredentials;
	
	@BeforeMethod(description = "Create the payload for LoginAPI")
	public void setup()
	{
		usercredentials = new UserCredentials("iamfd", "password");
	}
	
	@Test(description = "Verifying if the Login API is working for FD User", groups = { "api", "regression", "smoke" })
	public void loginAPITest() throws IOException {
		given()
				.spec(requestSpec(usercredentials))// here .spec applies the pre-built RequestSpecification that
															// bundles up reusable settings so that we dont have to call
															// them again and again every test
				.when().post("login")
				.then().spec(responseSpec_OK())
				.body("message", equalTo("Success"));

	}
}
