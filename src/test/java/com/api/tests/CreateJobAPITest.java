package com.api.tests;

import org.testng.annotations.Test;

import com.api.constants.Role;
import com.api.pojo.CreateJobPayload;
import com.api.pojo.Customer;
import com.api.pojo.CustomerAddress;
import com.api.pojo.CustomerProduct;
import com.api.pojo.Problems;
import com.api.utils.AuthTokenProvider;
import com.api.utils.ConfigManager;
import com.api.utils.SpecUtil;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;

public class CreateJobAPITest {

		
	
	@Test
	public void createJobAPITest()
	{
		
		
		Customer customer = new Customer("Deeksha", "Bajad", "9893325433", "", "deeksha.shirke27@gmail.com", "");
		CustomerAddress customerAddress  = new CustomerAddress("401", "Galaxy", "Stree-4", "Near Main Square", "Omkar Nagar", "440024", "India", "Maharashtra");
		CustomerProduct customerProduct = new CustomerProduct("2025-04-30T18:30:00.000Z", "108989649322923", "108989649322923", "108989649322923", "2025-04-30T18:30:00.000Z", 1, 1);
		Problems problems = new Problems("2", "Battery drains too quickly");
		Problems[] problemArray = new Problems[1];
		problemArray[0]=problems;
		CreateJobPayload createJobPayload = new CreateJobPayload(0, 2, 1, 1, customer, customerAddress, customerProduct, problemArray);
		
		given()
			.spec(SpecUtil.requestSpecWithAuth(Role.FD, createJobPayload))
			.when()
			.post("/job/create")
			.then()
			.spec(SpecUtil.responseSpec_OK());
			
	}
}
