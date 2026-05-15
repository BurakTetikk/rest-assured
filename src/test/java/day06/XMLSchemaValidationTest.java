package day06;


import io.restassured.matcher.RestAssuredMatchers;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class XMLSchemaValidationTest {
    @Test
    public void testXMLSchemaValidation() {
        given()
                .when().get("xml-response-body-return-url")
                .then()
                .assertThat().body(RestAssuredMatchers.matchesXsdInClasspath("xmlSchemaValidator.xsd"));

    }
}
