package day05;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.*;

public class FileUploadAndDownloadTest {
    @Test
    public void testFileUpload() {
        File fileName = new File("path");
        given()
                .multiPart("file", fileName) // key, value
                .contentType(ContentType.MULTIPART) // contentType("multipart/form-data)
                .when()
                .post("http://localhost:8080/uploadFile")
                .then()
                .statusCode(200)
                .body("fileName", equalTo("Text1.txt"));
    }

    @Test
    public void testMultipleFilesUpload() {
        File fileName1 = new File("path");
        File fileName2 = new File("path");
        given()
                .multiPart("files", fileName1) // key, value
                .multiPart("files", fileName2) // key, value
                .contentType(ContentType.MULTIPART) // contentType("multipart/form-data)
                .when()
                .post("http://localhost:8080/uploadFile")
                .then()
                .statusCode(200)
                .body("[0].fileName", equalTo("Text1.txt"))
                .body("[1].fileName", equalTo("Text2.txt"));
    }

    @Test
    public void testFileDownload() {
        given()
                .when()
                .get("http://localhost:8080/fileDownload")
                .then()
                .statusCode(200)
                .log().all();
    }
}
