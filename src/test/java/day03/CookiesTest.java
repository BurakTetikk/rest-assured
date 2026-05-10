package day03;

import io.restassured.http.Cookies;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class CookiesTest {
    @Test
    public void testCookies() {
        given()
                .when().get("https://www.google.com/")
                .then()
                .cookie("AEC", "AaJma5tT0l2mfBn47bD9cPY61YpsYT_-gO-jZLiELwSjXHyK2hRzI-DeeA")
                .log().all();

    }

    @Test
    public void testGetCookieInformation() {
        Response response = given()
                .when().get("https://www.google.com/");

        String aec = response.getCookie("AEC");
        System.out.println("AEC cookie value is: " + aec);
    }

    @Test
    public void testGetCookiesInformation() {
        Response response = given()
                .when().get("https://www.google.com/");

        //System.out.println(response.getCookies().keySet());

        response.getCookies().forEach((key, value) -> {
            String cookie = response.getCookie(key);
            System.out.println(key + " value is: " + cookie);
            //System.out.println(key + " value is: " + value);
        });
    }
}
