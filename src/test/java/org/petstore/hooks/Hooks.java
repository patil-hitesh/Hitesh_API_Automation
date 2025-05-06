package org.petstore.hooks;

import io.cucumber.java.Before;
import io.restassured.RestAssured;

public class Hooks {

    @Before
    public void setup() {
        RestAssured.baseURI = "https://petstore.swagger.io/v2";
    }
}
