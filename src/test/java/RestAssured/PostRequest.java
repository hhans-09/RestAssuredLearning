package RestAssured;


import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

public class PostRequest {
	
	int id;
	
	// Using HashMap
	@Test(priority = 1)
	void createUserusingHM() {
	
	HashMap<String, String> data = new HashMap<>();
	data.put("name", "Joe");
	data.put("job", "Teacher");
	
	
	id = given()
		.contentType("application/json")
		.body(data)
		
	
	.when()
		.post("https://reqres.in/api/users")
		.jsonPath().getInt("id");
	
	
}
	
	// using org.Json library 
	
	@Test(priority = 2)
	void createUserwithorgJson() {
		
		JSONObject data = new JSONObject();
		data.put("name", "Paul");
		data.put("job", "Plumber");
		
		given()
			.contentType("application/json")
			.body(data.toString())
			
		.when()
			.post("https://reqres.in/api/users")
			
		.then()
			.statusCode(201)
			.log().all();
		
		
	}
	
	
	// using POJO class
	@Test(priority = 3)
	void createUserwithPOJO() {
		
		POJO_PsotRequest data = new POJO_PsotRequest();
		
		data.setName("Scott");
		data.setJob("Engineer");
		
		
		
		given()
			.contentType("application/json")
			.body(data)
			
		.when()
			.post("https://reqres.in/api/users")
			
		.then()
			.statusCode(201)
			.log().all();
		
		
	}
	
	
	// using external JSON files
	@Test(priority = 4)
	void createUserusingJSONFile() throws FileNotFoundException {
		
		 File f = new File("./src/test/resources/body.json");
		 FileReader fr = new FileReader(f);
		 JSONTokener jt = new JSONTokener(fr);
		 
		 JSONObject data = new JSONObject(jt);
		 
		
		given()
		.contentType("application/json")
		.body(data.toString())
		
	.when()
		.post("https://reqres.in/api/users")
		
	.then()
		.statusCode(201)
		.log().all();
	}
		

}
