package day02;
/*
    Different  ways to create POST Request body
    1) Post request body using HashMap
    2) Post request body creation using org.json
    3) Post request body creation using POJO Class
    4) Post request body creation using external json file data
 */

import io.restassured.http.ContentType;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class DiffWaysToCreatePostReqBodyTest {
    // 1) Post request body using HashMap

    @Test
    void testPostUsingMap() {
        HashMap data = new HashMap<>();
        data.put("name", "Sare Tetik");
        data.put("location", "Zonguldak");
        data.put("phone", "5558889977");

        String[] courses = {"Java", "Rest Assured"};

        data.put("courses", courses);

        given()
                .contentType("application/json")
                .body(data)
                .when()
                .post("http://localhost:3000/students")
                .then()
                .statusCode(201)
                .body("name", equalTo("Sare Tetik"))
                .body("location", equalTo("Zonguldak"))
                .body("phone", equalTo("5558889977"))
                .body("courses[0]", equalTo("Java"))
                .body("courses[1]", equalTo("Rest Assured"))
                .header("Content-type", equalTo("application/json"))
                .log().all();

    }

    // 2) Post request body creation using org.json
    @Test
    void testPostUsingJsonLibrary() {
        JSONObject data = new JSONObject();
        data.put("name", "Sare Tetik");
        data.put("location", "Zonguldak");
        data.put("phone", "5558889977");

        String[] courses = {"Java", "Rest Assured"};

        data.put("courses", courses);

        given()
                .contentType("application/json")
                .body(data.toString())
                .when()
                .post("http://localhost:3000/students")
                .then()
                .statusCode(201)
                .body("name", equalTo("Sare Tetik"))
                .body("location", equalTo("Zonguldak"))
                .body("phone", equalTo("5558889977"))
                .body("courses[0]", equalTo("Java"))
                .body("courses[1]", equalTo("Rest Assured"))
                .header("Content-type", equalTo("application/json"))
                .log().all();

    }

    // 3) Post request body creation using POJO Class
    @Test
    void testPostUsingPOJO() {
        PostRequestPOJO data = new PostRequestPOJO();
        data.setName("Sare Tetik");
        data.setLocation("Zonguldak");
        data.setPhone("5558889977");

        String[] courses = {"Java", "Rest Assured"};

        data.setCourses(courses);

        given()
                .contentType("application/json")
                .body(data)
                .when()
                .post("http://localhost:3000/students")
                .then()
                .statusCode(201)
                .body("name", equalTo("Sare Tetik"))
                .body("location", equalTo("Zonguldak"))
                .body("phone", equalTo("5558889977"))
                .body("courses[0]", equalTo("Java"))
                .body("courses[1]", equalTo("Rest Assured"))
                .header("Content-type", equalTo("application/json"))
                .log().all();

    }

    // 4) Post request body creation using external json file data
    @Test
    void testPostUsingExternalJson() throws IOException {
        Path path = Path.of("src/test/resources/db.json");

        String s = Files.readString(path);
//        JSONTokener jt = new JSONTokener(s);
//        JSONObject data = new JSONObject(jt);


        given()
                .contentType(ContentType.JSON)
                .body(s)
                .when()
                .post("http://localhost:3000/students")
                .then()
                .statusCode(201)
                .header("Content-type", equalTo("application/json"))
                .body("name", equalTo("Sare Tetik"),
                        "location", equalTo("Zonguldak"),
                        "phone", equalTo("5558889977"),
                        "courses[0]", equalTo("Java"),
                        "courses[1]", equalTo("Rest Assured"))
                .log().ifValidationFails();

    }

    @Test
    void testDelete() {
        given()
                .when()
                .delete("http://localhost:3000/students/3")
                .then()
                .statusCode(200);
    }
}
