package com.auto.net.stepdefinations.store;

import com.auto.net.helpers.APIHelper;
import com.auto.net.models.Inventory;
import com.auto.net.stepdefinations.addpet.PetAPIStepDefs;
import com.auto.net.stepdefinations.common.CommonSteps;
import com.auto.net.stepdefinations.common.StepData;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.specification.RequestSpecification;
import com.auto.net.models.AddOrder;
import org.apache.hc.client5.http.auth.Credentials;
import org.apache.http.HttpStatus;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

public class StoreAPIStepDefs {

    private RequestSpecification requestSpecification;
    private final ObjectMapper objectMapper;
    private APIHelper apiHelper;
    public AddOrder addOrder;
    public Long GeneratedOrderid;

    @Autowired
    private StepData stepData;

    @Autowired
    private CommonSteps commonSteps;

    @Autowired
    private PetAPIStepDefs petAPIStepDefs;

    public StoreAPIStepDefs() {
        this.objectMapper = new ObjectMapper();
        this.apiHelper = new APIHelper();
    }

    @Given("Prepare update Add Order request for below data")
    public void addOrdertoPet(DataTable table) throws IOException {
        Map<String, String> dataTableMap = table.asMap(String.class, String.class);
        var inputStream = Credentials.class.getResourceAsStream("/testdata/Order.json");
        addOrder = stepData.getObjectMapper().readValue(inputStream, AddOrder.class);
        addOrder.setId(Long.parseLong(dataTableMap.get("id")));
        addOrder.setPetId(petAPIStepDefs.Generatedpetid);
        addOrder.setQuantity(Integer.parseInt(dataTableMap.get("quantity")));
        addOrder.setShipDate(new Date());
        addOrder.setStatus(dataTableMap.get("Orderstatus"));
        var body = stepData.getObjectMapper().writeValueAsBytes(addOrder);
        commonSteps.setRequestBody(body);
    }

    @Then("We Validate the response code and response in Order Pet response")
    public void validateThePetResponse() throws IOException {
        var actual = stepData.getResponse().body().as(AddOrder.class);
        Assert.assertEquals(HttpStatus.SC_OK, stepData.getResponse().statusCode());
        Assert.assertEquals("Status is not matching", addOrder.getStatus(), actual.getStatus());
        Assert.assertEquals("Pet id not matching", addOrder.getPetId(), actual.getPetId());
        Assert.assertEquals("Quantity is not matching", addOrder.getQuantity(), actual.getQuantity());
        Assert.assertEquals("ShipmentDate is not matching", addOrder.getShipDate(), actual.getShipDate());
        GeneratedOrderid = actual.getId();
    }

    @Then("We Validate the response code and response in Delete Order response")
    public void validateTheDeleteOrderResponse() throws IOException {
        var actual = stepData.getResponse().body();
        Assert.assertEquals(HttpStatus.SC_OK, stepData.getResponse().statusCode());
    }

    @Then("We Validate the Record Not found response code and response in Find Order by id response")
    public void validateTheFindPetResponseNotFound() throws IOException {
        var actual = stepData.getResponse().body();
        Assert.assertEquals(HttpStatus.SC_NOT_FOUND, stepData.getResponse().statusCode());
    }

    @Then("We Validate the response code and response for Inventory response")
    public void validateTheInventoryResponse() throws IOException {
        var actual = stepData.getResponse().body().as(Inventory.class);
        Assert.assertEquals(HttpStatus.SC_OK, stepData.getResponse().statusCode());
        Assert.assertTrue("Sold is not matching", actual.getSold() > 0);
        Assert.assertTrue("Pending is not matching", actual.getPending() > 0);
        Assert.assertTrue("Available is not matching", actual.getAvailable() > 0);
        Assert.assertTrue("Confirm is not matching", actual.getConfirm() > 0);
    }

}
