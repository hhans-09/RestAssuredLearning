package APIChaining;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

public class UpdateUser {
	
	
	@Test
	void test_updateUser(ITestContext context) {
		
Faker faker = new Faker();
		
		JSONObject data = new JSONObject();
		
		data.put("name", faker.name().firstName());
		data.put("job", faker.job());
		
		int id = (int) context.getAttribute("user_id");
		
		//int id = (int) context.getSuite().getAttribute("user_id"); // when suite level variable
		
		 given()
			.contentType("application/json")
			.body(data.toString())
			.pathParam("id", id)
		
		.when()
			.put("https://reqres.in/api/users/{id}")
		.then()
			.statusCode(200)
			.log().all();
		
		
		
	}

}
