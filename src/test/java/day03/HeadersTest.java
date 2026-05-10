package day03;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;


public class HeadersTest {
    @Test
    public void testHeaders() {
        given()
                .when().get("https://www.google.com/")
                .then()
                .header("content-Type", "text/html; charset=ISO-8859-1")
                .and()
                .header("server", "gws")
                .and()
                .header("content-encoding", "gzip");
                //.log().headers();
    }

    @Test
    public void testGetHeadersInfo() {
        Response response = given()
                .when().get("https://www.google.com/");

        response.getHeaders().forEach((header) -> {
            System.out.println(header.getName() + ": " + header.getValue());
        });
    }

}
