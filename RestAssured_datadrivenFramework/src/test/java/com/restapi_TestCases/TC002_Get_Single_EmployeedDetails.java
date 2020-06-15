package com.restapi_TestCases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.restapi_baseclass.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC002_Get_Single_EmployeedDetails extends TestBase {

	public static RequestSpecification httprequest;
	Response response;

	@BeforeClass
	void getEmplpoyeeData() throws InterruptedException {
		logger.info("************ Started TC002_Get_Single_EmployeedDetails ********************");

		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		httprequest = RestAssured.given();

		response = httprequest.request(Method.GET,"/employee/"+empID);

		//Thread.sleep(3000);

	}

	@Test
	void checkResponseBody() {

		logger.info("*************Checking Response Body of Request ********************");
		String responseBody = response.getBody().asString();
		System.out.println("The Response Body is " + responseBody);
		//Assert.assertTrue(responseBody != null);
		Assert.assertEquals(responseBody.contains(empID),true);

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

		if (responseTime > 3000)
			;
		logger.warn("The Response Time is more than 3000");
		Assert.assertTrue(responseTime < 3000);

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
	void checkCookies() {

		String Cookiedetails = response.getCookie("PHPSESSID");
		System.out.println("The Cookie is " + Cookiedetails);

	}
	
	@Test
	void checkContentLength() {
		logger.info("************Checking  Content Length  ********************");
		String contentLength = response.header("Content-Length");
		// System.out.println("The Content Length is " + contentLength);
		logger.info("The Content Encoding Type is " + contentLength);

		Assert.assertTrue(Integer.parseInt(contentLength)<1500);

	}

	@AfterClass
	void tearDown() {
		logger.info("************ Finish TC002_Get_Single_EmployeedDetails ********************");
	}
}
