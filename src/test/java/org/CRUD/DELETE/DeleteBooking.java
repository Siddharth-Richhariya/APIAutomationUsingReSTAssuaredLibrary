package org.CRUD.DELETE;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

public class DeleteBooking {
    RequestSpecification r = RestAssured.given();
    Response response;
    ValidatableResponse vr;
    String token = "572af1a90fd6225";  // for upcoming Operation need to change Token and Booking ID
    String bookingid = "425";

    String BASE_URL = "https://restful-booker.herokuapp.com";
    String BASE_PATH = "/booking";

    @Test
    public void testPatchREQUESTNonBDD() {
        String BASE_PATH_UPDATED = BASE_PATH + "/" + bookingid;
        System.out.println(BASE_PATH_UPDATED);


        r.baseUri(BASE_URL);
        r.basePath(BASE_PATH_UPDATED);
        r.contentType(ContentType.JSON);
        r.cookie("token", token);
        r.log().all();

        response = r.when().log().all().delete();

        vr = response.then().log().all();
        vr.statusCode(201);

    }
}
