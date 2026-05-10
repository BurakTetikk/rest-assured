package day03;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class PathAndQueryParametersTest {

    @Test
    void testPathAndQueryParameters() {
        String key = "reqres_19a5a6baaa8a4b9e8927291ca2011079";
        given()
                .header("x-api-key", key)
                .contentType(ContentType.JSON)
                .pathParams("mypath", "users")
                .queryParam("page", 2)
                .queryParam("id", 5)
                .when()
                .get("https://reqres.in/api/{mypath}")
                .then()
                .statusCode(200)
                .log().all();
    }

}
