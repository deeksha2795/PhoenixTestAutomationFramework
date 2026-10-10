package com.api.utils;

import org.hamcrest.Matchers;

import com.api.constants.Role;
import com.api.requestmodel.UserCredentials;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecUtil {
//static methods!!
	
	//GET-DEL
	public static RequestSpecification requestSpec()
	{
		RequestSpecification request = new RequestSpecBuilder()
		.setBaseUri(ConfigManager.getProperty("BASE_URI"))
		.setContentType(ContentType.JSON)
		.setAccept(ContentType.JSON)
		.log(LogDetail.URI)
		.log(LogDetail.METHOD)
		.log(LogDetail.HEADERS)
		.log(LogDetail.BODY)
		.build();
		return request;
	}
	
	//PUT-POST-PATCH : when we need to pass body in any request
	public static RequestSpecification requestSpec(Object payload)
	{
		RequestSpecification request = new RequestSpecBuilder()
		.setBaseUri(ConfigManager.getProperty("BASE_URI"))
		.setContentType(ContentType.JSON)
		.setAccept(ContentType.JSON)
		.setBody(payload)
		.log(LogDetail.URI)
		.log(LogDetail.METHOD)
		.log(LogDetail.HEADERS)
		.log(LogDetail.BODY)
		.build();
		return request;
	}
	
	//RequestSpecification with Auth
	public static RequestSpecification requestSpecWithAuth(Role role)
	{
		RequestSpecification requestSpecification = new RequestSpecBuilder()
		.setBaseUri(ConfigManager.getProperty("BASE_URI"))
		.setContentType(ContentType.JSON)
		.setAccept(ContentType.JSON)
		.addHeader("Authorization", AuthTokenProvider.getToken(role))
		.log(LogDetail.URI)
		.log(LogDetail.METHOD)
		.log(LogDetail.HEADERS)
		.log(LogDetail.BODY)
		.build();
		return requestSpecification;
	}
	
	//RequestSpecification with Auth and role
		public static RequestSpecification requestSpecWithAuth(Role role,Object payload)
		{
			RequestSpecification requestSpecification = new RequestSpecBuilder()
			.setBaseUri(ConfigManager.getProperty("BASE_URI"))
			.setContentType(ContentType.JSON)
			.setAccept(ContentType.JSON)
			.addHeader("Authorization", AuthTokenProvider.getToken(role))
			.setBody(payload)
			.log(LogDetail.URI)
			.log(LogDetail.METHOD)
			.log(LogDetail.HEADERS)
			.log(LogDetail.BODY)
			.build();
			return requestSpecification;
		}
	
	
	
	//Response specification spec builder
	public static ResponseSpecification responseSpec_OK()
	{
		ResponseSpecification responseSpecification =new ResponseSpecBuilder()
				.expectContentType(ContentType.JSON)
				.expectStatusCode(200)
				.expectResponseTime(Matchers.lessThan(5000L))
				.log(LogDetail.ALL)
				.build();
		return responseSpecification;
	}
	
	public static ResponseSpecification responseSpec_JSON(int statuscode)
	{
		ResponseSpecification responseSpecification =new ResponseSpecBuilder()
				.expectContentType(ContentType.JSON)
				.expectStatusCode(statuscode)
				.expectResponseTime(Matchers.lessThan(5000L))
				.log(LogDetail.ALL)
				.build();
		return responseSpecification;
	}
	
	
	public static ResponseSpecification responseSpec_TEXT(int statuscode)
	{
		ResponseSpecification responseSpecification =new ResponseSpecBuilder()
				.expectStatusCode(statuscode)
				.expectResponseTime(Matchers.lessThan(5000L))
				.log(LogDetail.ALL)
				.build();
		return responseSpecification;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
