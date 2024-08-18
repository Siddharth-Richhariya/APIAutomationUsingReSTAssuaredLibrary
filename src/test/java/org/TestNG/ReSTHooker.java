package org.TestNG;

import io.qameta.allure.Description;
import io.restassured.RestAssured;
import org.testng.annotations.Test;

public class ReSTHooker {

    @Test
    @Description ("Verift Get Request 1 ")
    public void getRequest1(){
        RestAssured
                .given()
                .baseUri("https://restful-booker.herokuapp.com")
                .basePath("/ping")
                .when()
                .get().then().statusCode(201);
    }
    @Test
    @Description ("Verift Get Request 2")
    public void getRequest2(){
        RestAssured
                .given()
                .baseUri("https://restful-booker.herokuapp.com")
                .basePath("/ping")
                .when()
                .get().then().statusCode(201);
    }
}
