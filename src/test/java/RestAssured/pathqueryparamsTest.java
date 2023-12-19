package RestAssured;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

public class pathqueryparamsTest {
	
	@Test
	void testQueryAndPathParams() {
		
		given()
			.pathParam("urlPath", "users")   //path parameters
			.queryParam("page", 2)  // query parameters
			.queryParam("id", 5)  
		
		.when()
			.get("https://reqres.in/api/{urlPath}")
		
		.then()
			.statusCode(200)
			.log().all();
		
		
		
	}
	

}
