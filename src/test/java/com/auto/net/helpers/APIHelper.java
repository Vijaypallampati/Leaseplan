package com.auto.net.helpers;

import com.auto.net.stepdefinations.common.StepData;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import static io.restassured.RestAssured.given;

@Component
public class APIHelper {

    @Autowired
    private StepData stepData;

    @Autowired
    private Environment environment;

    public void sendHTTPPostRequest(String endPoint) {
        final var builder = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .addFilter(stepData.getCustomLogFilter())
                .addRequestSpecification(stepData.getRequestSpecification());
        stepData.setResponse(given(builder.build()).when().post(environment.getProperty("endpoint." + endPoint)));
        stepData.logRequestAndResponse();
    }

    public void sendHTTPPostRequestwithImage(String endPoint, Long petid, String Image) {
        final var builder = new RequestSpecBuilder()
                .setContentType("multipart/form-data")
                .addMultiPart("file",Image)
                .addFilter(stepData.getCustomLogFilter())
                .addRequestSpecification(stepData.getRequestSpecification());
        stepData.setResponse(given(builder.build()).when().post(environment.getProperty("endpoint." + endPoint) + "/" + petid + "/uploadImage"));
        stepData.logRequestAndResponse();
    }

    public void sendHTTPGetRequest(String endPoint) {
        final var builder = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .addFilter(stepData.getCustomLogFilter())
                .addRequestSpecification(stepData.getRequestSpecification());
        stepData.setResponse(given(builder.build()).when().get(environment.getProperty("endpoint." + endPoint)));
        stepData.logRequestAndResponse();
    }

    public void sendHTTPGetRequestWithParameter(String endPoint, Long Parameter) {
        final var builder = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .addFilter(stepData.getCustomLogFilter())
                .addRequestSpecification(stepData.getRequestSpecification());
        stepData.setResponse(given(builder.build()).when().get(environment.getProperty("endpoint." + endPoint) +"/" + Parameter));
        stepData.logRequestAndResponse();
    }

    public void sendHTTPGetRequestWithParameter(String endPoint, String Parameter) {
        final var builder = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .addFilter(stepData.getCustomLogFilter())
                .addRequestSpecification(stepData.getRequestSpecification());
        stepData.setResponse(given(builder.build()).when().get(environment.getProperty("endpoint." + endPoint) +"/" + Parameter));
        stepData.logRequestAndResponse();
    }

    public void sendHTTPDeleteRequestWithParameter(String endPoint, Long Parameter) {
        final var builder = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .addFilter(stepData.getCustomLogFilter())
                .addRequestSpecification(stepData.getRequestSpecification());
        stepData.setResponse(given(builder.build()).when().delete(environment.getProperty("endpoint." + endPoint) +"/" + Parameter));
        stepData.logRequestAndResponse();
    }

    public void sendHTTPDeleteRequestWithParameter(String endPoint, String Parameter) {
        final var builder = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .addFilter(stepData.getCustomLogFilter())
                .addRequestSpecification(stepData.getRequestSpecification());
        stepData.setResponse(given(builder.build()).when().delete(environment.getProperty("endpoint." + endPoint) +"/" + Parameter));
        stepData.logRequestAndResponse();
    }

    public void sendHTTPGetRequestWithQueryParameter(String endPoint,String Parametername, String Parametervalue) {
        final var builder = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .addQueryParam(Parametername,Parametervalue)
                .addFilter(stepData.getCustomLogFilter())
                .addRequestSpecification(stepData.getRequestSpecification());
        stepData.setResponse(given(builder.build()).when().get(environment.getProperty("endpoint." + endPoint)));
        stepData.logRequestAndResponse();
    }

    public void sendHTTPGetRequestWithMultipleQueryParameter(String endPoint,String Parametername1, String Parametervalue1, String Parametername2, String Parametervalue2) {
        final var builder = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .addQueryParam(Parametername1,Parametervalue1)
                .addQueryParam(Parametername2,Parametervalue2)
                .addFilter(stepData.getCustomLogFilter())
                .addRequestSpecification(stepData.getRequestSpecification());
        stepData.setResponse(given(builder.build()).when().get(environment.getProperty("endpoint." + endPoint)));
        stepData.logRequestAndResponse();
    }

    public void sendHTTPPutRequest(String endPoint) {
        final var builder = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .addFilter(stepData.getCustomLogFilter())
                .addRequestSpecification(stepData.getRequestSpecification());
        stepData.setResponse(given(builder.build()).when().put(environment.getProperty("endpoint." + endPoint)));
        stepData.logRequestAndResponse();
    }

    public void sendHTTPPutRequestWithParameter(String endPoint, String Parameter) {
        final var builder = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .addFilter(stepData.getCustomLogFilter())
                .addRequestSpecification(stepData.getRequestSpecification());
        stepData.setResponse(given(builder.build()).when().put(environment.getProperty("endpoint." + endPoint) +"/" + Parameter));
        stepData.logRequestAndResponse();
    }

    public void sendHTTPDeleteRequest(String endPoint) {
        final var builder = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .addFilter(stepData.getCustomLogFilter())
                .addRequestSpecification(stepData.getRequestSpecification());
        stepData.setResponse(given(builder.build()).when().delete(environment.getProperty("endpoint." + endPoint)));
        stepData.logRequestAndResponse();
    }
}
