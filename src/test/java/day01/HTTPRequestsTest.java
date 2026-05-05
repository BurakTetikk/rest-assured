package day01;
/*
    given()
        content type, set cookies, add auth, add param, set headers info etc...
    when()
        get, post, put, delete
    then()
        validate status code, extract response, extract headers cookies & response body...
 */

import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class HTTPRequestsTest {

    String id = "";

    //@Test
    void getUsers() {
        String key = "reqres_19a5a6baaa8a4b9e8927291ca2011079";

        given()
                .header("x-api-key", key)
                .when()
                    .get("https://reqres.in/api/users")
                .then()
                    .statusCode(200)
                    .body("page", equalTo(1))
                    .log().all();
    }

    @Test(priority = 3)
    void getUser() {
        String key = "reqres_19a5a6baaa8a4b9e8927291ca2011079";
        given()
                    //.header("Authorization", "Bearer ", key)
                    .header("x-api-key", key)
                .when()
                    .get("https://reqres.in/api/users/" + id)
                .then()
                    .log().body()
                    .statusCode(200);
    }

    @Test(priority = 1)
    void createUser() {
        String key = "reqres_19a5a6baaa8a4b9e8927291ca2011079";

        HashMap<String,String> map = new HashMap<>();
        map.put("email", "eve.holt@reqres.in");
        map.put("password", "pistol");

        id = given()
                .header("x-api-key", key)
                .contentType("application/json")
                .body(map)
        .when()
                .post("https://reqres.in/api/register")
                .jsonPath().getString("id");

        System.out.println("Token: " + id);

    }

    @Test(priority = 2)
    void updateUser() {
        String key = "reqres_19a5a6baaa8a4b9e8927291ca2011079";

        HashMap<String,String> map = new HashMap<>();
        map.put("name", "morpheus");
        map.put("job", "zion resident");

       given()
               .header("x-api-key", key)
               .contentType("application/json")
               .body(map)
       .when()
                .put("https://reqres.in/api/users/" + id)
       .then()
               .statusCode(200)
               .log().body();

    }

    @Test(priority = 4)
    void deleteUser() {
        String key = "reqres_19a5a6baaa8a4b9e8927291ca2011079";

        given()
                .header("x-api-key", key)
                .when()
                .put("https://reqres.in/api/users/" + id)
                .then()
                .statusCode(204);
    }
}
