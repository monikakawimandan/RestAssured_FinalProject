package testCases;



import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Read_One_Product {
	
	@Test
	public void readOneProduct() {
		
		
		Response response = 
				
		given()
		      .baseUri("https://techfios.com/api-prod/api/product")
		      .headers("Content-Type", "application/json")
		      .queryParam("id", "2941")
		      .auth().preemptive().basic("userName", "password") //2 Type of authorization - Basic and through Header - This is the way for basic auth
		      .header("Authorization", "Bearer gjgkjgkjgkjgkjgkjgkj"). // This is the way for doing authorization by using the HEADER.
		when()
		      .get("/read_one.php").
					
	    then()
	          .extract().response();
		
		
		int responseStatusCode = response.getStatusCode();
		System.out.println("responseStatusCode:" + responseStatusCode);
		Assert.assertEquals(responseStatusCode, 200);
		
		String actualHeader = response.getHeader("Content-Type");
		System.out.println("actualHeader:" + actualHeader);
		Assert.assertEquals(actualHeader, "application/json");
		
		String actualResponseBody = response.getBody().asString();
		System.out.println("actualResponseBody:" + actualResponseBody);
		
		JsonPath jp = new JsonPath(actualResponseBody);
		
		
		System.out.println("jp:" + jp.prettyPrint());
		
		String productID = jp.get("id");
		System.out.println("productID:" + productID);
		Assert.assertEquals( productID, "2941");
		
		String productPrice = jp.get("price");
		System.out.println("productPrice:" + productPrice);
		Assert.assertEquals( productPrice, "199");
		
		
		
		
		
		
		
		
		
		
		

}
}
