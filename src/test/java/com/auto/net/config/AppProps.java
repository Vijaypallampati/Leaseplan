package com.auto.net.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "app")
public class AppProps {
    private String baseUri;
    private String username;
    private String password;
    private String appUrl;
    private String guiEndpointUri;
}
