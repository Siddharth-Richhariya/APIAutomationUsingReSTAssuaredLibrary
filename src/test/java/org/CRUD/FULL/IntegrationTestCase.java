package org.CRUD.FULL;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.testng.Assert.assertEquals;


public class IntegrationTestCase {
    RequestSpecification requestSpecification = RestAssured.given();
    Response response;
    ValidatableResponse validatableResponse;
    String BaseURL = "https://restful-booker.herokuapp.com";
    String token;
    Integer bookingId;

    @BeforeTest
    public void getToken(){
        String Payload = "{\n" +
                "    \"username\" : \"admin\",\n" +
                "    \"password\" : \"password123\"\n" +
                "}";

        String BasePath= "auth";
        requestSpecification.baseUri(BaseURL);
        requestSpecification.basePath(BasePath);
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.body(Payload).log().all();

        response = requestSpecification.post();

        validatableResponse= response.then();
        validatableResponse.statusCode(200);

        token = response.then().log().all().extract().path("token");
        System.out.println(token);
        Assert.assertNotNull(token);
    }
    @BeforeClass
    public void getBookingID(){
        String BasePath = "booking";
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
        requestSpecification.baseUri(BaseURL);
        requestSpecification.basePath(BasePath);
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.body(Payload).log().all();

        response = requestSpecification.post();

        validatableResponse = response.then();
        validatableResponse.statusCode(200);

        bookingId = response.then().log().all().extract().path("bookingid");
        System.out.println(bookingId);
        Assert.assertNotNull(bookingId);
    }
    @Test
    public void updateDetails(){
        String Payload = "{\n" +
                "    \"firstname\" : \"James\",\n" +
                "    \"lastname\" : \"Brown\",\n" +
                "    \"totalprice\" : 123,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2024-01-01\",\n" +
                "        \"checkout\" : \"2024-01-01\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Breakfast,lunch\"\n" +
                "}";

        requestSpecification.baseUri(BaseURL);
        requestSpecification.basePath("booking/"+bookingId);
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.cookie("token", token);
        requestSpecification.body(Payload).log().all();

        response = requestSpecification.put();

        validatableResponse = response.then();
        validatableResponse.statusCode(200);

        String firstnameResponse = response.then().log().all().extract().path("firstname");

        assertEquals(firstnameResponse, "James");

        // 1. RA - Matchers
        validatableResponse.body("firstname", Matchers.equalTo("James"));
        validatableResponse.body("lastname", Matchers.equalTo("Brown"));

        //  2. TestNG Asserts -
        //  Assert.assertEquals(firstNameResponse,"Pramod");
        String firstNameResponse = response.then().log().all().extract().path("firstname");
        assertEquals(firstNameResponse,"James");

        String fullResponseJSONString = response.asString();
        System.out.println(fullResponseJSONString);

        // 3 TestNG Assertion with JSON Path Lib
        JsonPath jsonPath = new JsonPath(fullResponseJSONString);
        String firstNameJSONPathExtracted = jsonPath.getString("firstname");
        String lastNameJSONPathExtracted = jsonPath.getString("lastname");
        Integer totalpriceJSONPathExtracted = jsonPath.getInt("totalprice");
        String checkinDate = jsonPath.getString("bookingdates.checkin");


        assertEquals(firstNameJSONPathExtracted,"James");
        assertEquals(lastNameJSONPathExtracted,"Brown");
        assertEquals(totalpriceJSONPathExtracted,123);
        assertEquals(checkinDate,"2024-01-01");
        Assert.assertNotNull(totalpriceJSONPathExtracted);


        // JSON Array Response
        String checkin = jsonPath.getString("[0][\"bookingdates\"][\"checkin\"]");
        System.out.println(checkin);


        // 4. AssertJ Matching

        assertThat(firstNameJSONPathExtracted)
                .isEqualTo("James")
                .isNotBlank().isNotEmpty();

        assertThat(totalpriceJSONPathExtracted).isPositive().isNotZero();



    }

}
