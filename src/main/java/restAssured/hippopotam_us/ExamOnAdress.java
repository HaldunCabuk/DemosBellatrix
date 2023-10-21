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
                //.body("country abbreviation",equalTo("TR"))
                .body("places[0].state", equalTo("İzmir"))
                //.body("latitude",equalTo("40.35"))
                .body("places.state", hasItems("İzmir"))
                //.body("places.state abbreviation",hasItems("35"))
                .body(containsString("post code"))
                .body(containsString("37.55"))


        ;
    }


    /*public void forLoop(){

        int num = 35380;
        int lNum = 35399;

        for (int i = num; i < lNum ; i+=5) {
            autoCheckAssert (i);
        }
    }*/

    @Test
    public void autoCheckAssert() {



        int num = 35380;
        int lNum = 35383;

        String url = "https://api.zippopotam.us/TR/{pc}";

        for (int i = num; i < lNum; i ++) {
            Response response = given().pathParam("pc",i).when().get(url);

            if (response.body() != null){
            given()
                    .pathParam("pc", i)
                    .when()
                    .get(url)
                    .prettyPrint();
        }
        }



        /*do {
            given()
                    .pathParam("pc", number)
                    .when()
                    .get(url)
                    .prettyPrint();

        }while (given().get(url).then().body(containsString("places")) != "" )*/


















        ;
    }
    @Test
    public void autoCheckAssertdeneme() {



        int num = 35380;
        int lNum = 35383;
        int count = 0;

        String url = "https://api.zippopotam.us/TR/{pc}";

        for (int i = num; i < lNum; i ++) {
            Response response = given().pathParam("pc",i).when().get(url)
                    .then().extract().response();
            String str = response.body().prettyPrint().toString();


            if (str != ""){
                given()
                        .pathParam("pc", i)
                        .when()
                        .get(url)
                        .prettyPrint();
                count++;
            }
        }
        System.out.println(count);

}
    @Test
    public void _autoCheckAssert() {





        String url = "https://api.zippopotam.us/TR/35381";
        Response response = get(url)
                .then().extract().response();
        response.prettyPrint();




        }
}
