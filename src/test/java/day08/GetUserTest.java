package day08;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class GetUserTest {
    @Test
    public void testGetUser(ITestContext context) {
        //int id = (int) context.getAttribute("id");
        int id = (int) context.getSuite().getAttribute("id");
        String token = "e4157702f668028734013c5543b7306bcfeeb2a85c804cf66c29f3601f6c7c11";

        given()
                .header("Authorization", "Bearer " + token)
                .pathParam("id", id)
                .when()
                .get("https://gorest.co.in/public/v2/users/{id}")
                .then()
                .statusCode(200)
                .log().all();
    }
}
