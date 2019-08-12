package com.fundation.restassured;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static io.restassured.http.ContentType.JSON;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

/**
 * TestClass class.
 *
 * @author Raul Choque
 * @version 0.0.1
 */
public class TestRestAssured {

    private static RequestSpecification requestSpec;

    @BeforeClass
    public static void createRequestSpecification() {
        requestSpec = new RequestSpecBuilder().
                setBaseUri("https://zippopotam.us").
                build();

    }

    @Test
    public void requestUsZipCode90210_checkStatusCode_expectedHttp200() {
        given().
                spec(requestSpec).
                when().
                get("/us/90210").
                then().
                assertThat().
                statusCode(200);
    }

    private static ResponseSpecification responseSpec;

    @BeforeClass
    public static void createResponseSpecification() {
        responseSpec = new ResponseSpecBuilder().
                expectStatusCode(200).
                expectContentType(JSON).
                build();
    }

    @Test
    public void requestUsZipCode90210_checkPlaceNameInResponseBody_expectBeberlyHills() {
        given().
                spec(requestSpec).
                when().
                get("/us/90210").
                then().
                spec(responseSpec).
                and().
                assertThat().
                body("places[0].'place name'", equalTo("Beverly Hills"));
    }

    @Test
    public void requestUsZipCode90210_checkContentType_expectApplicationJson() {
        given().
                when().
                get("https://zippopotam.us/us/90210").
                then().
                assertThat().
                contentType(JSON);
    }

    @Test
    public void requestUsZipCode90210_logRequestAndResponseRequest() {
        given().
                log().all().
                when().
                get("https://zippopotam.us/us/90210").
                then().
                log().body();
    }

    @Test
    public void test_NumberOfCircuitsFor2017Season_ShouldBe20() {

        given().
                when().
                get("http://ergast.com/api/f1/2017/circuits.json").
                then().
                statusCode(200);
//                assertThat().
//                body("MRData.CircuitTable.Circuits.circuitId",hasSize(20));
    }

    @Test
    public void test_getAlbumsById1StatusCode_ShoudBe200() {
        given().
                when().
                get("https://jsonplaceholder.typicode.com/albums/1").
                then().
                assertThat().
                statusCode(200);
    }

    @Test
    public void test_DeleteAlbumsByID1_StatusCode_ShoudBe200() {
        Response response =
                when().
                        get("https://jsonplaceholder.typicode.com/Albums/1");
        Assert.assertEquals(200, response.statusCode());
    }

    @Test
    public void test_GetAlbumsById1ValidateData_ShoudBe1Andquidem_molestiae_enim() {

        when().
                get("https://jsonplaceholder.typicode.com/albums/1").
                then().
                body("userId", equalTo(1)).
                body("title", equalTo("quidem molestiae enim"));
    }

    @Test
    public void test_PostAlbumsWithTitleTestAlbum_ShoudBeTitle_TestAlbum() {
        Map<String, Object> mymap = new HashMap<String, Object>();
        mymap.put("title", "TestAlbum");

        given().
                contentType(ContentType.JSON).
                body(mymap).
                when().
                post("https://jsonplaceholder.typicode.com/albums").

                then().
                statusCode(201);
    }

    @Test
    public void test_PUTAlbumsById1_ShoudBeTitle_TestAlbumUPDATE() {
        Map<String, Object> mymap = new HashMap<String, Object>();
        mymap.put("title", "Update TestAlbum");

        given().
                contentType(JSON).
                body(mymap).
                when().
                put("https://jsonplaceholder.typicode.com/albums/1").

                then().
                body("title", equalTo("Update TestAlbum"));
    }

    @Test
    public void test_GETAlbumsById1_ShouldBeTitle_quidem_molestiae_enim() {
        Album album = get("https://jsonplaceholder.typicode.com/albums/1").
                as(Album.class);
        Assert.assertEquals("quidem molestiae enim", album.getTitle());
    }

    @Test
    public void test_GETPhotosById1_ShouldBeTitle_quidem_molestiae_enim() {
        Photos album = get("https://jsonplaceholder.typicode.com/photos/1").
                as(Photos.class);
        Assert.assertEquals("accusamus beatae ad facilis cum similique qui sunt", album.getTitle());
    }

    @Test
    public void test_DeletePhotosByID1_StatusCode_ShoudBe200() {
        Response response =
                when().
                        get("https://jsonplaceholder.typicode.com/photos/1");
        Assert.assertEquals(200, response.statusCode());
    }
}

