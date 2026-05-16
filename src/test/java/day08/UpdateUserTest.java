package day08;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class UpdateUserTest {
    @Test
    public void createUserTest(ITestContext testContext) throws JsonProcessingException {
        Faker faker = new Faker();
        UserCreateRequestDTO userCreateRequestDTO = new UserCreateRequestDTO(
                faker.name().fullName(),
                faker.internet().safeEmailAddress(),
                "male",
                "active"
        );

        String token = "e4157702f668028734013c5543b7306bcfeeb2a85c804cf66c29f3601f6c7c11";

        //int id = (int) testContext.getAttribute("id");
        int id = (int) testContext.getSuite().getAttribute("id");
        given()
                .contentType(ContentType.JSON)
                .body(userCreateRequestDTO)
                .header("Authorization", "Bearer " + token)
                .pathParam("id", id)
                .when()
                .put("https://gorest.co.in/public/v2/users/{id}")
                .then()
                .statusCode(200)
                .log().all();


    }
}
