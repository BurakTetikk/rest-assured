package day08;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DeleteUserTest {
    @Test
    public void testDeleteUser(ITestContext testContext) {
        //int id = (int) testContext.getAttribute("id");
        int id = (int) testContext.getSuite().getAttribute("id");
        String token = "e4157702f668028734013c5543b7306bcfeeb2a85c804cf66c29f3601f6c7c11";

        given()
                .header("Authorization", "Bearer " + token)
                .pathParam("id", id)
                .when()
                .delete("https://gorest.co.in/public/v2/users/{id}")
                .then()
                .statusCode(204)
                .log().all();
    }
}
