
package restAssured.lecture;


import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.jupiter.api.Order;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class _08GoRest1 {

    String accessToken = "Bearer 8945d5d3c32830c0508a726471600d879047b9de7d7516ece5a56c08ac5cc642";
        RequestSpecification requestSpecification;
        ResponseSpecification responseSpecification;

    @Before
    public void beforeClass() {
        RestAssured.baseURI = "https://gorest.co.in/public/v2";
        requestSpecification = new RequestSpecBuilder()
                .addHeader("Authorization", accessToken)
                .setContentType(ContentType.JSON)
                .build();

        responseSpecification = new ResponseSpecBuilder()
                .expectStatusCode(oneOf(200,201))
                .expectContentType(ContentType.JSON)
                .build();

    }

    @Test()
    @Order(1)
    public void getAllUsers() {
        given()
                .spec(requestSpecification)
                .accept(ContentType.JSON)
                .when().get("/users")
                .then().log().body()
                .spec(responseSpecification)
                .body(not(empty()))


        ;
    }



    public String randomEmail() {
        return RandomStringUtils.randomAlphabetic(10)
                + "@" + RandomStringUtils.randomAlphabetic(3).toLowerCase()
                + "." + RandomStringUtils.randomAlphabetic(3).toLowerCase();
    }

    int id = 0;

    @Test()
    @Order(2)
    public void createUser() {

        String body = "{\"name\":\"Tenali Ramakrishna\", \n" +
                " \"gender\":\"male\", \n" +
                " \"email\":\"" + randomEmail() + "\",\n" +
                "  \"status\":\"active\"}";
        id = given()
                .spec(requestSpecification)
                .body(body)
                .when()
                .post("/users")
                .then()
                .log().body()
                .spec(responseSpecification)
                .body(not(empty()))
                .extract().jsonPath().getInt("id")


        ;
    }


    @Test()
    public void updateUser() {
        String mail = randomEmail();
        String body = "" + " {\"name\":\"Allasani Peddana\",\n" +
                "\"email\":\"" + mail + "\" ,\n" +
                "\"status\":\"active\"} ";


        given()
                .header("Authorization", accessToken)
                .header("Accept", "application/json")
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .patch("/users/" + id)
                .then()
                .log().ifError()
                .statusCode(oneOf(200, 201, 204))
                .contentType(ContentType.JSON)
                .body(not(empty()))
                .body("email", equalTo(mail))


        ;

    }




    @Test()
    public void deleteUser() {


        given()
                .header("Authorization", accessToken)
                .when()
                .delete("/users/" + id)
                .then()
                .statusCode(oneOf(200, 201, 204))



        ;

    }


}
