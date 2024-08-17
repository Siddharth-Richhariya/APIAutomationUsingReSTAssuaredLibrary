package org.TestNG;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

public class ReSTHooker {

    @Test
    public void getRequest(){
        RestAssured
                .given()
                .baseUri("https://restful-booker.herokuapp.com")
                .basePath("/ping")
                .when()
                .get().then().statusCode(201);
    }
}
