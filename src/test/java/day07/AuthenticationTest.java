package day07;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class AuthenticationTest {

    @Test
    public void testBasicAuthentication() {
        given()
                .auth().basic("postman", "password")
                .when()
                .get("https://postman-echo.com/basic-auth")
                .then()
                .statusCode(200)
                .body("authenticated", equalTo(true))
                .log().body();

    }

    @Test
    public void testDigestAuthentication() {
        given()
                .auth().digest("postman", "password")
                .when()
                .get("https://postman-echo.com/basic-auth")
                .then()
                .statusCode(200)
                .body("authenticated", equalTo(true))
                .log().body();

    }

    @Test
    public void testPreemptiveAuthentication() {
        given()
                .auth().preemptive().basic("postman", "password") // preemtive de direkt olarak preemtive methodu çağrılmaz, preemtive + basic authentication birlikte çağrılır, basic authenticationdan farkı algoritmasıdır.
                .when()
                .get("https://postman-echo.com/basic-auth")
                .then()
                .statusCode(200)
                .body("authenticated", equalTo(true))
                .log().body();
    }

    // Yukarıdaki 3 authentication "username, password" alarak çalışırlar ama algoritmaları farklıdır

    @Test
    public void testBearerTokenAuthentication() {
        String bearerToken = "secret_token";

        given()
                .header("Authorization", "Bearer " + bearerToken)
                .when()
                .get("https://api.github.com/user/repos")
                .then()
                .statusCode(200)
                .log().all();
    }

    @Test
    public void testOAuth1Authentication() {

        given()
                .auth().oauth("consumerKey", "consumerSecret", "accessToken", "tokenSecret") // this is for OAuth1.0
                .when()
                .get("https://api.github.com/user/repos")
                .then()
                .statusCode(200)
                .log().all();
    }

    @Test
    public void testOAuth2Authentication() {

        given()
                .auth().oauth2("oauth2.0_token")
                .when()
                .get("https://api.github.com/user/repos")
                .then()
                .statusCode(200)
                .log().all();
    }

    @Test
    public void testAPIKeyAuthentication() {

//        given()
//                .queryParam("apiKeyName", "apiKeyValue")
//                .when()
//                .get("url")
//                .then()
//                .statusCode(200)
//                .log().all();

        given()
                .queryParam("apiKeyName", "apiKeyValue")
                .pathParam("path", "data/2.5/forecast/daily")
                .queryParam("q", "Delphi")
                .queryParam("units", "metric")
                .queryParam("cnt", "7")
                .when()
                .get("https://api.openweathermap.org/{path}")
                .then()
                .statusCode(200)
                .log().all();
    }
}
