package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.io.File;


public class DeleteAProduct {
	
	
	@Test
	public void deleteAproduct() {
		
		Response response = 
		
	given()
		.baseUri("http://techfios.com/api-prod/api/product/delete.php")
		.header("Content-Type", "application/json; charset=UTF-8")
		.body(new File("C:\\Users\\monik\\eclipse-workspace\\Rest_Assured_Project\\RestAssured_FinalProject\\src\\main\\java\\data\\deletePayload.json"))
		.headers("Authorization" , "Bearer nnknknknj").
	when()
		.delete("/delete.php").
	then()
	    .extract().response();
	
		
	int actualResponseCode = response.getStatusCode();
	System.out.println("actualResponseCode:" + actualResponseCode);
	Assert.assertEquals(actualResponseCode, 200);
	
	String actualHeader = response.getHeader("Content-Type");
	System.out.println("actualHeader:" + actualHeader);
	Assert.assertEquals(actualHeader, "application/json; charset=UTF-8" );
	
	String actualReponseBody = response.getBody().asString();
	System.out.println("actualReponseBody:" + actualReponseBody );
	
	
	JsonPath jp = new JsonPath("message");
	
	String actualResponseMessage = jp.getString("message");
	System.out.println("actualResponseMessage:" + actualResponseMessage );
	Assert.assertEquals(actualResponseMessage, "Product was deleted.");
			
		
	}

}
