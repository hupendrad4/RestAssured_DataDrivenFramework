package com.restapi_TestCases;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.restapi_baseclass.TestBase;
import com.restapi_utilities.RestUtils;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC003_Post_New_Employee extends TestBase {

	public static RequestSpecification httprequest;
	Response response;
	String eName = RestUtils.empName();
	String eSalary = RestUtils.empSalary();
	String eAge = RestUtils.empAge();

	@BeforeClass
	void postNewEmployee() throws InterruptedException {
		logger.info("****************** Starting TC003_Post_New_Employee  *************************");
		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";

		httprequest = RestAssured.given();

		JSONObject RequestParam = new JSONObject();
		RequestParam.put("name", eName);
		RequestParam.put("salary", eSalary);
		RequestParam.put("age", eAge);

		// add header stating the request body is json type
		httprequest.header("Content-Type", "application/json");
		httprequest.body(RequestParam.toJSONString());

		response = httprequest.request(Method.POST, "/create");

		Thread.sleep(3000);
	}

	@Test
	void postRequestValidation() {
		logger.info("****************** The Response Body *************************");
		String responseBody = response.getBody().asString();
		System.out.println("The Response Body of Post Request is " + responseBody);

		logger.info("The Response body Validation is ");
		Assert.assertEquals(responseBody.contains(eName), true);
		Assert.assertEquals(responseBody.contains(eSalary), true);
		Assert.assertEquals(responseBody.contains(eAge), true);
	}

	@Test
	void checkStatusCode() {
		logger.info("****************** The Check Status  *************************");
		int statusCode = response.getStatusCode();
		System.out.println("The Status Code is ==>" + statusCode);
		logger.info("The Status Code is ==>" + statusCode);
		Assert.assertEquals(statusCode, 200);
	}

	@Test
	void checkStatusLine() {
		logger.info("****************** Check Status Line  *************************");
		String statusLine = response.statusLine();
		System.out.println("The Status Line is ==>" + statusLine);
		logger.info("The Status Line is ==>" + statusLine);
	}

	@Test
	void checkContentType() {
		logger.info("****************** Check Content-Type *************************");
		String contentType = response.header("Content-Type");
		Assert.assertEquals(contentType, "application/json;charset=utf-8");
		System.out.println("The Content Type is ==>" + contentType);
		logger.info("The Content Type is ==>" + contentType);
	}

	@Test
	void checkServerType() {
		logger.info("****************** Check Content-Server Type *************************");
		String contentServerType = response.header("Server");
		Assert.assertEquals(contentServerType, "nginx/1.16.0");
		System.out.println("The Content Server Type is ==>" + contentServerType);
		logger.info("The Content Server Type is ==>" + contentServerType);
	}

	@Test
	void checkContentEncodingType() {
		logger.info("****************** check Content Encoding Typee *************************");
		String contentEncodingType = response.header("Content-Encoding");
		Assert.assertEquals(contentEncodingType, "gzip");
		System.out.println("The Content Encoding Type is ==>" + contentEncodingType);
		logger.info("The Content Encoding Type is ==>" + contentEncodingType);
	}

	@AfterClass
	void tearDown() {
		logger.info("****************** Finish - TC003_Post_New_Employee  *************************");
	}
}
