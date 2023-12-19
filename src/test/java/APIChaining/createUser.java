package APIChaining;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.restassured.response.Response;

public class createUser {
	
	@Test
	void test_CreateUser(ITestContext context) {
		
		
		Faker faker = new Faker();
		
		JSONObject data = new JSONObject();
		
		data.put("name", faker.name().firstName());
		data.put("job", faker.job());
		
		int id = given()
			.contentType("application/json")
			.body(data.toString())
		
		.when()
			.post("https://reqres.in/api/users")
			.jsonPath().getInt("id");
		
		
		System.out.println("Generate id is :"+id);
		
		context.setAttribute("user_id", id);  // this is available throughout test in XML
		
		//context.getSuite().setAttribute("user_id", data); // this is available at suite-level
		
		
		
	}

}
