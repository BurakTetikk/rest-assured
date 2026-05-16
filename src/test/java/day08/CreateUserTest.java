package day08;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class CreateUserTest {
    @Test
    public void createUserTest(ITestContext testContext) throws JsonProcessingException {
        Faker faker = new Faker();
        UserCreateRequestDTO userCreateRequestDTO = new UserCreateRequestDTO(
                faker.name().fullName(),
                faker.internet().safeEmailAddress(),
                "male",
                "active"
        );
        //System.out.println(userCreateRequestDTO.toString());
//        ObjectMapper objectMapper = new ObjectMapper();
//        String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(userCreateRequestDTO);
        //System.out.println(json);

        String token = "e4157702f668028734013c5543b7306bcfeeb2a85c804cf66c29f3601f6c7c11";

//        Response authorization = given()
//                .header("Authorization", "Bearer " + token)
//                .body(userCreateRequestDTO)
//                .contentType(ContentType.JSON)
//                .when()
//                .post("https://gorest.co.in/public/v2/users");
//
//        //System.out.println(authorization.getBody().asString());
//        int id = authorization.jsonPath().getInt("id");
//        //System.out.println("Generated id is: " + id);
//        Assert.assertEquals(authorization.getStatusCode(), 201);

        UserCreateResponseDTO authorization = given()
                .contentType(ContentType.JSON)
                .body(userCreateRequestDTO)
                .header("Authorization", "Bearer " + token)
                .when()
                .post("https://gorest.co.in/public/v2/users")
                .as(UserCreateResponseDTO.class);

        Integer id = authorization.id();
        //testContext.setAttribute("id", id);
        testContext.getSuite().setAttribute("id", id);
        System.out.println("Generated id is: " + id);


    }
}
