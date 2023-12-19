package RestAssured;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.util.List;

import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class parseXMLResponse {
	
	
	@Test
	void parseXMLResponseTest() {
		
		// XML response is access through XPath 
		
				/* <courses>
				 * 	<subject>
				 * 		<name>Rest Assured</name>
				 * 		<price>10</price>
				 * 		<version>1.0</version>
				 * 	</subject>
				 * <subject>
				 * 		<name>Postman</name>
				 * 		<price>17</price>
				 * 		<version>1.0</version>
				 * 	</subject>
				 *  <subject>
				 * 		<name>Selenium</name>
				 * 		<price>3</price>
				 * 		<version>1.0</version>
				 * 	</subject>
				 * 	<subject>
				 * 		<name>cucumber</name>
				 * 		<price>10</price>
				 * 		<version>1.0</version>
				 * 	</subject>
				 * </courses>
				 * 
				 */
		
		// Method - 1 
		given()
		.when()
			.get("https://restapi.adequateshop.com/api/Traveler?page=1")
		.then()
			.body("courses.subject[0].name", equalTo("Rest Assured"))
			.body("courses.subject.name",Matchers.hasItems("Rest Assured", "Postman"));

		
		// Method - 2
		Response res = given()
						.when()
							.get("https://restapi.adequateshop.com/api/Traveler?page=1");
		
	
		Assert.assertEquals(res.header("Content-Type"), "application/xml");
		res.xmlPath().get("courses.subject[0].name").toString();
		
		// Method - 3
		XmlPath  xp = new XmlPath(res.asString());
		
		List<String> subjects = xp.getList("course.subject");  // will get all the subject nodes
		
		Assert.assertEquals(subjects.size(), 4);   // total number of subjects 
		
		List<String> subjectNames = xp.getList("course.subject.name");
		
		boolean status = false;
		
		for(String s : subjectNames) {
			System.out.println(s);
			if(s.equals("Selenium")) {
				status = true;
				break;
			}
		}
		
		Assert.assertEquals(status, true);
	
		
	
	}
	
	
	@Test
	void fileUpload() {
		
		File f = new File("path/of/file.txt");
		
		given()
			.multiPart("file",f)    // if multiple files the ("files",f) for every file to upload
			.contentType("multipart/form-data")

		.when()
			.post("")
		
		.then()
			.statusCode(201)
			.body("filename", equalTo(""));
		
		
		
	}
	
	@Test
	void fileDownload() {
		
		given()
		
		.when()
			.get("url for download")
		.then()
			.statusCode(200);
		
		
		
	}

}
