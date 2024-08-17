package ReSTAssuaredLearning;

import io.restassured.RestAssured;

public class ReSTHookerPing {
    public static void main(String[] args) {

        RestAssured
                .given()
                .baseUri("https://restful-booker.herokuapp.com")
                .basePath("/ping")
                .when()
                .get()
                .then()
                .statusCode(202);
    }
}
