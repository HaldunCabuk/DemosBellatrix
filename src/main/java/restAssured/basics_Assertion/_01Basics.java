package restAssured.basics_Assertion;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;

public class _01Basics {
    /*
    Rest Assured ile backend testleri
    BDD mantiginda yazilir

    given() -> on kosul Postman deki request kismini ifade eder
    when () -> yapilan islem, method (GET, POST vs...)
    then () -> assertion



     */

    @Test
    public void test01_BasicUsage() {
        given()
                .when()
                .then();
    }

    @Test
    public void test02_getandLog() {
        given()
                .when()
                //.log().headers() -> request in header bilgileri gelir
                .get("https://reqres.in/api/users?page=1")
                .then()
                //.log().body()  // log istenilen kismi konsola yazdirilir
                //.log().all()
                .log().headers() // respons un header ini alir

        ;

    }

    @Test
    public void test03_statusCode() {
        given()
                .when()
                .get("https://reqres.in/api/users?page=1")
                .then()
                .log().status() // status consola yazdirilir
                .statusCode(200) // response code beklentisi(statuscode) assert edilir
                .contentType(ContentType.JSON)//response contentType json olmali


        ;
    }

    @Test
    public void test04_responseTime(){
        String url = "https://reqres.in/api/users?page=1";

        get(url)
                .then()
                .statusCode(200);

        long time = get(url)
                .timeIn(TimeUnit.MILLISECONDS);
        System.out.println(time);




    }

    @Test
    public void test05_prettyPrint(){
        String url = "https://reqres.in/api/users?page=1";

        Response response = get(url)
                .then()
                .extract()//istenilen datayi ayirmak icin kullanilir
                .response(); // response extract edildi

        response.prettyPrint(); // responsu pretty print (beautify) etti
        response
                .then()
                .statusCode(200);
        System.out.println(response.statusCode());

    }

    @Test
    public void test06_pathParams(){

        given()
                .when()
                .get("https://reqres.in/api/users?page=1")
                .then()
                .statusCode(200);

        given()
                .pathParam("page","1")
                .pathParam("link","api")
                .when()
                .get("https://reqres.in/{link}/users?page={page}")
                .then()
                .statusCode(200)
                .log().status();

}

    @Test
    public void quiz_findIds(){
        for (int i = 1; i < 6 ; i++) {

            given()
                    .pathParam("page",i )
                    .when()
                    .get("https://reqres.in/api/users?page={page}")
                    .then()
                    .log().body();
        }

    }

    @Test
    public void test07_getSingleUser(){
        for (int i = 1; i <3 ; i++) {
            getSingleUser(i);
        }
    }


    public void getSingleUser(int id){
        String url = "https://reqres.in/api/users/{id}";
        given()
                .pathParam("id",id)
                .when()
                .get(url)
                .prettyPrint();


    }

    @Test
    public void test07_BaseUri(){

        RestAssured.baseURI = "https://regres.in";
        RestAssured.basePath = "/api";

        // path : baseUri+basePath+ {endPoint}

        given()
                .when()
                .get("/users/1")
                .then()
                .log().body();

        given()
                .when()
                .get("https://regres.in/api/users/2")
                .then()
                .log().body();


    }

    }
