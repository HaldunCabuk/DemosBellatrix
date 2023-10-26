package restAssured.lecture;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class _08GoRest {

    String accessToken = "Bearer 8945d5d3c32830c0508a726471600d879047b9de7d7516ece5a56c08ac5cc642";


    @BeforeClass
    public void beforeClass() {
        RestAssured.baseURI = "https://gorest.co.in/public/v2";

    }


    /*
List users
curl -i -H "Accept:application/json"
-H "Content-Type:application/json"
-H "Authorization: Bearer ACCESS-TOKEN"
-XGET "https://gorest.co.in/public/v2/users"

*/

    @Test(priority = 1)
    public void getAllUsers() {
        given()
                .header("Authorization", accessToken)
                .accept(ContentType.JSON)
                .when().get("/users")
                .then().log().body()
                .statusCode(oneOf(200, 201))
                .body(not(empty()))


        ;
    }

/*
Create user
curl -i -H "Accept:application/json"
-H "Content-Type:application/json"
-H "Authorization: Bearer ACCESS-TOKEN"
 -XPOST "https://gorest.co.in/public/v2/users"
 -d '{"name":"Tenali Ramakrishna",
 "gender":"male",
 "email":"tenali.ramakrishna@15ce.com",
  "status":"active"}'
*/

    public String randomEmail() {
        return RandomStringUtils.randomAlphabetic(10)
                + "@" + RandomStringUtils.randomAlphabetic(3).toLowerCase()
                + "." + RandomStringUtils.randomAlphabetic(3).toLowerCase();
    }

    int id = 0;

    @Test(priority = 2)
    public void createUser() {

        String body = "{\"name\":\"Haldun Cabuk\", \n" +
                " \"gender\":\"male\", \n" +
                " \"email\":\"" + randomEmail() + "\",\n" +
                "  \"status\":\"active\"}";
        id = given()
                .header("Authorization", accessToken)
                .header("Accept", "application/json")// ==.accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post("/users")
                .then()
                .log().body()
                .statusCode(oneOf(200, 201))
                .body(not(empty()))
                .contentType(ContentType.JSON)
                .extract().jsonPath().getInt("id")


        ;
    }

    /*Update user
    curl -i
    -H "Accept:application/json"
    -H "Content-Type:application/json"
    -H "Authorization: Bearer ACCESS-TOKEN"
    -XPATCH "https://gorest.co.in/public/v2/users/628347"
    -d '{"name":"Allasani Peddana",
    "email":"allasani.peddana@15ce.com",
    "status":"active"}'*/
    @Test(priority = 3, dependsOnMethods = "createUser")
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



/*Delete user
curl -i
-H "Accept:application/json"
-H "Content-Type:application/json"
-H "Authorization: Bearer ACCESS-TOKEN"
-XDELETE "https://gorest.co.in/public/v2/users/628347"*/

    @Test(priority = 4)
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
