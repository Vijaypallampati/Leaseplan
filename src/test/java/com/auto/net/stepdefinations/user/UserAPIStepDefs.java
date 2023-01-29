package com.auto.net.stepdefinations.user;

import com.auto.net.helpers.APIHelper;
import com.auto.net.models.*;
import com.auto.net.stepdefinations.addpet.PetAPIStepDefs;
import com.auto.net.stepdefinations.common.CommonSteps;
import com.auto.net.stepdefinations.common.StepData;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.specification.RequestSpecification;
import org.apache.hc.client5.http.auth.Credentials;
import org.apache.http.HttpStatus;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.Map;

public class UserAPIStepDefs {
    private RequestSpecification requestSpecification;
    private final ObjectMapper objectMapper;
    private APIHelper apiHelper;
    public AddUserList addUserList;
    public AddUser addUser;
    public Long GeneratedUserid;


    @Autowired
    private StepData stepData;

    @Autowired
    private CommonSteps commonSteps;

    @Autowired
    private PetAPIStepDefs petAPIStepDefs;

    public UserAPIStepDefs() {
        this.objectMapper = new ObjectMapper();
        this.apiHelper = new APIHelper();
        this.addUserList = new AddUserList();
    }

    @Given("Prepare update Add User request for below data")
    public void addUser(DataTable table) throws IOException, JsonProcessingException {
        var inputStream = Credentials.class.getResourceAsStream("/testdata/UserList.json");
        var addUsers = stepData.getObjectMapper().readValue(inputStream, AddUser[].class);
        var userList = stepData.getObjectMapper().convertValue(table.asMaps(String.class,String.class),AddUser[].class);
        var body = stepData.getObjectMapper().writeValueAsString(userList);
        commonSteps.setRequestBody(body);
    }

    @Given("Prepare update Create user request for below data")
    public void createUser(DataTable table) throws IOException {
        Map<String, String> dataTableMap = table.asMap(String.class, String.class);
        var inputStream = Credentials.class.getResourceAsStream("/testdata/User.json");
        addUser = stepData.getObjectMapper().readValue(inputStream, AddUser.class);
        addUser.setId(Long.parseLong(dataTableMap.get("id")));
        addUser.setUsername(dataTableMap.get("username"));
        addUser.setFirstName(dataTableMap.get("firstName"));
        addUser.setLastName(dataTableMap.get("lastName"));
        addUser.setEmail(dataTableMap.get("email"));
        addUser.setPassword(dataTableMap.get("password"));
        addUser.setPhone(dataTableMap.get("phone"));
        addUser.setUserStatus(Integer.parseInt(dataTableMap.get("userStatus")));
        var body = stepData.getObjectMapper().writeValueAsBytes(addUser);
        commonSteps.setRequestBody(body);

    }
    @Then("We Validate the response code and response in create User new User response")
    public void validateTheCreateUseresponse() throws IOException {
        var actual = stepData.getResponse().body().as(AddUserResponse.class);
        Assert.assertEquals(HttpStatus.SC_OK, stepData.getResponse().statusCode());
        Assert.assertEquals("Type is not matching", actual.type,"unknown");
        Assert.assertTrue("Message does not have generated userid", (Long.parseLong(String.valueOf(actual.message))) > 0);
        GeneratedUserid = Long.parseLong(String.valueOf(actual.message));
    }

    @Then("We Validate the response code and response in createUserWithList response")
    public void validateTheCreateUserWithListResponse() throws IOException {
        var actual = stepData.getResponse().body();
        Assert.assertEquals(HttpStatus.SC_OK, stepData.getResponse().statusCode());
    }

    @Then("We Validate the response code and response Where user name Found response")
    public void validateTheCreateUserWithNotFoundResponse() throws IOException {
        var actual = stepData.getResponse().body();
        Assert.assertEquals(HttpStatus.SC_NOT_FOUND, stepData.getResponse().statusCode());
    }

    @Then("We Validate the login Successful")
    public void validateLogin() throws IOException {
        var actual = stepData.getResponse().body().as(AddUserResponse.class);;
        Assert.assertEquals(HttpStatus.SC_OK, stepData.getResponse().statusCode());
        if(String.valueOf(actual.message).contains("logged in user session:")){
            Assert.assertTrue("Login not successful",String.valueOf(actual.message).contains("logged in user session:"));
        }
    }

    @Then("We Validate the logout Successful")
    public void validateLogout() throws IOException {
        var actual = stepData.getResponse().body().as(AddUserResponse.class);;
        Assert.assertEquals(HttpStatus.SC_OK, stepData.getResponse().statusCode());
        if(String.valueOf(actual.message).contains("ok")){
            Assert.assertTrue("Logout not successful",String.valueOf(actual.message).contains("ok"));
        }
    }


}
