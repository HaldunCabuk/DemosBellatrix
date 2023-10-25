package restAssured.sampleQuiz;

import io.restassured.http.ContentType;
import lombok.Data;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Order;


import java.util.List;

import static io.restassured.RestAssured.get;
import static org.hamcrest.Matchers.*;


public class Tasks {

    /** Task 1
     * create a request to https://httpstat.us/203
     * expect status 203
     * expect content type TEXT
     */
    @Test
    public void task1(){

                get("https://httpstat.us/203")
                .then()
                .log().body()
                .statusCode(203)
                .contentType(ContentType.TEXT);

    }

    /** Task 2
     * create a request to https://httpstat.us/203
     * expect status 203
     * expect content type TEXT
     * expect BODY to be equal to "203 Non-Authoritative Information"
     */

    @Test
    public void task2(){
        get("https://httpstat.us/203")
                .then()
                .log().body()
                .statusCode(203)
                .contentType(ContentType.TEXT)
                .body(containsString("203 Non-Authoritative Information"))



                ;
    }

    /** Task 3
     *  create a request to https://jsonplaceholder.typicode.com/todos/2
     *  expect status 200
     *  expect content type JSON
     *  expect title in response body to be "quis ut nam facilis et officia qui"
     */
    @Test
    public void task3(){
        get("https://jsonplaceholder.typicode.com/todos/2")
                .then()
                .statusCode(oneOf(200,201))
                .log().body()
                .contentType(ContentType.JSON)
                .body("title", equalTo("quis ut nam facilis et officia qui"))

;
    }

    /** Task 4
     * create a request to https://jsonplaceholder.typicode.com/todos/2
     *  expect status 200
     *  expect content type JSON
     *  expect response completed status to be false
     */

    @Test
    public void task4(){
        get("https://jsonplaceholder.typicode.com/todos/2")
                .then()
                .log().body()
                .statusCode(oneOf(200,201))
                .contentType(ContentType.JSON)
                .body("completed", equalTo(false))

        ;
    }

    /** Task 5
     * create a request to https://jsonplaceholder.typicode.com/todos
     * expect status 200
     * expect content type JSON
     * expect third item have:
     *      title = "fugiat veniam minus"
     *      userId = 1
     */
    @Test
    public void task5(){

      List<String> titles =  get("https://jsonplaceholder.typicode.com/todos")
                .jsonPath().getList("title");
        System.out.println("titles = " + titles);

        get("https://jsonplaceholder.typicode.com/todos")
                .then()
                .log().body()
                .statusCode(oneOf(200,201))
                .contentType(ContentType.JSON)
                .body("title[2]", equalTo("fugiat veniam minus"))
                .body("userId[2]",equalTo(1))




        ;
    }

    /** Task 6
     * create a request to https://jsonplaceholder.typicode.com/todos/2
     * expect status 200
     * Converting Into POJO
     */

    /** Task 7
     * create a request to https://jsonplaceholder.typicode.com/todos
     * expect status 200
     * Converting Array Into Array of POJOs
     */
}
