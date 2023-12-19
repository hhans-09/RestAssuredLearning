package RestAssured;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;


public class parseJSONResponse {


		
		@Test
		void Test() {
			
			Response res = given()
								.contentType(ContentType.JSON)
							.when()
								.get("");
			
			
			// ways to validate response
			
			Assert.assertEquals(res.statusCode(), 200);
			 
			// get particular value 
			res.jsonPath().get("book[3].title").toString();
			
			
			// 2nd way
			
			JSONObject jo = new JSONObject(res.toString());
			
			String bookTitle = jo.getJSONArray("book").getJSONObject(1).get("title").toString();
			
			
			
		}

	}


