package com.fundation.cucumber;

import com.fundation.cucumber.FizzBuzz;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.Assert;

public class FizzBuzzStepdefs {
    FizzBuzz fizzBuzz;
    String result;

    @Given("Create a FizzBuzz game play")
    public void createAFizzBuzzGamePlay() {
        fizzBuzz = new FizzBuzz();
    }

    @When("I play with number {int}")
    public void iPlayWithNumber(int number) {
        result = fizzBuzz.play(number);
    }

    @Then("The result is {string}")
    public void theResultIs(String resultString) {
        Assert.assertEquals(result,resultString);
    }
}
