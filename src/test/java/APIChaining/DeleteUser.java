package APIChaining;


import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.ITestContext;
import org.testng.annotations.Test;


public class DeleteUser {
	
	@Test
	void test_DeleteUser(ITestContext context) {
		
		int id = (int) context.getAttribute("user_id");
		
		
		given()
			.pathParam("id", id)
		
		.when()
			.delete("https://reqres.in/api/users/{id}")
		
		.then()
			.statusCode(204)
			.log().all();		
		
	}

}
