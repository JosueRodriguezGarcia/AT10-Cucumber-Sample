package com.fundation.cucumberRestAssured;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

/**
 * @author Alejandro Sánchez Luizaga
 * @version 1.0
 */
public class RetrieveGistStepdefs {
    private Response response;
    private ValidatableResponse json;
    private RequestSpecification request;

    private String GIST_ENDPOINT = "https://api.github.com/gists";

    @Given("a gist exists since (.*)")
    public void a_gist_exists_since(String time){
        request = given().param("since", time);
    }

    @When("a user retrieves gist by time")
    public void a_user_retrieves_gist_by_time(){
        response = request.when().get(GIST_ENDPOINT);
        System.out.println("response: " + response.prettyPrint());
    }

    @Then("the status code is (\\d+)")
    public void verify_status_code(int statusCode){
        json = response.then().statusCode(statusCode);
    }
}
