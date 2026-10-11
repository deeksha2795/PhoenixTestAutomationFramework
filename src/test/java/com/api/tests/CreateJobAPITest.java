package com.api.tests;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.constants.Model;
import com.api.constants.OEM;
import com.api.constants.Platform;
import com.api.constants.Problem;
import com.api.constants.Product;
import com.api.constants.Role;
import com.api.constants.Service_Location;
import com.api.constants.Warranty_Status;
import com.api.requestmodel.CreateJobPayload;
import com.api.requestmodel.Customer;
import com.api.requestmodel.CustomerAddress;
import com.api.requestmodel.CustomerProduct;
import com.api.requestmodel.Problems;
import com.api.utils.AuthTokenProvider;
import com.api.utils.ConfigManager;
import com.api.utils.DateTimeUtil;
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
		CustomerProduct customerProduct = new CustomerProduct(DateTimeUtil.getTimeWithDaysAgo(10), "112989049322923", "112989049322923", "112989049322923", DateTimeUtil.getTimeWithDaysAgo(10), 
				Product.NEXUS_2.getCode(), Model.NEXUS_2_BLUE.getCode());
		Problems problems = new Problems(Problem.SMARTPHONE_IS_RUNNING_SLOW.getCode(), "Battery drains too quickly");
		List<Problems> problemList = new ArrayList();
		problemList.add(problems);
		CreateJobPayload createJobPayload = new CreateJobPayload(Service_Location.SERVICE_LOCATION_A.getCode(), Platform.FRONT_DESK.getCode(), Warranty_Status.IN_WARRANTY.getCode(), OEM.GOOGLE.getCode(), customer, customerAddress, customerProduct, problemList);
		
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
