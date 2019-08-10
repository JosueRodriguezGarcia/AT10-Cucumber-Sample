package com.fundation.cucumberRestAssured;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.AuthenticationSpecification;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.specification.ProxySpecification.host;
import static org.hamcrest.Matchers.equalTo;

/**
 * @author Alejandro Sánchez Luizaga
 * @version 1.0
 */
public class CreateGistStepdefs {
    private Response response;
    private ValidatableResponse json;
    private RequestSpecification request;
    private AuthenticationSpecification authentication;

    private String GIST_ENDPOINT = "https://api.github.com/gists";


    //RestAssured.proxy = host("172.31.90.162").withPort(8080);

    @Given("a user is registered at GitHub")
    public void a_user_is_registered_at_GitHub () {
        request = given().auth().preemptive().basic("alszla","misuperpassword123");
    }

    @And("user has content")
    public void user_has_content () {
        request = request.body("{\"files\": {\"previos.txt\": {\"content\": \"BDD + IntelliJ\"}}}");
    }

    @When("a user makes a post request")
    public void a_user_makes_a_post_request () {
        RestAssured.proxy = host("172.31.90.162").withPort(8080);
        response = request.when().post(GIST_ENDPOINT);
        System.out.println("Response: ");
        response.prettyPrint();
    }

    @Then("the create status code is (\\d+)")
    public void verify_creation_status_code(int statusCode){
        json = response.then().statusCode(statusCode);
    }

    @And("response includes the following")
    public void response_equals_to(Map<String,String> responseFields){
        for (Map.Entry<String, String> field : responseFields.entrySet()) {
            if(StringUtils.isNumeric(field.getValue())){
                json.body(field.getKey(), equalTo(Integer.parseInt(field.getValue())));
            }
            else{
                json.body(field.getKey(), equalTo(field.getValue()));
            }
        }
    }
}
