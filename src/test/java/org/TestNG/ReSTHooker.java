package org.TestNG;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

public class ReSTHooker {

    @Test
    public void getRequest1(){
        RestAssured
                .given()
                .baseUri("https://restful-booker.herokuapp.com")
                .basePath("/ping")
                .when()
                .get().then().statusCode(201);
    }
    @Test
    public void getRequest2(){
        RestAssured
                .given()
                .baseUri("https://restful-booker.herokuapp.com")
                .basePath("/ping")
                .when()
                .get().then().statusCode(201);
    }
}
