package restAssured;

import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.get;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.IsEqual.equalTo;

public class _03JsonPath {
    @Test
    public void test1() {
        get("https://jsonplaceholder.typicode.com/")
                .prettyPrint();


    }


    @Test
    public void test3() {
        Response response = get("https://jsonplaceholder.typicode.com/");
        //Response response =  get("https://jsonplaceholder.typicode.com/").then().extract().response();

        response.prettyPrint();
        response.then().statusCode(200);


    }

    @Test
    public void test4() {
        Response response = get("https://jsonplaceholder.typicode.com/");

        response.then()
                .body("[0].id", equalTo(1))
                .body("email", hasSize(500))// emailleri list olarak secer hata verir


        ;

    }

    @Test
    public void test5_JsonPath() {
        Response response = get("https://reqres.in/api/users/1");

        String email = response.then()
                .log().body()
                .extract().jsonPath().get("data.email");

        int id = response.then()
                .extract().jsonPath().get("data.id");
        System.out.println(id);

    }

    @Test
    public void test6_JsonPath() {

        Response response = get("https://reqres.in/api/users");

        List<String> list = response.then()
                //.log().body()
                .extract().jsonPath().getList("data.email");//get() ile de alabilirdik
        for (String s : list){
            System.out.println(s);
        }

    }
    @Test
    public void test7_JsonPath() {

        Response response = get("https://reqres.in/api/users");

        List<String> list = response.then()
                .log().body()
                .extract().jsonPath().getList("data.findAll{it.id<2}.email");//it aktif element demek.
        for (String s : list){
            System.out.println(s);
        }




}
}