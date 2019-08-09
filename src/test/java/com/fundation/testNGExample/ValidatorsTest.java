package com.fundation.testNGExample;

//import org.junit.Test;

//import static junit.framework.TestCase.assertFalse;
//import static junit.framework.TestCase.assertTrue;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.*;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static org.testng.Assert.*;

public class ValidatorsTest {

    /**
     * This unit test verify the path is correct.
     */
    @BeforeMethod
    public void exampleBM(){
        System.out.println("Before Method");
    }

    @BeforeSuite
    public void exampleBS(){
        System.out.println("BEFORE SUITE");
    }

    @Test (priority = 2)
    public void testCaseOne() {
        System.out.println("*** test case one started ***");
        Assert.assertEquals(5, 5, "First hard assert failed");
        System.out.println("hard assert success....");
        Assert.assertTrue("Hello".equals("hello"), "Second hard assert failed");
        System.out.println("*** test case one executed successfully ***");
    }

    @Test (priority = 1)
    public void testCaseTwo() {
        SoftAssert softAssert = new SoftAssert();
        System.out.println("*** test case two started ***");
        softAssert.assertEquals("Hello", "Hello", "First soft assert failed");
        System.out.println("first soft assert success....");
        softAssert.assertTrue("Hello".equals("hello"), "Second soft assert failed");
        softAssert.assertTrue("Welcome".equals("welcomeeee"), "Third soft assert failed");
        System.out.println("*** test case two executed successfully ***");
        softAssert.assertAll();
    }

    @AfterMethod
    public void afterMethod(){
        System.out.println("After method");
    }

    @AfterSuite
    public void afterSuite(){
        System.out.println("AFTER SUITE");
    }
}
