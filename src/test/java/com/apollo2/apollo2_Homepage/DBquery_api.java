package com.apollo2.apollo2_Homepage;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class DBquery_api {
	
	@Test(priority=1)
	public void Dbquery() {
	    String graphqlQuery = "{human fetus brain age greater than 20 weeks}";

	    Response response = RestAssured
	            .given()
	            .auth()
	            .preemptive()
	            .basic("admin", "admin")
	            .contentType(ContentType.JSON)
	            .body("{\"query\": \"" + graphqlQuery + "\"}")
	            .when()
	            .post("https://apollo2.humanbrain.in/analytics/dbBrainQuery");

	    int statusCode = response.getStatusCode();

	    if (statusCode == 200) {
	        System.out.println("API request to Database query passed. Status code: " + statusCode);
	        String query="human fetus brain age greater than 20 weeks";
	        System.out.println("Query : "+query);
	        System.out.println("Query Result:");
	        System.out.println(response.getBody().asString());
	    } else {
	        System.out.println("API request to Database query failed. Status code: " + statusCode);
	    }

	    Assert.assertEquals(statusCode, 200, "API request to Database query failed");
	}

}
