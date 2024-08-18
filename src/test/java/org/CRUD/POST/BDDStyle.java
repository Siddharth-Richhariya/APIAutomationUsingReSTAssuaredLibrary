package org.CRUD.POST;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;



public class BDDStyle {
    String BaseURLs = "https://restful-booker.herokuapp.com";
    String BasePath = "/booking";
    String Payload = "{\n" +
            "    \"firstname\" : \"Sid\",\n" +
            "    \"lastname\" : \"Doe\",\n" +
            "    \"totalprice\" : 123,\n" +
            "    \"depositpaid\" : true,\n" +
            "    \"bookingdates\" : {\n" +
            "        \"checkin\" : \"2024-01-01\",\n" +
            "        \"checkout\" : \"2024-01-01\"\n" +
            "    },\n" +
            "    \"additionalneeds\" : \"Breakfast, lunch, dinner\"\n" +
            "}";
    @Test
    public void postRequest(){
            RestAssured.given()
                    .baseUri(BaseURLs).basePath(BasePath)
                    .contentType(ContentType.JSON).log().all().body(Payload)
                    .when().post()
                    .then().log().all().statusCode(200);

    }
}
