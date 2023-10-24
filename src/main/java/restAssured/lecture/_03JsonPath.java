package restAssured.lecture;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

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
                // .log().body()
                .extract().jsonPath().get("data.email");
        System.out.println(email);

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
        for (String s : list) {
            System.out.println(s);
        }

    }

    @Test
    public void test7_JsonPath() {

        Response response = get("https://reqres.in/api/users");

        List<String> list = response.then()
                .log().body()
                .extract().jsonPath().getList("data.findAll{it.id<2}.email");//it aktif element demek.
        for (String s : list) {
            System.out.println(s);
        }

    }

    @Test
    public void testOdev_JsonPath() {

        Response response = get("https://reqres.in/api/users");

        String name = response
                .jsonPath().get("data.find{it.first_name=='Charles'}.email");
        // tek bir isim cagirdigimiz icin data.find dedik

        System.out.println(name);

    }

    @Test
    public void test9_JsonPath_toMap() {

        Response response = get("https://reqres.in/api/users");

        String name = response
                .jsonPath().get("data.find{it.first_name=='Charles'}").toString();
        System.out.println("name = " + name);

       /* Map<String, ?> map = response.jsonPath()// ? degisken bir deger anlamindadir
                .getMap("data.find{it.first_name=='Charles'}");

        System.out.println(map);
        map.forEach((k, v) -> System.out.println(k + " : " + v));*/
    }

    @Test
    public void test10_JsonPath_toMap() {

        Response response = get("https://reqres.in/api/users");

        String name = response
                .jsonPath().get("data").toString();
        System.out.println(name);

        ArrayList<Map<String, ?>> maps = response.jsonPath()
                .get("data");

        for (Map<String, ?> map : maps) {
            map.forEach((k, v) -> System.out.println(k + " : " + v));
            System.out.println("------");
        }


    }

    @Test
    public void test11_JsonPath_ObjectsToMaps() {
        Response response = get("https://jsonplaceholder.typicode.com/comments");
        //response.prettyPrint();
        ArrayList<Map<String, ?>> maps = response.jsonPath().get("");// yada "$" rootu gosterdik
        System.out.println(maps.size());

    }

    @Test
    public void test12_getDistinctEmailExtensions() {

        List<String> list = get("https://jsonplaceholder.typicode.com/comments")
                .jsonPath().getList("email");

        Set<String> set = new TreeSet<>();

        for (int i = 0; i < list.size(); i++) {
            String[] arr = list.get(i).split("[.]");
            set.add(arr[arr.length - 1]);
        }

        System.out.println(set);

    }

    @Test
    public void test13_getDistinctEmailExtensions_WithJsonPath() throws MalformedURLException {

        String json = get("https://jsonplaceholder.typicode.com/comments").asString();

        List<String> list = JsonPath.from(json).getList("email");
        //List<String> list = JsonPath.from(new URL("https://jsonplaceholder.typicode.com/comments")).getList("email");

        Set<String> set = new TreeSet<>();

        for (int i = 0; i < list.size(); i++) {
            String[] arr = list.get(i).split("[.]");
            set.add(arr[arr.length - 1]);
        }

        System.out.println(set);

    }
    public static void main(String[] args) {
        // Örnek bir dizi tanımla
        String[] arr = {"John", "Doe", "example.com"};

        // arr dizisinin 1. elemanını al ve yazdır
        String domain = arr[1];
        System.out.println("Domain: " + domain);
    }
}