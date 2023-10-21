package restAssured.basics_Assertion;

import io.restassured.http.ContentType;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.*;

public class _02Assertions {
    @Test
    public void test01(){
        given()
                .when()
                .get("https://reqres.in/api/users/1")
                .then()
                .log().body()
                .body("data.id",equalTo(1))
                .body("data.first_name",equalTo("George"))
                .body(containsString("contributions towards"))
                .body("data", hasKey("id"))
                .body("data.id",lessThan(5))
                .body(not(empty()))


        ;
    }

    @Test
    public void test02(){
        given()
                .pathParam("page",2)
                .when()
                .get("https://reqres.in/api/users?page={page}")
                .then()
                .contentType(ContentType.JSON)
                .log().body()
                .statusCode(200)
                .body("page",equalTo(2))
                .body("data[1].last_name",equalTo("Ferguson"))
                .body(containsString("rachel"))
                .body("data.first_name",hasItems("Michael","Lindsay"))
                .body("",hasKey("support"))
                .body("$",hasKey("support"))// $ = json in rootunu ifade eder
                .body(containsString("contributions"))
                // data.first_name icinde "Michael" ve "Lindsay" olmali
                .body("data.first_name",hasItems("Michael","Lindsay"))



        ;
    }
}
