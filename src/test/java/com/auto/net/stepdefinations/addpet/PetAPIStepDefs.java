package com.auto.net.stepdefinations.addpet;

import com.auto.net.helpers.APIHelper;
import com.auto.net.models.Addpet;
import com.auto.net.models.AddpetList;
import com.auto.net.models.Tag;
import com.auto.net.stepdefinations.common.CommonSteps;
import com.auto.net.stepdefinations.common.StepData;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;
import org.apache.hc.client5.http.auth.Credentials;
import org.apache.http.HttpStatus;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

@Slf4j
public class PetAPIStepDefs {


    private RequestSpecification requestSpecification;
    private final ObjectMapper objectMapper;
    public Addpet addpet;
    private APIHelper apiHelper;
    public Long Generatedpetid;

    @Autowired
    private StepData stepData;

    @Autowired
    private CommonSteps commonSteps;


    public PetAPIStepDefs() {
        this.objectMapper = new ObjectMapper();
        this.apiHelper = new APIHelper();
    }


    @Given("Prepare update Add Pet request for below data")
    public void createPetAPI(DataTable table) throws IOException {
        Map<String, String> dataTableMap = table.asMap(String.class, String.class);
        var inputStream = Credentials.class.getResourceAsStream("/testdata/Addpet.json");
        addpet = stepData.getObjectMapper().readValue(inputStream, Addpet.class);
        addpet.setId(Long.parseLong(dataTableMap.get("id")));
        addpet.category.setId(Long.parseLong(dataTableMap.get("categoryid")));
        addpet.category.setName(dataTableMap.get("categoryname"));
        addpet.setName(dataTableMap.get("name"));
        addpet.photoUrls(dataTableMap.get("photourl"));
        addpet.setStatus(dataTableMap.get("status"));
    }


    @Given("Update Tags to Add Pet request with below data")
    public void addtags(DataTable table) throws JsonProcessingException {
        var tagsList = objectMapper.convertValue(table.asMaps(String.class, String.class), Tag[].class);
        addpet.setTags(Arrays.asList(tagsList));
        var body = stepData.getObjectMapper().writeValueAsBytes(addpet);
        commonSteps.setRequestBody(body);
    }

    @Then("We Validate the response code and response in AddPet response")
    public void validateThePetResponse() throws IOException {
        var actual = stepData.getResponse().body().as(Addpet.class);
        Assert.assertEquals(HttpStatus.SC_OK, stepData.getResponse().statusCode());
        Assert.assertEquals("id is not matching", addpet.category.getId(), actual.category.getId());
        Assert.assertEquals("name is not matching", addpet.category.getName(), actual.category.getName());
        Assert.assertEquals("status is not matching", addpet.getStatus(), actual.getStatus());
        Assert.assertEquals("name is not matching", addpet.getName(), actual.getName());
        Generatedpetid = actual.getId();
    }

    @Then("We Validate the response code and response in Delete pet response")
    public void validateTheDeletePetResponse() throws IOException {
        var actual = stepData.getResponse().body();
        Assert.assertEquals(HttpStatus.SC_OK, stepData.getResponse().statusCode());
    }

    @Then("We Validate the Record Not found response code and response in Find pet by id response")
    public void validateTheFindPetResponseNotFound() throws IOException {
        var actual = stepData.getResponse().body();
        Assert.assertEquals(HttpStatus.SC_NOT_FOUND, stepData.getResponse().statusCode());
    }

    @Then("We Update the request of Pet")
    public void updatePet(DataTable table) throws IOException {
        Map<String, String> dataTableMap = table.asMap(String.class, String.class);
        addpet.setId(Generatedpetid);
        addpet.setStatus(dataTableMap.get("updated_status"));
        addpet.setName(dataTableMap.get("updated_name"));
        var body = stepData.getObjectMapper().writeValueAsBytes(addpet);
        commonSteps.setRequestBody(body);
    }

    @Then("We Validate the response code and response of Upload image")
    public void verifyUploadImage() throws IOException {
        var actual = stepData.getResponse().body();
        Assert.assertEquals(HttpStatus.SC_OK, stepData.getResponse().statusCode());
    }

    @Then("We Validate the response code and response in find by pet response")
    public void validateTheFindByPetResponse() throws IOException {
        var actual = stepData.getResponse().body().as(AddpetList[].class);
        Assert.assertEquals(HttpStatus.SC_OK, stepData.getResponse().statusCode());
        Assert.assertTrue("All status of pet is not returned", Arrays.stream(actual).count() > 1);
    }

    @When("Send {string} HTTP post request")
    public void sendHTTPPostRequest(String endPoint) {
        apiHelper.sendHTTPPostRequest(endPoint);
    }

}
