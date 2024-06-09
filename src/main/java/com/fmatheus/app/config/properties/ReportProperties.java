package com.fmatheus.app.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "report")
public class ReportProperties {
    private String pathJasper;
    private String pathLogo;
    private String pathLogoCoffee;
    private String pathClientSimple;
    private String pathClientDetails;
    private String reportHeader;
}
