package com.auto.net.stepdefinations.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.InputStream;

@Component
public class CommonSteps {

    @Autowired
    private StepData stepData;

    public void setRequestBody(String requestBody) {
        var requestSpec = stepData.getRequestSpecification().given().body(requestBody);
        stepData.setRequestSpecification(requestSpec);
    }

    public void setRequestBody(InputStream requestBody) {
        var requestSpec = stepData.getRequestSpecification().given().body(requestBody);
        stepData.setRequestSpecification(requestSpec);
    }

    public void setRequestBody(byte[] requestBody) {
        var requestSpec = stepData.getRequestSpecification().given().body(requestBody);
        stepData.setRequestSpecification(requestSpec);
    }
}
