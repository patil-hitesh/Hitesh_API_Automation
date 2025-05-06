package org.petstore.NegativePetStepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import static org.hamcrest.Matchers.is;

import static org.hamcrest.MatcherAssert.assertThat;

public class PetNegativeStepDefinitions {

	private Response response;

	@When("I send a POST request to {string} with invalid body")
	public void i_send_a_post_request_with_invalid_body(String path) {
	    // Completely invalid: missing required fields, or wrong types, or bad format
	    String invalidBody = "{}";  // Missing required fields like id, name, category
	    response = SerenityRest.given()
	            .header("Content-Type", "application/json")
	            .body(invalidBody)
	            .post(path);
	}

	@When("I send a GET request to {string}")
	public void i_send_a_get_request(String path) {
		response = SerenityRest.get(path);
	}

	@When("I send a PUT request to {string} with incomplete body")
	public void i_send_a_put_request_with_incomplete_body(String path) {
	    // Missing required id field or using wrong type
	    String incompleteBody = "{ \"id\": \"wrong_type\", \"name\": \"Fluffy\" }";  // Invalid id
	    response = SerenityRest.given()
	            .header("Content-Type", "application/json")
	            .body(incompleteBody)
	            .put(path);
	}

	@When("I send a DELETE request to {string}")
	public void i_send_a_delete_request(String path) {
		response = SerenityRest.delete(path);
	}

	@Then("the response status should be {int}")
	public void the_response_status_should_be(int expectedStatus) {
		assertThat("Expected status code does not match", response.statusCode(), is(expectedStatus));
	}
	
	@Then("the response should contain error message {string}")
	public void the_response_should_contain_error_message(String expectedMessage) {
	    String responseBody = response.getBody().asString();
	    assertThat("Expected error message not found", responseBody.contains(expectedMessage));
	}

}
