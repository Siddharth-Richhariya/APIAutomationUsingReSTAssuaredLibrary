package org.CRUD.PUT;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
public class UpdateDetails {
    String baseURL = "https://restful-booker.herokuapp.com";
    String basePath = "/booking";
    String token = "822428ac37411a4"; // for upcoming Operation need to change Token and Booking ID
    String bookingID = "1646";

    RequestSpecification r = RestAssured.given();
    ;
    Response response;
    ValidatableResponse vr;

    @BeforeClass
    public void setup(){
        r.baseUri(baseURL);
        r.contentType(ContentType.JSON);
        r.cookie("token", token);
    }

    @Test
    public void updateFullDetails() {
        String updatedBasePath = basePath + "/" + bookingID;
        String payload = "{\n" +
                "    \"firstname\" : \"James\",\n" +
                "    \"lastname\" : \"Brown\",\n" +
                "    \"totalprice\" : 123,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2023-01-01\",\n" +
                "        \"checkout\" : \"2024-01-01\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Breakfast,lunch\"\n" +
                "}";

        r.basePath(updatedBasePath);
        r.body(payload).log().all();

        response = r.when().log().all().put();

        vr = response.then().log().all();
        vr.statusCode(200);
        vr.body("firstname", Matchers.equalTo("James"));
        vr.body("lastname", Matchers.equalTo("Brown"));
    }
}
