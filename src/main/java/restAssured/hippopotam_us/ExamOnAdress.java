package restAssured.hippopotam_us;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasKey;

public class ExamOnAdress {

    @Test
    public void baseTests() {

        RestAssured.baseURI = "https://api.zippopotam.us/";

        given()
                .get("TR/01000")
                .then()
                .statusCode(200)
                .log().headers()
                .contentType(ContentType.JSON);
        String url = "https://api.zippopotam.us/TR/01000";

        long delayTime = get(url).timeIn(TimeUnit.MILLISECONDS);
        System.out.println(delayTime);
    }

    @Test
    public void extract() {
        String url = "https://api.zippopotam.us/TR/01000";

        Response response = get(url)
                .then().extract().response();
        response.prettyPrint();

        Headers headers = get(url)
                .then().extract().headers();
        System.out.println(headers);


    }

    @Test
    public void hippo_pathParams() {

        given()
                .pathParam("link1", "TR")
                .pathParam("link2", "35380")
                .get("https://api.zippopotam.us/{link1}/{link2}")
                .then()
                .log().status()
                .log().body()
        ;
    }

    @Test
    public void forLoop_pathParams() {

        int num = 35380;
        int lNum = 35399;
        String url = "https://api.zippopotam.us/TR/{pc}";

        for (int i = num; i < lNum ; i+= 5) {

            given()
                    .pathParam("pc", i)
                    .when()
                    .get(url)
                    .prettyPrint()
            ;
        }


    }
    @Test
    public void ExamOnAdressForAssert(){
        given()
                .get("https://api.zippopotam.us/TR/35380")
                .then()
                .contentType(ContentType.JSON)
                .log().body()
                .statusCode(200)
                .body("$",hasKey("post code"))
                .body("country", equalTo("Turkey"))
               // .body("country abbreviation",equalTo("TR"))
                .body("places[0].place name",equalTo("Seyhan Mah."))






        ;
    }

}
