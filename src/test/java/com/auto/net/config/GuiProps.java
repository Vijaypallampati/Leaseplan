package com.auto.net.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "gui")
public class GuiProps {
    private int explicitWait;
    private int implicitWait;
    private int pageLoadTimeout;
    private int scriptTimeout;
}
