package day06;

import io.restassured.module.jsv.JsonSchemaValidator;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

//https://jsonformatter.org/json-to-jsonschema

public class JsonSchemaValidationTest {
    @Test
    public void testJsonSchemaValidation() {
        given()
                .when().get("http://localhost:3000/store")
                .then()
                .assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("jsonSchemaValidator.json"));

    }
}
