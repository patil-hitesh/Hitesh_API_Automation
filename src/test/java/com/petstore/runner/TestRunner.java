package com.petstore.runner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
    plugin = {
        "pretty",
        "html:target/site/serenity/report.html", 
        "json:target/site/serenity/report.json"
    },
    publish = true, 
    features = "src/test/resources/features/petStore", 
    glue = {
        "org.petstore.stepdefinitions", 
        "org.petstore.hooks"
    }
)
public class TestRunner {}
