package restAssured.hippopotam_us;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

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
        String url = "https://api.zippopotam.us/TR/35380";

        Response response = get(url)
                .then().extract().response();
        response.prettyPrint();

        Headers headers = get(url)
                .then().extract().headers();
        System.out.println("HeadersHighlight: = " + headers);
        ;


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

        for (int i = num; i < lNum; i += 5) {

            given()
                    .pathParam("pc", i)
                    .when()
                    .get(url)
                    .prettyPrint()
            ;
        }


    }

    @Test
    public void ExamOnAdressForAssert() {
        given()
                .get("https://api.zippopotam.us/TR/35380")
                .then()
                .contentType(ContentType.JSON)
                .log().body()
                .statusCode(200)
                .body("$", hasKey("post code"))
                .body("country", equalTo("Turkey"))
                .body("places[0].state", equalTo("İzmir"))
                .body("places.state", hasItems("İzmir"))
                .body(containsString("post code"))
                .body(containsString("37.55"))
                .body("places[0].latitude", equalTo("40.35"))
                .body("country", equalTo("Turkey"))

        ;
    }


    @Test
    public void getAdressWithData() {

        int num = 35380;
        int lNum = 35399;

        String data = "places";

        for (int i = num; i < lNum; i++) {
            pCodeLocationSearch1(i, data);
        }
    }


    public void pCodeLocationSearch1(int i, String data) {
        String url = "https://api.zippopotam.us/TR/{pc}";
        String str = "";

        Response response = given().pathParam("pc", i).when().get(url)
                .then().extract().response();

        str = response.getBody().asString();

        if (str.contains(data)) {

            given()
                    .pathParam("pc", i)
                    .when()
                    .get(url)
                    .prettyPrint();

        }
    }


    @Test
    public void AdressAssert() {

        int num = 35380;
        int lNum = 35399;
        String data = "places";

        for (int i = num; i < lNum; i++) {
            pCodeLocationSearch2(i, data);
        }
    }


    public void pCodeLocationSearch2(int i, String data) {
        String url = "https://api.zippopotam.us/TR/{pc}";
        String str = "";

        Response response = given().pathParam("pc", i).when().get(url)
                .then().extract().response();

        str = response.getBody().asString();

        if (str.contains(data)) {
            given()
                    .pathParam("pc", i)
                    .when()
                    .get(url)
                    .then()
                    .body("country", equalTo("Turkey"))
                    .body("places[0].state", equalTo("İzmir"))
                    .body("country", equalTo("Turkey"));

            given().pathParam("pc", i)
                    .when().get(url).prettyPrint();

        }

    }
}

