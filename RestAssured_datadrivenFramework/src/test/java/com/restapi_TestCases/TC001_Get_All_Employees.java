package com.restapi_TestCases;

import static org.testng.Assert.assertEquals;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.restapi_baseclass.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;

public class TC001_Get_All_Employees extends TestBase {

	@BeforeClass
	void getAllEmployeeDetails() {
		logger.info("************ Started TC001_Get_All_Employees Test Case Started ********************");

		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		httprequest = RestAssured.given();
		response = httprequest.request(Method.GET, "/employees");

	}

	@Test
	void checkResponseBody() {

		logger.info("*************Checking Response Body of Request ********************");
		String responseBody = response.getBody().asString();
		System.out.println("The Response Body is " + responseBody);
		Assert.assertTrue(responseBody != null);

		logger.info("The Response Body ==>" + responseBody);

	}

	@Test

	void checkStatusCode() {
		logger.info("************Checking Status Code ********************");
		int statusCode = response.getStatusCode();
		// System.out.println("The Status Code is "+statusCode);
		logger.info("The Status Code is ==>" + statusCode);
		Assert.assertEquals(statusCode, 200);

		// logger.info(message);
	}

	@Test
	void checkReponseTime() {
		logger.info("************Checking Response Time ********************");
		long responseTime = response.getTime();
		// System.out.println("The Response Time is "+responseTime);
		logger.info(" The Response Time ==>" + responseTime);

		if (responseTime > 2000);
			logger.warn("The Response Time is more than 2000");
		Assert.assertTrue(responseTime < 2000);

	}

	@Test
	void checkStatusLine() {
		logger.info("************Checking Status Line ********************");
		String statusLine = response.getStatusLine();
		// System.out.println("The Status Line is "+statusLine);
		logger.info("The Status Line is ==>" + statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");

	}

	@Test
	void checkTheContentType() {
		logger.info("************Checking Content Type ********************");
		String contentType = response.header("Content-Type");
		// System.out.println("The Content Type is " + contentType);
		logger.info("The Content Type is ==>" + contentType);
		Assert.assertEquals(contentType, "application/json;charset=utf-8");

	}

	@Test
	void checkServerType() {
		logger.info("************Checking  Server Type  ********************");
		String serverType = response.header("Server");
		// System.out.println("The Server-Type is " + serverType);
		logger.info("The Server Type is==>" + serverType);
		Assert.assertEquals(serverType, "nginx/1.16.0");

	}

	@Test
	void checkContentEncodeType() {
		logger.info("************Checking  Content Encoding Type  ********************");
		String contentEncodeType = response.header("Content-Encoding");
		// System.out.println("The Content Encoding Type is " + contentEncodeType);
		logger.info("The Content Encoding Type is " + contentEncodeType);
		Assert.assertEquals(contentEncodeType, "gzip");

	}

	@Test
	void checkContentLength() {
		logger.info("************Checking  Content Length  ********************");
		String contentLength = response.header("Content-Length");
		// System.out.println("The Content Length is " + contentLength);
		logger.info("The Content Encoding Type is " + contentLength);

		int Length = contentLength.length();
		if (Length < 100)
			logger.warn("The Content Lenght is less than 100");
		System.out.println("The Content Leght is " + Length);
		Assert.assertEquals(contentLength, "595");

	}

	@Test 
	void checkCookies() {
		
		String Cookiedetails =response.getCookie("PHPSESSID");
		System.out.println("The Cookie is " +Cookiedetails);
		
	}
	
	
	@AfterClass
	void tearDown() {
		logger.info("************ Finish TC001_Get_All_Employees Test Case Started ********************");	
		
	}
}
