package com.auto.net.stepdefinations.common;

import com.auto.net.helpers.CustomLogFilter;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.Scenario;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.Data;
import org.openqa.selenium.WebDriver;
import org.springframework.stereotype.Component;

@Data
@Component
public class StepData {
    private Response response;
    private CustomLogFilter customLogFilter;
    private ObjectMapper objectMapper;
    private Scenario scenario;
    private WebDriver driver;
    private RequestSpecification requestSpecification;

    public StepData() {
        objectMapper = new ObjectMapper();
    }

    public void logRequestAndResponse() {
        if (this.customLogFilter.responseBuilderLogs != null) {
            scenario.log("\n" + "API Request : " + this.customLogFilter.getRequestBuilder() +
                    "\n" + "API Response : " + this.customLogFilter.getResponseBuilder());
        }
    }
}
