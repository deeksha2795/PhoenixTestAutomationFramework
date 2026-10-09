package com.api.tests;

import org.hamcrest.Matchers;
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
import io.restassured.module.jsv.JsonSchemaValidator;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

public class CreateJobAPITest {

		
	
	@Test
	public void createJobAPITest()
	{
		
		
		Customer customer = new Customer("Deeksha", "Bajad", "9893325433", "", "deeksha.shirke27@gmail.com", "");
		CustomerAddress customerAddress  = new CustomerAddress("401", "Galaxy", "Stree-4", "Near Main Square", "Omkar Nagar", "440024", "India", "Maharashtra");
		CustomerProduct customerProduct = new CustomerProduct("2025-04-30T18:30:00.000Z", "112989649322923", "112989649322923", "112989649322923", "2025-04-30T18:30:00.000Z", 1, 1);
		Problems problems = new Problems("2", "Battery drains too quickly");
		List<Problems> problemList = new ArrayList();
		problemList.add(problems);
		CreateJobPayload createJobPayload = new CreateJobPayload(0, 2, 1, 1, customer, customerAddress, customerProduct, problemList);
		
		given()
			.spec(SpecUtil.requestSpecWithAuth(Role.FD, createJobPayload))
			.when()
			.post("/job/create")
			.then()
			.spec(SpecUtil.responseSpec_OK())
			.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("Response-Schema/CreateJobAPIResponseSchema.json"))
			.body("message",Matchers.equalTo("Job created successfully. "))
			.body("data.mst_service_location_id",Matchers.equalTo(1))
			.body("data.job_number",Matchers.startsWith("JOB_"));
			
	}
}
