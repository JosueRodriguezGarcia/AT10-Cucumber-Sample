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

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Logger;

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
    private String username;
    private String token;
    private String gist_endpoint;
    private String proxyHost;
    private String proxyPort;

    private Logger logger = Logger.getLogger("CreateGistStepdefs.class");

    @Given("(.*) has credentials for GitHub")
    public void user_has_credentials (String user) {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("gist.properties")) {
            Properties properties = new Properties();
            properties.load(input);
            username = user;
            token = properties.getProperty(user);
            gist_endpoint = properties.getProperty("github_uri") + properties.getProperty("gist_endpoint");
            proxyHost = properties.getProperty("proxy.host");
            proxyPort = properties.getProperty("proxy.port");
        }
        catch (IOException ioex) {
            logger.warning("Unable to find gist.properties");
            ioex.printStackTrace();
        }
    }

    @And("is authenticated at GitHub")
    public void a_user_is_registered_at_GitHub () {
        request = given().auth().preemptive().basic(username,token);
    }

    @And("has (.*)")
    public void user_has_content (String jsonString) {
        request = request.body(jsonString);
    }

    @When("a user makes a post request")
    public void a_user_makes_a_post_request () {
        RestAssured.proxy = host(proxyHost).withPort(Integer.parseInt(proxyPort));
        response = request.when().post(gist_endpoint);
        logger.info("Response: ");
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
