package com.auto.net.helpers;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CustomLogFilter implements Filter {
    public StringBuilder requestBuilderLogs;
    public StringBuilder responseBuilderLogs;


    @Override
    public Response filter(FilterableRequestSpecification filterableRequestSpecification, FilterableResponseSpecification filterableResponseSpecification, FilterContext filterContext) {
        Response response = filterContext.next(filterableRequestSpecification, filterableResponseSpecification);
        requestBuilderLogs = new StringBuilder();
        requestBuilderLogs.append("\n");
        requestBuilderLogs.append("\n" + "Request Method : ").append(objectValidation(filterableRequestSpecification.getMethod()));
        requestBuilderLogs.append("\n" + "Request URI : ").append(objectValidation(filterableRequestSpecification.getURI()));
        requestBuilderLogs.append("\n" + "Request Header : ").append(objectValidation(filterableRequestSpecification.getHeaders()));
        requestBuilderLogs.append("\n" + "Request Body : ").append(objectValidation(filterableRequestSpecification.getBody()));
        requestBuilderLogs.append("\n");
        requestBuilderLogs.append("*********************************");
        log.info(requestBuilderLogs.toString());  //Log your request where you need it
        responseBuilderLogs = new StringBuilder();
        responseBuilderLogs.append("\n");
        responseBuilderLogs.append("\n" + "Status Code : ").append(response.getStatusCode());
        responseBuilderLogs.append("\n" + "Status Line : ").append(response.getStatusLine());
        responseBuilderLogs.append("\n" + "Response Header : ").append(response.getHeaders());
        responseBuilderLogs.append("\n" + "Response Body : ").append(response.getBody().prettyPrint());
        log.info(responseBuilderLogs.toString()); //Log your response where you need it
        return response;
    }

    public String getRequestBuilder() {
        return requestBuilderLogs.toString();
    }

    public String getResponseBuilder() {
        return responseBuilderLogs.toString();
    }

    public String objectValidation(Object o) {
        if (o == null) {
            return null;
        } else return o.toString();
    }


}