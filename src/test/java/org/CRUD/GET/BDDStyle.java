package org.CRUD.GET;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

public class BDDStyle {

    @Test
    public void getResponse(){
        RestAssured.given()
                .baseUri("https://api.zippopotam.us/")
                .basePath("/us/90210")

                .when().log().all().get()

                .then().log().all().statusCode(200);
    }
}
