package com.auto.net.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "endpoint")
public class EndPointProps {
    private String token;
    private String ping;
    private String bookingId;
}
