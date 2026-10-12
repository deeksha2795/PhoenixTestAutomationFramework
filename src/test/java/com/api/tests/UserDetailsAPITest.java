package com.api.tests;
import static io.restassured.RestAssured.*;
import java.io.IOException;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;
import static com.api.utils.AuthTokenProvider.*;
import static com.api.constants.Role.*;
import com.api.utils.ConfigManager;
import static com.api.utils.SpecUtil.*;
import static com.api.utils.ConfigManagerOLD.*;
import io.restassured.http.ContentType;
import io.restassured.http.Header;

public class UserDetailsAPITest {

	//ConfigManager configManager = new ConfigManager();
	@Test(description = "Verify if the UserDetails API response is shown correctly", groups = {"api","regression","smoke"})
	public void userDetailsAPITest() throws IOException
	{
		given()
			.spec(requestSpecWithAuth(FD))
		.when()
			.get("userdetails")
		.then()
			.spec(responseSpec_OK());
			
	}
}
