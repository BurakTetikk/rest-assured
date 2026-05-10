package day04;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ParsingJsonResponseDataTest {
    @Test
    public void testParseJsonResponseData() {
//        given()
//                .contentType(ContentType.JSON)
//                .when()
//                .get("http://localhost:3000/store")
//                .then()
//                .statusCode(200)
//                .contentType(ContentType.JSON)
//                .body("book[2].category", equalTo("fiction"));

        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get("http://localhost:3000/store");

//        Assert.assertEquals(response.getStatusCode(), 200);
//        Assert.assertEquals(response.getHeader("content-type"),  "application/json");

        String author = response.jsonPath().get("book[2].author").toString();
        Assert.assertEquals(author, "Herman Melville");

    }

    @Test
    public void testParseJsonResponseDataWithJSONObject() {
        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get("http://localhost:3000/store");


        JSONObject jsonObject = new JSONObject(response.asString());

        JSONArray jsonArray = jsonObject.getJSONArray("book");

//        for (int i = 0; i < jsonArray.length(); i++) {
//            //String title = jsonArray.getJSONObject(i).get("title").toString();
//            String title = jsonArray.getJSONObject(i).getString("title");
//            System.out.println(title);
//        }


//        boolean isExists = false;
//        for (int i = 0; i < jsonArray.length(); i++) {
//            String title = jsonArray.getJSONObject(i).getString("title");
//
//            if (title.equals("Sword of Honour")) {
//                isExists = true;
//                break;
//            }
//        }
//        Assert.assertTrue(isExists);


        Double totalPrice = 0.0;
        for (int i = 0; i < jsonArray.length(); i++) {
            double price = jsonArray.getJSONObject(i).getDouble("price");
            totalPrice += price;
        }
        System.out.println(totalPrice);

        Assert.assertTrue(totalPrice > 50);



    }


}
