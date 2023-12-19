package RestAssured;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class Authentications {
	
	@Test(priority=1)
	void basicAuthTest() {
		
		given()
			.auth().basic("postman","password")
		
		.when()
			.get("https://postman-echo.com/basic-auth")
		
		.then()
			.statusCode(200)
			.body("authenticated",equalTo(true))
			.log().all();
		
	}
	
	@Test(priority=2)
	void digestAuthTest() {
		
		given()
			.auth().digest("postman","password")
		
		.when()
			.get("https://postman-echo.com/basic-auth")
		
		.then()
			.statusCode(200)
			.body("authenticated",equalTo(true))
			.log().all();
		
	}
	
	@Test(priority=3)
	void preemptiveAuthTest() {
		
		given()
			.auth().preemptive().basic("postman", "password")
		
		.when()
			.get("https://postman-echo.com/basic-auth")
		
		.then()
			.statusCode(200)
			.body("authenticated",equalTo(true))
			.log().all();
		
	}
	
	@Test(priority=4)
	void bearerTokenAuthTest() {
		
		String bToken = "Bearer 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";
		
		given()
			.headers("Authorization",bToken)
		
		.when()
			.get("https://api.github.com/user/repos")
		
		.then()
			.statusCode(200)
			.log().all();
		
	}
	
	//@Test(priority=5)
	void oAuthAuthTest() {
		
//		given()    // OAuth 1.0 
//			.auth().oauth("consumerKey", "consumerSecret", "accessToken","tokensecrets")
		
		given()
			.auth().oauth2("token")
		
		.when()
			.get("https://api.github.com/user/repos")
		
		.then()
			.statusCode(200)
			.log().all();
		
	}
	
	@Test(priority=6)
	void apiKeyAuthTest() {
		
		given()
			.queryParam("appid", "value")  // appid is API Key
			
		
		.when()
			.get("https://api.github.com/user/repos")
		
		.then()
			.statusCode(200)
			.log().all();
		
	}



}
