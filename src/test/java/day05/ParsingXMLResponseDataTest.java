package day05;

import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ParsingXMLResponseDataTest {
    @Test
    public void testParseXmlResponseData() {

//        given()
//                .when()
//                .get("http://localhost:8080/day05")
//                .then()
//                .statusCode(200)
//                .header("Content-Type", "application/xml")
//                //.contentType(ContentType.XML)
//                .body("TravelerinformationResponse.page", equalTo(1))
//                .body("TravelerinformationResponse.travelers.Travelerinformation[0].name", equalTo("John Doe"));

        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get("http://localhost:3000/store");

//        Assert.assertEquals(response.getStatusCode(), 200);
//        Assert.assertEquals(response.getHeader("content-type"),  "application/xml");

        String sayfaNumarasi = response.xmlPath().get("TravelerinformationResponse.page").toString();
        //String sayfaNumarasi = response.xmlPath().getString("TravelerinformationResponse.page"); // bu daha modern yol

        Assert.assertEquals(sayfaNumarasi, "1");

        String travelerName = response.xmlPath().get("TravelerinformationResponse.travelers.Travelerinformation[0].name").toString();
        Assert.assertEquals(travelerName, "John");

    }

    @Test
    public void testParseXmlResponseBody() {

        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get("http://localhost:3000/store");

        XmlPath xml = new XmlPath(response.asString());

        List<String> list = xml.getList("TravelerinformationResponse.travelers.Travelerinformation");

        Assert.assertEquals(list.size(), 10);


        List<String> nameList = xml.getList("TravelerinformationResponse.travelers.Travelerinformation.name");
        Assert.assertTrue(nameList.stream().anyMatch(name -> name.equals("John")));
    }
}
