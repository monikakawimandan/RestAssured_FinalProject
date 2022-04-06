package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.io.File;
import java.util.HashMap;

/*{
    "name": "Samsung phone",
    "description": "great phone.",
    "price": "1299",
    "category_id": "2",
    "category_name": "Electronics"
},*/

public class CreateAProduct {
	
	@Test
	public void createAProduct() {
		
		
		HashMap<String, String> payload = new HashMap<String ,String>();
		payload.put("name", "Samsung phone");
		payload.put("price", "1299");
		payload.put("description", "great phone.");
		payload.put( "category_id",  "2");
		payload.put("category_name" , "Electronics");
		
		
		
		Response response = 
		
	given()
		.baseUri("http://techfios.com/api-prod/api/product/create.php")
		.header("Content-Type", "application/json; charset=UTF-8")
		.body(new File("src\\main\\java\\data\\createPayload.json"))
	//	.auth().preemptive().basic("username", "password")
		.body(payload).	
	when()
		.post("/create.php").
	then()
		.extract().response();
		
		int responseCode = response.getStatusCode();
		System.out.println("responseCode:" + responseCode );
		Assert.assertEquals(responseCode, 201);
		
		String actualHeader = response.getHeader("Content-Type");
		System.out.println("actualHeader:" + actualHeader);
		Assert.assertEquals(actualHeader , "\"application/json; charset=UTF-8\"");
		
		String responseBody = response.getBody().asString();
		System.out.println("responseBody:" + responseBody);
		
		JsonPath jp = new JsonPath(responseBody);
		
		String productMessage = jp.get("message");
		System.out.println("productMessahe:" + productMessage);
		Assert.assertEquals(productMessage, "Product was created");
		
		
		
		
		
		
	}

}
