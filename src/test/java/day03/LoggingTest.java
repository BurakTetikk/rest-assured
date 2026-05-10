package day03;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;


public class LoggingTest {
    @Test
    public void testLogging() {
        given()
                .when().get("http://localhost:3000/students")
                .then()
                //.log().headers();
                //.log().cookies();
                .log().body();
                //.log().all();
    }
}
