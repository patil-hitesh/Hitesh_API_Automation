package com.petstore.NegativeRunner;
import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
    features = "src/test/resources/features/PetNegativeScenario/petstore_negative.feature",		
    glue = {"org.petstore.NegativePetStepDefinitions",
    		"org.petstore.hooks"},
    plugin = {"pretty",
    		"html:target/site/serenity/report.html", 
            "json:target/site/serenity/report.json"}
)
public class PetNegativeTestRunner {

}
