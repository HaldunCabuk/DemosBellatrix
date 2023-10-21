package restAssured.hippopotam_us;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class ExamOnAdress {

    @Test
    public void baseTests() {

        RestAssured.baseURI = "https://api.zippopotam.us/";

        given()
                .get("TR/01000")
                .then()
                .statusCode(200)
                .log().headers()
                .contentType(ContentType.JSON)




        ;
    }
}
