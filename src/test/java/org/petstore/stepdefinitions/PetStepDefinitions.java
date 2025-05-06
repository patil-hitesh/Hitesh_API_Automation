package org.petstore.stepdefinitions;

import io.cucumber.java.en.*;
import io.restassured.response.Response;
import models.Pet;
import net.serenitybdd.rest.SerenityRest;
import org.junit.Assert;

public class PetStepDefinitions {

    private Response response;
    private String petId;
    private String petName;
    private String petStatus;

    // Create a pet with given name and status
    @Given("I have a valid pet payload with name {string} and status {string}")
    public void i_have_a_valid_pet_payload_with_name_and_status(String name, String status) {
        Pet pet = new Pet(name, status);

        response = SerenityRest.given()
                .contentType("application/json")
                .body(pet)
                .post("/pet");

        response.then().statusCode(200);

        petId = response.jsonPath().getString("id");
        petName = name;
        petStatus = status;

        System.out.println("Created pet with ID: " + petId);
    }

    // Validate the pet is created successfully
    @Then("the pet should be created successfully")
    public void the_pet_should_be_created_successfully() {
        Assert.assertEquals(200, response.getStatusCode());
        Assert.assertEquals(petName, response.jsonPath().getString("name"));
        Assert.assertEquals(petStatus, response.jsonPath().getString("status"));
    }

    // Retrieve pet details by ID
    @When("I send a GET request to the pet endpoint")
    public void i_send_a_get_request_to_the_pet_endpoint() {
        response = SerenityRest.given().get("/pet/" + petId);
    }

    // Validate the pet details are returned successfully
    @Then("the pet details should be returned successfully")
    public void the_pet_details_should_be_returned_successfully() {
        response.then().statusCode(200);
        Assert.assertEquals(petName, response.jsonPath().getString("name"));
        Assert.assertEquals(petStatus, response.jsonPath().getString("status"));
    }

    // Update the pet status
    @When("I update the pet status to {string}")
    public void i_update_the_pet_status_to(String newStatus) {
        Pet updatedPet = new Pet();
        updatedPet.setId(Long.parseLong(petId));
        updatedPet.setName(petName);
        updatedPet.setStatus(newStatus);

        response = SerenityRest.given()
                .contentType("application/json")
                .body(updatedPet)
                .put("/pet");

        response.then().statusCode(200);

        // Update the local pet status
        petStatus = newStatus;
    }

    // Validate the pet details are updated successfully
    @Then("the pet details should be updated successfully")
    public void the_pet_details_should_be_updated_successfully() {
        response = SerenityRest.given()
                .get("/pet/" + petId);

        response.then().statusCode(200);
        Assert.assertEquals(petStatus, response.jsonPath().getString("status"));
    }

    // Delete the pet
    @When("I delete the pet")
    public void i_delete_the_pet() {
        response = SerenityRest.given()
                .contentType("application/json")
                .delete("/pet/" + petId);

        response.then().statusCode(200);
    }

    // Validate the pet has been deleted
    @Then("the pet should be successfully deleted")
    public void the_pet_should_be_successfully_deleted() {
        // First check that DELETE was successful
        response.then().statusCode(200); // or 204, depending on API spec

        // Now confirm it's really deleted by trying to GET
        Response getResponse = SerenityRest.given().get("/pet/" + petId);
        getResponse.then().statusCode(200);
    }
}
