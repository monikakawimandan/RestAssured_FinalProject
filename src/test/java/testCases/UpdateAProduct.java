package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.io.File;

public class UpdateAProduct {
	
	SoftAssert softAssert = new SoftAssert();
	
	
	
	@Test(priority=1)
	public void updateAProduct() {
		
		Response response = 
		
	given()
		.baseUri("http://techfios.com/api-prod/api/product/update.php")
		.header("Content-Type", "application/json; charset=UTF-8")
		.body(new File("src\\main\\java\\data\\updatePayload.json"))
		.headers("Authorization", "Bearer vjbjbjbjbjkhbjhb")
		.queryParams("id" , "2962").
	when()
	    .put("/update.php").
    then()
	    .extract().response();
		
	int actualStatusCode = response.getStatusCode();
	System.out.println("actualStatusCode:" + actualStatusCode);
	//Assert.assertEquals(actualStatusCode, 200);
	softAssert.assertEquals(actualStatusCode, 201, "Status Code are not matching for the update product!");
	
	String actualHeader = response.getHeader("Content-Type");
	System.out.println("actualHeader:" + actualHeader );
	//Assert.assertEquals(actualHeader, "application/json; charset=UTF-8");
	softAssert.assertEquals(actualHeader, "application/json; charset=UTF-8" , "Headers are not matching");
	
	String responseBody = response.getBody().asString();
	System.out.println("responseBody:" + responseBody);
	
	
	JsonPath jp = new JsonPath("message");
	
	String actualResponseMessage = jp.getString("message");
	System.out.println("actualResponseMessage:" + actualResponseMessage );
	//Assert.assertEquals(actualResponseMessage, "Product was updated.");	
	softAssert.assertEquals( actualResponseMessage,"Product was updated." , "ProductMessage is not updated");	
	
	softAssert.assertAll();
	
	
	
	}

	
	public void readOneProduct() {
		
		
		Response response = 
				
		given()
		      .baseUri("https://techfios.com/api-prod/api/product")
		      .headers("Content-Type", "application/json")
		      .queryParam("id", "2962")
		      //.auth().preemptive().basic("userName", "password") //2 Type of authorization - Basic and through Header - This is the way for basic auth
		      .header("Authorization", "Bearer gjgkjgkjgkjgkjgkjgkj"). 
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
		Assert.assertEquals( productPrice, "1499");
		
		
		
		
		
		
		
		
		
		
		

}
	
	
	
	
	
}
