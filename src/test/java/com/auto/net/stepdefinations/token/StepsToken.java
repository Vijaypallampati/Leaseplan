package com.auto.net.stepdefinations.token;

import com.auto.net.config.AppProps;
import com.auto.net.helpers.APIHelper;
import com.auto.net.stepdefinations.addpet.PetAPIStepDefs;
import com.auto.net.stepdefinations.common.CommonSteps;
import com.auto.net.stepdefinations.common.StepData;
import com.auto.net.stepdefinations.store.StoreAPIStepDefs;
import com.auto.net.stepdefinations.user.UserAPIStepDefs;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;

public class StepsToken {
    @Autowired
    private APIHelper apiHelper;

    @Autowired
    private StepData stepData;

    @Autowired
    private AppProps appProps;

    @Autowired
    private CommonSteps commonSteps;

    @Autowired
    private PetAPIStepDefs petAPIStepDefs;

    @Autowired
    private UserAPIStepDefs userAPIStepDefs;

    @Autowired
    private StoreAPIStepDefs storeAPIStepDefs;

    public StepsToken() {
        stepData = new StepData();
    }

    @When("Send post {string} request")
    public void sendPostRequest(String endPoint) {
        apiHelper.sendHTTPPostRequest(endPoint);
    }

    @When("Send post {string} request with Image {string}")
    public void sendPostRequestImage(String endPoint, String Image) {
        apiHelper.sendHTTPPostRequestwithImage(endPoint, petAPIStepDefs.Generatedpetid, Image);
    }

    @When("Send get {string} request")
    public void sendGetRequest(String endPoint) {
        apiHelper.sendHTTPGetRequest(endPoint);
    }

    @When("Send get {string} request with Parameter name as {string} and Parameter Value as {string}")
    public void sendGetRequestWithQueryParameter(String endPoint, String Parametername, String Parametervalue) {
        apiHelper.sendHTTPGetRequestWithQueryParameter(endPoint, Parametername, Parametervalue);
    }

    @When("Send get {string} request with username and password")
    public void sendGetRequestWithQueryParameter(String endPoint) {
        apiHelper.sendHTTPGetRequestWithMultipleQueryParameter(endPoint, "username", userAPIStepDefs.addUser.getUsername(), "password",userAPIStepDefs.addUser.getPassword());
    }

    @When("Send get {string} request with Petid")
    public void sendGetRequestWithParameterforPetID(String endPoint) {
        apiHelper.sendHTTPGetRequestWithParameter(endPoint, petAPIStepDefs.Generatedpetid);
    }

    @When("Send get {string} request with username {string}")
    public void sendGetRequestWithParameterWithUsername(String endPoint, String username) {
        apiHelper.sendHTTPGetRequestWithParameter(endPoint, username);
    }

    @When("Send put {string} request with username {string}")
    public void sendPutRequestWithParameterWithUsername(String endPoint, String username) {
        apiHelper.sendHTTPPutRequestWithParameter(endPoint, username);
    }


    @When("Send delete {string} request with Petid")
    public void sendDeleteRequestWithParameterforPetID(String endPoint) {
        apiHelper.sendHTTPDeleteRequestWithParameter(endPoint, petAPIStepDefs.Generatedpetid);
    }

    @When("Send delete {string} request for Username")
    public void sendDeleteRequestWithParameterforUsername(String endPoint) {
        apiHelper.sendHTTPDeleteRequestWithParameter(endPoint, userAPIStepDefs.addUser.getUsername());
    }

    @When("Send get {string} request with Orderid")
    public void sendGetRequestWithParameterforOrderID(String endPoint) {
        apiHelper.sendHTTPGetRequestWithParameter(endPoint, storeAPIStepDefs.GeneratedOrderid);
    }

    @When("Send delete {string} request with Orderid")
    public void sendDeleteRequestWithParameterforOrderID(String endPoint) {
        apiHelper.sendHTTPDeleteRequestWithParameter(endPoint, storeAPIStepDefs.GeneratedOrderid);
    }

    @When("Send put {string} request")
    public void sendPutRequest(String endPoint) {
        apiHelper.sendHTTPPutRequest(endPoint);
    }

    @When("Send delete {string} request")
    public void sendDeleteRequest(String endPoint) {
        apiHelper.sendHTTPDeleteRequest(endPoint);
    }

}
