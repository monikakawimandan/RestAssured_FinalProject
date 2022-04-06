package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.util.concurrent.TimeUnit;

public class Read_All_Products {

@Test
	public void readAllproduct() {
		
		/*
		given: all input details (base URI, Headers, Payload/Body, Query Parameter )
		 * When: submit api requests(HTTP method, Endpopint/Resource)
		 * Then: Validate responses  (Status code, Headers, Payload/Body. responseTime)
		 * 
		 */
	
		Response response = 
		
		
		
		given()
		.baseUri("https://techfios.com/api-prod/api/product")
		.headers("Content-Type", "application/json; charset=UTF-8").
		
		when()
		.get("/read.php").
					
	    then()
	    .extract().response();
		
		long actualResponseTime = response.getTimeIn(TimeUnit.MILLISECONDS);
		System.out.println("actualResponseTime:" + actualResponseTime);
		if(actualResponseTime<2000) {
			System.out.println("actualResponseTime is within range");		
		}else {
			System.out.println("actualResponseTime is out of range");	
		}
	  
		int actualstatusCode = response.getStatusCode();
		System.out.println("actualStatusCode:" + actualstatusCode);
		Assert.assertEquals(actualstatusCode, 200);
				
		String actualheader = response.getHeader("Content-Type");
		Assert.assertEquals(actualheader, "application/json; charset=UTF-8" );
		
	}

}
