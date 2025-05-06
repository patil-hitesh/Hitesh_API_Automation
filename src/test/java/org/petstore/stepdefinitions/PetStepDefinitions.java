package org.petstore.stepdefinitions;

import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import io.cucumber.java.en.*;
import models.Pet;
import org.junit.Assert;

public class PetStepDefinitions {

    private Response response;
    private String petId;
    private String petName;
    private String petStatus;

    @Given("I have a valid pet payload with name {string} and status {string}")
    public void i_have_a_valid_pet_payload_with_name_and_status(String name, String status) {
        Pet pet = new Pet();
        pet.setName(name);
        pet.setStatus(status);

        response = SerenityRest.given()
            .contentType("application/json")
            .body(pet)
            .post("/pet");

        response.then().statusCode(200);

        petId = response.jsonPath().getString("id");
        petName = name;
        petStatus = status;
    }
    
    @Then("the pet should be created successfully")
    public void the_pet_should_be_created_successfully() {
        response.then().statusCode(200);

        
        String id = response.jsonPath().getString("id");
        String name = response.jsonPath().getString("name");
        String status = response.jsonPath().getString("status");

        Assert.assertNotNull("Pet ID should not be null", id);
        Assert.assertEquals("Pet name should match", petName, name);
        Assert.assertEquals("Pet status should match", petStatus, status);
    }


    @When("I send a POST request to /pet")
    public void i_send_a_post_request_to_pet() {
      
        response.then().statusCode(200);
    }

    @When("I send a GET request to {string}")
    public void i_send_a_get_request_to(String endpoint) {
        Assert.assertNotNull("Pet ID must not be null before sending GET request", petId);
        endpoint = endpoint.replace("{petId}", petId);

        response = SerenityRest.given()
            .contentType("application/json")
            .get(endpoint);
    }


    @Then("the pet details should be returned successfully")
    public void the_pet_details_should_be_returned_successfully() {
        response.then().statusCode(200);

        String returnedName = response.jsonPath().getString("name");
        String returnedStatus = response.jsonPath().getString("status");

        Assert.assertEquals("Pet name should match", petName, returnedName);
        Assert.assertEquals("Pet status should match", petStatus, returnedStatus);
    }

    @When("I send a PUT request to /pet")
    public void i_send_a_put_request_to_pet() {
        petStatus = "sold"; // Simulate status change

        Pet updatedPet = new Pet();
        updatedPet.setId(Long.parseLong(petId));
        updatedPet.setName(petName);
        updatedPet.setStatus(petStatus);

        response = SerenityRest.given()
            .contentType("application/json")
            .body(updatedPet)
            .put("/pet");
    }

    @Then("the pet details should be updated successfully")
    public void the_pet_details_should_be_updated_successfully() {
        response.then().statusCode(200);

        String returnedStatus = response.jsonPath().getString("status");
        Assert.assertEquals("Pet status should be updated to 'sold'", "sold", returnedStatus);
    }

    @When("I send a DELETE request to {string}")
    public void i_send_a_delete_request_to(String endpoint) {
        endpoint = endpoint.replace("{petId}", petId);

        response = SerenityRest.given()
            .contentType("application/json")
            .delete(endpoint);
    }

    @Then("the pet should be successfully deleted")
    public void the_pet_should_be_successfully_deleted() {
        response.then().statusCode(200);
    }
}
