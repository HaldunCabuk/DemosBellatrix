package restAssured.sampleQuiz.task9;

import org.junit.Test;
import static io.restassured.RestAssured.get;

public class Task9 {

    @Test
    public void test_9(){
      int userId =  get("https://jsonplaceholder.typicode.com/users")
               .then().extract().jsonPath().getInt("find(it.name=='Erwin Howell').id");
        System.out.println(userId);

    }





}
