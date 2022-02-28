package Testcases;

import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;

public class TC_VideoGame_API {
    @Test
    public void test_getAllVideoGame(){
        given()
        .when()
                .when("URL")
                .then()
                .statusCode(200);
    }

    @Test
    public void test_addNewVideoGame(){
        HashMap data = new HashMap();
        data.put("id", "100");
        data.put("name", "Spider-Man");
        data.put("releaseDate", "2019_09_20");

       Response res = given()
                .contentType("application/json")
                .body(data)
                .when()
                .port("URL")
                .then()
                .statusCode(200)
                .log().body()
                .extract.response();
      String jsonString = res.asString();
        Assert.assertEquals(jsonString.contains("Records added successuflly"),true);
    }

    @Test
    public void test_getVideoGame(){
        given()
                .when()
                .get("URL/100")
                .then()
                .statusCode(200)
                .body("videoGame.id", equalTo("100"))
                .body("videoGame.name",equalTo("Spider_Man") );
    }

    @Test
    public void test_UpdateVideoGame(){
        HashMap data = new HashMap();
        data.put("id", "100");
        data.put("name", "Pacman");

        given()
                .contentType("application/json")
                .body(data)
                .when()
                .put("URL/100")
                .then()
                .statusCode(200)
                .log().body()
                .body("videoGame.id", equalTo("100"))
                .body("videoGame.name",equalTo("Packman"));

    }

    @Test
    public void test_DeleteVideoGame(){
       Response res = given()
                .when()
                .delete("URL/100")
                .then()
                .statusCode(200)
                .log().body().extract().response();

       String jsonString = res.toString();

       Assert.assertEquals(jsonString.contains("Recods deleted successfully"), true );
    }
}
