package org.CRUD.POST;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;


public class NonBDD {
    RequestSpecification r = RestAssured.given();
    Response response;
    ValidatableResponse validatableResponse;

    @Test
    public void postRequest(){
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
        r.baseUri(BaseURLs);
        r.basePath(BasePath).log().all();
        r.body(Payload);
        r.contentType(ContentType.JSON);

        response = r.when().log().all().post();
        String responseString = response.asString();
        System.out.println(responseString);

        validatableResponse = response.then();
        validatableResponse.statusCode(200);

    }
    @Test
    public void postRequestNegative(){
        String BaseURLs = "https://restful-booker.herokuapp.com";
        String BasePath = "/booking";
        String Payload = "";
        r.baseUri(BaseURLs);
        r.basePath(BasePath).log().all();
        r.body(Payload);
        r.contentType(ContentType.JSON);

        response = r.when().log().all().post();
        String responseString = response.asString();
        System.out.println(responseString);

        validatableResponse = response.then();
        validatableResponse.statusCode(500);

    }
}
